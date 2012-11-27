package com.aestasit.markdown.slidery.converters;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Date;

import com.google.common.collect.Multimap;

/**
 * Converter configuration interface.
 * 
 * @author Andrey Adamovich
 *
 */
public interface Configuration {

  void validate() throws IllegalStateException;

  // Mark-down input files
  Collection<File> getInputFiles();
  Configuration inputFile(File inputFile);
  Configuration inputFiles(Collection<File> inputFiles);
  Charset getInputEncoding();
  Configuration encoding(String encoding);
  Configuration encoding(Charset encoding);

  // Template
  File getTemplateFile();
  Configuration templateFile(File templateFile);

  // Static template or input files
  Multimap<String, File> getStaticFiles();
  Configuration staticFile(File staticFile);
  Configuration staticFile(String relativePath, File staticFile);

  // Output
  File getOutputFile();
  Configuration outputFile(File outputFile);

  // Meta-data
  Configuration author(String name);
  String getAuthor();
  Configuration company(String name);
  String getCompany();
  Configuration title(String title);
  String getTitle();
  Configuration description(String description);
  String getDescription();
  Configuration date(Date date);
  Configuration currentDate();
  Date getDate();
  Configuration logo(File file);
  File getLogo();

  // Lists
  boolean listsIncremented();
  Configuration incrementalLists();  
  Configuration incrementalLists(boolean state);
  
  // Notes
  boolean notesIncluded();
  Configuration includeNotes();
  Configuration excludeNotes();

  // Styling
  Configuration theme(String name);
  String getTheme();
  Configuration font(String name, URL link);
  Configuration font(String name);
  String getFontName();
  URL getFontLink();
  Configuration backgroundColor(String color);
  Configuration fontColor(String color); 
  Configuration color(String ref, String color);
  String getBackgroundColor();
  String getFontColor();
  String getColor(String name);
  
  // Custom settings
  String getOption(String name);
  Configuration option(String name, String value);

  // Rules
  Configuration transitionRule(String selector, String rule);
  Multimap<String, String> getTransitionRules();
  Configuration stylingRule(String selector, String rule);
  Multimap<String, String> getStylingRules();

  // Debug
  boolean isSplitOutput();
  Configuration splitOutput();
  Configuration splitOutput(boolean state);
  
}
