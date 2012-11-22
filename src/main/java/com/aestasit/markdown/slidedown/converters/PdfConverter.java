package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.IOUtils;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

/**
 * TODO: finish implemetation
 * 
 * @author Andrey Adamovich
 *
 */
public class PdfConverter extends TextTemplateConverter {

  protected void beforeStart(Configuration config) {
    config.templateFile(classpath("pdf/template.html"));
  }

  protected void expandBinding(HashMap<String, Object> binding, Document slidesDocument, Configuration config) {
  }

  protected void transformDocument(Document slidesDocument) {
  }

  protected void afterConversion(File inputFile, Configuration config) {
    try {
      ITextRenderer renderer = new ITextRenderer();
      renderer.setDocument(config.getOutputFile());
      renderer.layout();
      FileOutputStream outputStream = new FileOutputStream(config.getOutputFile());
      try {
        renderer.createPDF(outputStream);
      } catch (DocumentException e) {
        throw new RuntimeException(e);
      } finally {
        IOUtils.closeQuietly(outputStream); 
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
