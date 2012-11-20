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

}
