package com.aestasit.markdown.slidedown.converters;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class SimpleConfiguration implements Configuration {

  private Collection<File>       inputFiles    = new LinkedHashSet<File>();
  private Charset                inputEncoding = Charset.forName("UTF-8");
  private Multimap<String, File> staticFiles   = HashMultimap.create();
  private Map<String, String>    properties    = new HashMap<String, String>();
  private File                   outputFile;
  private File                   templateFile;

  public SimpleConfiguration inputFile(File inputFile) {
    inputFiles.add(inputFile);
    return this;
  }

  public SimpleConfiguration staticFile(File staticFile) {
    staticFiles.put(".", staticFile);
    return this;
  }

  public SimpleConfiguration staticFile(String relativePath, File staticFile) {
    staticFiles.put(relativePath, staticFile);
    return this;
  }

  public SimpleConfiguration outputFile(File outputFile) {
    this.outputFile = outputFile;
    return this;
  }

  public SimpleConfiguration templateFile(File templateFile) {
    this.templateFile = templateFile;
    return this;
  }

  public SimpleConfiguration encoding(String encoding) {
    Preconditions.checkArgument(Charset.isSupported(encoding), "Charset is not supported!");
    inputEncoding = Charset.forName(encoding);
    return this;
  }

  public SimpleConfiguration encoding(Charset encoding) {
    Preconditions.checkNotNull(encoding, "Charset is null!");
    Preconditions.checkArgument(Charset.isSupported(encoding.name()), "Charset is not supported!");
    inputEncoding = encoding;
    return this;
  }

  public SimpleConfiguration property(String name, String value) {
    properties.put(name, value);
    return this;
  }

  public void validate() {
    Preconditions.checkState(inputFiles.size() > 0, "No input files given!");
    Preconditions.checkState(outputFile != null, "Output file is not specified!");
    Preconditions.checkState(templateFile != null && templateFile.exists(), "Template file does not exist!");
  }

  public Collection<File> getInputFiles() {
    return Collections.unmodifiableCollection(inputFiles);
  }

  public Multimap<String, File> getStaticFiles() {
    return Multimaps.unmodifiableMultimap(staticFiles);
  }

  public File getTemplateFile() {
    return templateFile;
  }

  public File getOutputFile() {
    return outputFile;
  }

  public Charset getInputEncoding() {
    return inputEncoding;
  }

  public String getProperty(String name) {
    return properties.get(name);
  }

}
