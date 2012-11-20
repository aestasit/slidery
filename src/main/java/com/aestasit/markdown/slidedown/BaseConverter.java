package com.aestasit.markdown.slidedown;

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

public abstract class BaseConverter {

  void render(Configuration config) throws IOException {
    File joinedFile = joinInputFiles(config);
    File parentDir = createOutputDirectory(config);
    copyStaticFiles(config, parentDir);
    createOutput(config, joinedFile);
    joinedFile.delete();
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

  private void copyStaticFiles(Configuration config, File parentDir) throws IOException {
    for (File templateFile : config.getStaticFiles()) {
      copyFileToDirectory(templateFile, parentDir);
    }
  }

  private void createOutput(Configuration config, File joinedFile) throws IOException {
    beforeConversion(joinedFile, config);
    FileOutputStream outputStream = null;
    try {
      outputStream = openOutputStream(config.getOutputFile());
      convert(Slidedown.toSlides(joinedFile), new OutputStreamWriter(outputStream), config);
    } finally {
      closeQuietly(outputStream);
    }
    afterConversion(joinedFile, config);
  }


  protected void beforeConversion(File inputFile, Configuration config) {
  }

  protected abstract void convert(String slides, Writer writer, Configuration config) throws IOException;

  protected void afterConversion(File inputFile, Configuration config) {
  }

}
