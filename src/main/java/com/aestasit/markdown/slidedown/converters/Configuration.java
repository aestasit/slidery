package com.aestasit.markdown.slidedown.converters;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collection;

public interface Configuration {

  Collection<File> getInputFiles();

  Collection<File> getStaticFiles();

  File getTemplateFile();
  
  File getOutputFile();

  Charset getInputEncoding();

  Configuration inputFile(File inputFile);

  Configuration staticFile(File staticFile);

  Configuration outputFile(File outputFile);

  Configuration encoding(String encoding);

  Configuration encoding(Charset encoding);

  void validate() throws IllegalStateException;

  Configuration templateFile(File templateFile);

  String getProperty(String name);

  Configuration property(String name, String value);

}
