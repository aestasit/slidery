package com.aestasit.markdown.slidedown.converters;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.lang3.StringUtils.isBlank;
import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.codehaus.groovy.control.CompilationFailedException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * <p>
 * This is an extension to {@link BaseConverter} that processes configured
 * template file with the help of Groovy's {@link SimpleTemplateEngine}.
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
 * It also provides a number of hook methods for the subclasses to extend
 * default behavior:
 * </p>
 * 
 * <ul>
 * <li>{@link #expandBinding(HashMap, Document, Configuration)} method allows
 * adding more binding variables available to the template.</li>
 * <li>{@link #transformDocument(Document)} method allows modifying <i>DOM</i>
 * tree of the slides document.</li>
 * </ul>
 * 
 * @author Andrey Adamovich
 * 
 */
public abstract class TextTemplateConverter extends BaseConverter {

  final protected void convert(Document slidesDocument, Writer output, Configuration config) throws IOException {
    String templateText = readFileToString(config.getTemplateFile(), config.getInputEncoding().name());
    transformDocument(slidesDocument, config);
    HashMap<String, Object> parameters = createBinding(slidesDocument, config);
    compileTemplate(templateText).make(parameters).writeTo(output);
  }

  private HashMap<String, Object> createBinding(Document slidesDocument, Configuration config) {
    HashMap<String, Object> binding = new HashMap<String, Object>();
    binding.put("configuration", config);
    bindTitle(slidesDocument, config, binding);
    bindDomElements(slidesDocument, binding);
    bindConfigurationElements(config, binding);
    expandBinding(binding, slidesDocument, config);
    return binding;
  }

  private void bindDomElements(Document slidesDocument, HashMap<String, Object> binding) {
    binding.put("document", slidesDocument);
    binding.put("body", slidesDocument.getElementsByTag("body").first());
    binding.put("slides", getSlideCollection(slidesDocument));
  }

  private void bindTitle(Document slidesDocument, Configuration config, HashMap<String, Object> binding) {
    if (!isBlank(config.getTitle())) {
      binding.put("title", config.getTitle());
    } else {
      binding.put("title", getFirstSlideTitle(slidesDocument));
    }
  }

  private void bindConfigurationElements(Configuration config, HashMap<String, Object> binding) {
    binding.put("author", config.getAuthor());
    binding.put("company", config.getCompany());
    binding.put("description", config.getDescription());
    binding.put("date", new SimpleDateFormat("dd-mm-yyyy").format(config.getDate()));
    binding.put("backgroundColor", config.getBackgroundColor());
    binding.put("fontColor", config.getFontColor());
    binding.put("fontName", config.getFontName());
    binding.put("fontLink", config.getFontLink());
    binding.put("theme", config.getTheme());
    if (config.getLogo() != null) {
      binding.put("logoFile", config.getLogo().getName());
    }
    if (config.getOutputFile() != null) {
      binding.put("outputFile", config.getOutputFile().getName());
    }
  }

  protected void expandBinding(final HashMap<String, Object> binding, Document slidesDocument, Configuration config) {
    // Override in subclasses.
  }

  protected void transformDocument(final Document slidesDocument, final Configuration config) {
    if (!config.notesIncluded()) {
      for (Element notesElement : slidesDocument.select("aside")) {
        notesElement.remove();
      }
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

}
