package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

/**
 * TODO: finish implemetation
 * 
 * @author Andrey Adamovich
 * 
 */
public class PdfConverter extends TextTemplateConverter {

  public static final String CONVERTER_ID = "pdf";

  protected void beforeStart(Configuration config) {
    config.templateFile(classpath("pdf/template.html"));
  }

  protected void afterConversion(File inputFile, Configuration config) {
    File tempFile = null;
    try {
      ITextRenderer renderer = new ITextRenderer();
      renderer.setDocument(config.getOutputFile());
      renderer.layout();
      tempFile = File.createTempFile("slidedown", ".pdf");
      FileOutputStream outputStream = new FileOutputStream(tempFile);
      try {
        renderer.createPDF(outputStream, true);
      } catch (DocumentException e) {
        throw new RuntimeException(e);
      } finally {
        closeQuietly(outputStream);
      }
      copyFile(tempFile, config.getOutputFile());
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      deleteQuietly(tempFile);
    }
  }

}
