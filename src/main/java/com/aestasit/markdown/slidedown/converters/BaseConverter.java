package com.aestasit.markdown.slidedown.converters;

import static org.apache.commons.io.FileUtils.copyFileToDirectory;
import static org.apache.commons.io.FileUtils.forceMkdir;
import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.FileUtils.readLines;
import static org.apache.commons.io.FileUtils.writeLines;
import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collection;

import org.jsoup.nodes.Document;

import com.aestasit.markdown.slidedown.Slidedown;

public abstract class BaseConverter implements Converter {

  @Override
  public void render(Configuration config) throws IOException {
    beforeStart(config);
    config.validate();
    copyStaticFiles(config.getStaticFiles(), createOutputDirectory(config));
    createOutput(config, joinInputFiles(config));
  }

  private File joinInputFiles(Configuration config) throws IOException {
    File joinedFile = File.createTempFile("slidedown", "joined");
    joinedFile.deleteOnExit();
    for (File inputFile : config.getInputFiles()) {
      writeLines(joinedFile, readLines(inputFile, config.getInputEncoding()), true);
    }
    return joinedFile;
  }

  private File createOutputDirectory(Configuration config) throws IOException {
    File parentDir = config.getOutputFile().getParentFile();
    forceMkdir(parentDir);
    return parentDir;
  }

  private void copyStaticFiles(Collection<File> staticFiles, File outputDir) throws IOException {
    for (File templateFile : staticFiles) {
      copyFileToDirectory(templateFile, outputDir);
    }
  }

  private void createOutput(Configuration config, File joinedFile) throws IOException {
    beforeConversion(joinedFile, config);
    FileOutputStream outputStream = null;
    try {
      outputStream = openOutputStream(config.getOutputFile());
      convert(Slidedown.toDom(joinedFile), new OutputStreamWriter(outputStream), config);
    } finally {
      closeQuietly(outputStream);
    }
    afterConversion(joinedFile, config);
    joinedFile.delete();
  }

  protected void beforeStart(Configuration config) {
  }

  protected void beforeConversion(File inputFile, Configuration config) {
  }

  protected abstract void convert(Document slidesDocument, Writer writer, Configuration config) throws IOException;

  protected void afterConversion(File inputFile, Configuration config) {
  }

}
