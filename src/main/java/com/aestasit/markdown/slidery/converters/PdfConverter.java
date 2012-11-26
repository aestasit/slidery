package com.aestasit.markdown.slidery.converters;

import static com.aestasit.markdown.Resources.classpath;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

/**
 * Presentation converter that is based on <a href="http://itextpdf.com/">iText</a> library capable of converting
 * <i>HTML</i> to <i>PDF</i>.
 * 
 * @author Andrey Adamovich
 * 
 */
public class PdfConverter extends TextTemplateConverter {

  public static final String CONVERTER_ID = "pdf";

  protected void beforeStart(Configuration config) {
    config
        .templateFile(classpath("pdf/template.html"))
        .staticFile("style", classpath("pdf/style/base.css"))
        .staticFile("style", classpath("pdf/style/code.css"))
        .staticFile("style", classpath("pdf/style/pdf.css"))
        .staticFile("style", classpath("pdf/style/reset.css"));
  }

  protected void afterConversion(File joinedInputFile, Configuration config) {
    convertToPdf(config.getOutputFile());
    if (config.isSplitOutput()) {
      for (File inputFile : config.getInputFiles()) {
        convertToPdf(getSplitOutputFile(config.getOutputFile(), inputFile));
      }
    }
    deleteStaticFiles(config);
  }

  private void convertToPdf(File outputFile) {
    File tempFile = null;
    try {
      ITextRenderer renderer = new ITextRenderer();
      renderer.setDocument(outputFile);
      renderer.layout();
      tempFile = File.createTempFile("slidery", ".pdf");
      FileOutputStream outputStream = new FileOutputStream(tempFile);
      try {
        renderer.createPDF(outputStream, true);
      } catch (DocumentException e) {
        throw new RuntimeException(e);
      } finally {
        closeQuietly(outputStream);
      }
      copyFile(tempFile, outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      deleteQuietly(tempFile);
    }
  }

  private void deleteStaticFiles(Configuration config) {
    File outputDirectory = config.getOutputFile().getParentFile();
    for (Entry<String, File> fileEntry : config.getStaticFiles().entries()) {
      String relativePath = fileEntry.getKey();
      String fileName = fileEntry.getValue().getName();
      deleteQuietly(new File(new File(outputDirectory, relativePath), fileName));
    }
  }

}
