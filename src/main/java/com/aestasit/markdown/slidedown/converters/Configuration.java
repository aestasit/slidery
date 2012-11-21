package com.aestasit.markdown.slidedown.converters;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collection;

import com.google.common.collect.Multimap;

public interface Configuration {

  Collection<File> getInputFiles();

  Multimap<String, File> getStaticFiles();

  File getTemplateFile();

  File getOutputFile();

  Charset getInputEncoding();

  Configuration inputFile(File inputFile);

  Configuration staticFile(File staticFile);

  Configuration staticFile(String relativePath, File staticFile);

  Configuration outputFile(File outputFile);

  Configuration encoding(String encoding);

  Configuration encoding(Charset encoding);

  void validate() throws IllegalStateException;

  Configuration templateFile(File templateFile);

  String getProperty(String name);

  Configuration property(String name, String value);

}
