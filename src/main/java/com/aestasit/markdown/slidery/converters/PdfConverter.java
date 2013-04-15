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

import com.aestasit.markdown.slidery.configuration.Configuration;
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

  protected void beforeStart(final Configuration config) {
    config
        .templateFile(classpath("pdf/template.html"))
        .staticFile("style", classpath("pdf/style/base.css"))
        .staticFile("style", classpath("pdf/style/code.css"))
        .staticFile("style", classpath("pdf/style/pdf.css"))
        .staticFile("style", classpath("pdf/style/reset.css"));
  }

  protected void afterConversion(final File joinedInputFile, final Configuration config) {
    convertToPdf(config.getOutputFile());
    if (config.isSplitOutput()) {
      for (final File inputFile : config.getInputFiles()) {
        convertToPdf(getSplitOutputFile(config.getOutputFile(), inputFile));
      }
    }
    deleteStaticFiles(config);
  }

  private void convertToPdf(final File outputFile) {
    File tempFile = null;
    try {
      final ITextRenderer renderer = new ITextRenderer();
      renderer.setDocument(outputFile);
      renderer.layout();
      tempFile = File.createTempFile("slidery", ".pdf");
      final FileOutputStream outputStream = new FileOutputStream(tempFile);
      try {
        renderer.createPDF(outputStream, true);
      } catch (final DocumentException e) {
        throw new RuntimeException(e);
      } finally {
        closeQuietly(outputStream);
      }
      copyFile(tempFile, outputFile);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    } finally {
      deleteQuietly(tempFile);
    }
  }

  private void deleteStaticFiles(final Configuration config) {
    final File outputDirectory = config.getOutputFile().getParentFile();
    for (final Entry<String, File> fileEntry : config.getStaticFiles().entries()) {
      final String relativePath = fileEntry.getKey();
      final String fileName = fileEntry.getValue().getName();
      deleteQuietly(new File(new File(outputDirectory, relativePath), fileName));
    }
  }

  public String getId() {
    return CONVERTER_ID;
  }

  public String getDescription() {
    return "Basic PDF converter.";
  }

}
