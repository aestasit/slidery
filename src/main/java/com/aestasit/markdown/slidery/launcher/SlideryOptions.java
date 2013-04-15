package com.aestasit.markdown.slidery.launcher;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collection;

import com.aestasit.markdown.slidery.configuration.Configuration;
import com.lexicalscope.jewel.cli.Option;
import com.lexicalscope.jewel.cli.Unparsed;

/**
 * 
 * 
 * @author Andrey Adamovich
 * 
 */
public interface SlideryOptions extends Configuration {

  @Option(defaultValue = { "deck-js" }, shortName = { "f" })
  String getFormat();

  @Unparsed
  Collection<File> getInputFiles();

  @Option(defaultToNull = true, shortName = { "e" })
  Charset getInputEncoding();

  @Option(defaultToNull = true, shortName = { "t" })
  File getTemplateFile();

  @Option(defaultValue = { "presentation/slides.html" }, shortName = { "o" })
  File getOutputFile();

  @Option(defaultToNull = true)
  String getAuthor();

  @Option(defaultToNull = true)
  String getCompany();

  @Option(defaultToNull = true)
  String getTitle();

  @Option(defaultToNull = true)
  String getDescription();

  @Option(defaultToNull = true)
  File getLogo();

  @Option(defaultToNull = true)
  String getTheme();

}
