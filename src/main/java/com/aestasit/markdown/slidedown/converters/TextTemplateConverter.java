package com.aestasit.markdown.slidedown.converters;

import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.control.CompilationFailedException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TextTemplateConverter extends BaseConverter {

  @Override
  final protected void convert(Document slidesDocument, Writer output, Configuration config) throws IOException {
    String templateText = FileUtils.readFileToString(config.getTemplateFile(), config.getInputEncoding());
    transformDocument(slidesDocument);
    HashMap<String, Object> parameters = createBinding(slidesDocument, config);
    compileTemplate(templateText).make(parameters).writeTo(output);
  }

  private HashMap<String, Object> createBinding(Document slidesDocument, Configuration config) {
    HashMap<String, Object> binding = new HashMap<String, Object>();
    binding.put("document", slidesDocument);
    binding.put("body", slidesDocument.getElementsByTag("body").first());
    binding.put("slides", getSlideCollection(slidesDocument));
    binding.put("title", getFirstSlideTitle(slidesDocument));    
    expandBinding(binding, slidesDocument, config);
    return binding;
  }

  protected void expandBinding(final HashMap<String, Object> binding, Document slidesDocument, Configuration config) {
    // Override in subclasses.
  }

  protected void transformDocument(final Document slidesDocument) {
    // Override in subclasses.
  }

  private Elements getSlideCollection(Document slidesDocument) {
    return slidesDocument.getElementsByTag("section");
  }

  private String getFirstSlideTitle(Document slidesDocument) {
    return getSlideCollection(slidesDocument).first().getElementsByTag("header").first().text();
  }

  private Template compileTemplate(String templateText) throws IOException {
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
