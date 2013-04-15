package com.aestasit.markdown.slidery.converters;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isBlank;
import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.groovy.control.CompilationFailedException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.aestasit.markdown.slidery.configuration.Configuration;
import com.aestasit.markdown.slidery.configuration.ConfigurationBuilder;
import com.aestasit.markdown.slidery.configuration.Location;
import com.uwyn.jhighlight.renderer.Renderer;
import com.uwyn.jhighlight.renderer.XhtmlRendererFactory;

/**
 * <p>
 * This is an extension to {@link BaseConverter} that processes configured template file with the help of Groovy's
 * {@link SimpleTemplateEngine}.
 * </p>
 * 
 * <p>
 * The template has access the following variables:
 * </p>
 * 
 * <ul>
 * <li><code>configuration</code> - converter's configuration object</li>
 * <li><code>document</code> - full <i>DOM</i> tree of the slides document</li>
 * <li><code>body</code> - body element</li>
 * <li><code>slides</code> - collection of slide elements</li>
 * <li><code>title</code> - presentation's title</li>
 * <li><code>author</code> - presentation's author</li>
 * <li><code>company</code> - author's employer</li>
 * <li><code>description</code> - presentation's description</li>
 * <li><code>date</code> - presentation's date</li>
 * <li><code>backgroundColor</code> - optional background color</li>
 * <li><code>fontColor</code> - optional font color</li>
 * <li><code>fontName</code> - custom font name</li>
 * <li><code>fontLink</code> - custom font link</li>
 * <li><code>theme</code> - selected presentation's theme</li>
 * <li><code>logoFile</code> - logo file name</li>
 * <li><code>outputFile</code> - presentation's final output file name</li>
 * </ul>
 * 
 * <p>
 * It also provides a number of hook methods for the subclasses to extend default behavior:
 * </p>
 * 
 * <ul>
 * <li>{@link #expandBinding(HashMap, Document, Configuration)} method allows adding more binding variables available to
 * the template.</li>
 * <li>{@link #transformDocument(Document, Configuration)} method allows modifying <i>DOM</i> tree of the slides
 * document.</li>
 * </ul>
 * 
 * @author Andrey Adamovich
 * 
 */
public abstract class TextTemplateConverter extends BaseConverter {

  final protected void convert(Writer output, Document slidesDocument, Configuration config) throws IOException {
    String templateText = readFileToString(config.getTemplateFile(), config.getInputEncoding().name());
    transformDocument(slidesDocument, config);
    HashMap<String, Object> parameters = createBinding(slidesDocument, config);
    compileTemplate(templateText).make(parameters).writeTo(output);
  }

  private HashMap<String, Object> createBinding(Document slidesDocument, Configuration config) {
    HashMap<String, Object> binding = new HashMap<String, Object>();
    binding.put("configuration", config);
    bindTitle(binding, slidesDocument, config);
    bindDomElements(binding, slidesDocument);
    bindConfigurationElements(binding, config);
    expandBinding(binding, slidesDocument, config);
    return binding;
  }

  private void bindDomElements(HashMap<String, Object> binding, Document slidesDocument) {
    binding.put("document", slidesDocument);
    binding.put("body", slidesDocument.getElementsByTag("body").first());
    binding.put("slides", getSlideCollection(slidesDocument));
  }

  private void bindTitle(HashMap<String, Object> binding, Document slidesDocument, Configuration config) {
    if (!isBlank(config.getTitle())) {
      binding.put("title", config.getTitle());
    } else {
      binding.put("title", getFirstSlideTitle(slidesDocument));
    }
  }

  private void bindConfigurationElements(HashMap<String, Object> binding, Configuration config) {
    binding.put("author", config.getAuthor());
    binding.put("company", config.getCompany());
    binding.put("description", defaultIfNull(config.getDescription(), binding.get("title")));
    binding.put("date", new SimpleDateFormat("dd-MM-yyyy").format(config.getDate()));
    binding.put("backgroundColor", config.getBackgroundColor());
    binding.put("fontColor", config.getFontColor());
    binding.put("fontName", config.getFontName());
    binding.put("fontLink", config.getFontLink());
    binding.put("theme", config.getTheme());
    if (config.getLogo() != null) {
      binding.put("logoFile", config.getLogo().getName());
    } else {
      binding.put("logoFile", null);
    }
    if (config.getOutputFile() != null) {
      binding.put("outputFile", config.getOutputFile().getName());
    } else {
      binding.put("outputFile", null);
    }
  }

  protected void expandBinding(final HashMap<String, Object> binding, Document slidesDocument, ConfigurationBuilder config) {
    // Override in subclasses.
  }

  protected void transformDocument(final Document slidesDocument, final Configuration config) {
    if (!config.notesIncluded()) {
      for (Element notesElement : slidesDocument.select("aside")) {
        notesElement.remove();
      }
    }
    if ("true".equals(config.getOption("renderSyntaxHighlighting"))) {
      renderSyntaxHighlightingHtml(slidesDocument, config);
    }
  }

  private void renderSyntaxHighlightingHtml(final Document slidesDocument, final Configuration config) {
    for (Element code : slidesDocument.select("code")) {
      Charset encoding = config.getInputEncoding();
      ByteArrayInputStream input = new ByteArrayInputStream(code.text().getBytes(encoding));
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      String className = code.className();
      if (StringUtils.isBlank(className)) {
        className = "java";
      }
      Renderer renderer = XhtmlRendererFactory.getRenderer(className);
      if (renderer != null) {
        try {
          renderer.highlight("slidery", input, out, encoding.name(), true);
          code.html(new String(out.toByteArray(), encoding));
          code.select("br").remove();
          removeComments(code);
          code.html(code.html().trim());
          Element parent = code.parent();
          if (parent.tagName() == "pre") {
            parent.addClass("code");
          }
        } catch (IOException e) {
          // TODO: Handle exception
        }
      }
    }
  }

  private void removeComments(Node parent) {
    List<Node> nodesToRemove = new ArrayList<Node>();
    for (Node child : parent.childNodes()) {
      if (child.nodeName().equals("#comment")) {
        nodesToRemove.add(child);
      }
    }
    for (Node node : nodesToRemove) {
      node.remove();
    }
  }

  protected Elements getSlideCollection(Document slidesDocument) {
    return slidesDocument.getElementsByTag("section");
  }

  protected String getFirstSlideTitle(Document slidesDocument) {
    Elements slideCollection = getSlideCollection(slidesDocument);
    if (slideCollection != null && slideCollection.size() > 0) {
      Elements header = slideCollection.first().getElementsByTag("header");
      if (header != null && header.size() > 0) {
        return header.first().text();
      }
    }
    return "";
  }

  protected Template compileTemplate(String templateText) throws IOException {
    SimpleTemplateEngine engine = new SimpleTemplateEngine();
    Template template = null;
    try {
      template = engine.createTemplate(templateText);
    } catch (CompilationFailedException e) {
      throw new IOException(e);
    } catch (ClassNotFoundException e) {
      throw new IOException(e);
    }
    return template;
  }

  public Collection<String> getTransitionIds() {
    return Collections.<String> emptyList();
  }

  public String getDefaultTransitionId() {
    return "default";
  }

  public Collection<String> getThemeIds() {
    return Collections.<String> emptyList();
  }

  public String getDefaultThemeId() {
    return "default";
  }

  public Collection<Location> getLocations() {
    return Collections.<Location> emptyList();
  }

}
