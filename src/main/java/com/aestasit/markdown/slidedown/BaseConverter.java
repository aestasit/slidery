package com.aestasit.markdown.slidedown;

import static org.apache.commons.io.FileUtils.copyFileToDirectory;
import static org.apache.commons.io.FileUtils.forceMkdir;
import static org.apache.commons.io.FileUtils.readLines;
import static org.apache.commons.io.FileUtils.writeLines;

import java.io.File;
import java.io.IOException;

public abstract class BaseConverter {

  void render(Configuration config) throws IOException {

    // Join input files.
    File joinedFile = File.createTempFile("slidedown", "joined");
    joinedFile.deleteOnExit();
    for (File inputFile : config.getInputFiles()) {
      writeLines(joinedFile, readLines(inputFile, config.getInputEncoding()), true);
    }

    // Create output directory.
    File parentDir = config.getOutputFile().getParentFile();
    forceMkdir(parentDir);

    // Copy static files.
    for (File templateFile : config.getTemplateFiles()) {
      copyFileToDirectory(templateFile, parentDir);
    }

    // Create output file.
    beforeConversion(joinedFile, config);
    convert(Slidedown.toSlides(joinedFile), config.getOutputFile());
    afterConversion(joinedFile, config);

    // Clean-up.
    joinedFile.delete();
    
  }

  protected abstract void convert(String slides, File outputFile);

  protected void beforeConversion(File inputFile, Configuration config) {
  }

  protected void afterConversion(File inputFile, Configuration config) {
  }

}
