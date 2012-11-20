package com.aestasit.markdown.slidedown;

import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.control.CompilationFailedException;

public class TextTemplateConverter extends BaseConverter {

  @Override
  protected void convert(String slides, Writer output, Configuration config) throws IOException {
    SimpleTemplateEngine engine = new SimpleTemplateEngine();
    String templateText = FileUtils.readFileToString(config.getTemplateFile(), config.getInputEncoding());
    Template template = compileTemplate(engine, templateText);
    // TODO: pass slides to the template
    template.make().writeTo(output);
  }

  private Template compileTemplate(SimpleTemplateEngine engine, String templateText) throws IOException {
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
