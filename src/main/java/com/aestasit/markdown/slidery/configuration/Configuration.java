package com.aestasit.markdown.slidery.configuration;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Multimap;

/**
 * Converter configuration reader interface.
 * 
 * @author Andrey Adamovich
 * 
 */
public interface Configuration extends ConfigurationBuilder {

  Collection<File> getInputFiles();

  Transition getTransition(File inputFile);

  Styling getStyling(File inputFile);

  Charset getInputEncoding();

  File getTemplateFile();

  Multimap<String, File> getStaticFiles();

  File getOutputFile();

  String getAuthor();

  String getCompany();

  String getTitle();

  String getDescription();

  Date getDate();

  File getLogo();

  boolean listsIncremented();

  boolean notesIncluded();

  String getTheme();

  String getFontName();

  URL getFontLink();

  String getBackgroundColor();

  String getFontColor();

  String getColor(String name);

  String getOption(String name);

  Map<String, String> getOptions();

  Multimap<String, String> getTransitionRules();

  Multimap<String, String> getStylingRules();

  boolean isSplitOutput();

}
