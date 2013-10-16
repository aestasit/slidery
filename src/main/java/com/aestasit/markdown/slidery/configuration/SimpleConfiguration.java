/*
 * Copyright (C) 2011-2013 Aestas/IT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aestasit.markdown.slidery.configuration;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;

/**
 * Straight-forward in-memory implementation of the {@link Configuration}
 * interface.
 * 
 * @author Andrey Adamovich
 * 
 */
public class SimpleConfiguration implements Configuration {

  private File templateFile;
  private final Collection<File> inputFiles = new LinkedHashSet<File>();
  private Charset inputEncoding = Charset.forName("UTF-8");
  private final Multimap<String, File> staticFiles = HashMultimap.create();
  private final Map<String, String> options = new HashMap<String, String>();
  private File outputFile;

  private String author;
  private String company;
  private String title;
  private String description;
  private Date date;
  private File logo;

  private String theme;
  private String font;
  private URL fontLink;
  private final Map<String, String> colors = new HashMap<String, String>();
  private final Multimap<String, String> transitionRules = HashMultimap.create();
  private final Multimap<String, String> stylingRules = HashMultimap.create();

  private boolean notesIncluded = true;
  private boolean listIncremented = true;
  private boolean splitOutput = false;

  public SimpleConfiguration() {
    super();
  }

  public SimpleConfiguration(final Configuration config) {
    super();
    configuration(config);
  }

  @Override
  public ConfigurationBuilder configuration(final ConfigurationReader config) {
    author(config.getAuthor());
    backgroundColor(config.getBackgroundColor());
    // TODO: config.getColor(name)
    // TODO: color(ref, color)
    company(config.getCompany());
    if (config.getDate() != null) {
      date(config.getDate());
    }
    description(config.getDescription());
    if (config.getInputEncoding() != null) {
      encoding(config.getInputEncoding());
    }
    // TODO: font(name)
    // TODO: font(name, link)
    fontColor(config.getFontColor());
    includeNotes(config.notesIncluded());
    incrementalLists(config.listsIncremented());
    if (config.getInputFiles() != null) {
      inputFiles(config.getInputFiles());
    }
    logo(config.getLogo());
    // TODO: option(name, value)
    outputFile(config.getOutputFile());
    splitOutput(config.isSplitOutput());
    // TODO: staticFile(staticFile)
    // TODO: stylingRule(selector, rule)
    templateFile(config.getTemplateFile());
    theme(config.getTheme());
    title(config.getTitle());
    // TODO: transitionRule(selector, rule)
    return this;
  }

  @Override
  public ConfigurationBuilder clear() {
    clearInputFiles();
    clearStaticFiles();
    clearTemplateFiles();
    clearSnippets();
    return this;
  }

  @Override
  public ConfigurationBuilder clearInputFiles() {
    inputFiles.clear();
    return this;
  }

  @Override
  public ConfigurationBuilder clearStaticFiles() {
    staticFiles.clear();
    return this;
  }

  @Override
  public ConfigurationBuilder clearTemplateFiles() {
    templateFile = null;
    return this;
  }

  @Override
  public ConfigurationBuilder clearSnippets() {
    // TODO Auto-generated method stub
    return this;
  }

  @Override
  public SimpleConfiguration inputFile(final File inputFile) {
    inputFiles.add(inputFile);
    return this;
  }

  @Override
  public SimpleConfiguration inputFiles(final Collection<File> inputFiles) {
    this.inputFiles.addAll(Ordering.from(fileNames()).sortedCopy(inputFiles));
    return this;
  }

  @Override
  public SimpleConfiguration staticFile(final File staticFile) {
    staticFiles.put(".", staticFile);
    return this;
  }

  @Override
  public SimpleConfiguration staticFiles(final Collection<File> staticFiles) {
    this.staticFiles.putAll(".", staticFiles);
    return this;
  }

  @Override
  public SimpleConfiguration staticFile(final String relativePath, final File staticFile) {
    staticFiles.put(relativePath, staticFile);
    return this;
  }

  @Override
  public SimpleConfiguration staticFiles(final String relativePath, final Collection<File> staticFiles) {
    this.staticFiles.putAll(relativePath, staticFiles);
    return this;
  }

  @Override
  public SimpleConfiguration outputFile(final File outputFile) {
    this.outputFile = outputFile;
    return this;
  }

  @Override
  public SimpleConfiguration templateFile(final File templateFile) {
    this.templateFile = templateFile;
    return this;
  }

  @Override
  public SimpleConfiguration encoding(final String encoding) {
    Preconditions.checkArgument(Charset.isSupported(encoding), "Charset is not supported!");
    inputEncoding = Charset.forName(encoding);
    return this;
  }

  @Override
  public SimpleConfiguration encoding(final Charset encoding) {
    Preconditions.checkNotNull(encoding, "Charset is null!");
    Preconditions.checkArgument(Charset.isSupported(encoding.name()), "Charset is not supported!");
    inputEncoding = encoding;
    return this;
  }

  @Override
  public SimpleConfiguration option(final String name, final String value) {
    options.put(name, value);
    return this;
  }

  @Override
  public void validate() {
    Preconditions.checkState(inputFiles.size() > 0, "No input files given!");
    Preconditions.checkState(outputFile != null, "Output file is not specified!");
    Preconditions.checkState(templateFile != null && templateFile.exists(), "Template file does not exist!");
  }

  @Override
  public Collection<File> getInputFiles() {
    return Collections.unmodifiableCollection(inputFiles);
  }

  @Override
  public Multimap<String, File> getStaticFiles() {
    return Multimaps.unmodifiableMultimap(staticFiles);
  }

  @Override
  public File getTemplateFile() {
    return templateFile;
  }

  @Override
  public File getOutputFile() {
    return outputFile;
  }

  @Override
  public Charset getInputEncoding() {
    return inputEncoding;
  }

  @Override
  public String getOption(final String name) {
    return options.get(name);
  }

  @Override
  public ConfigurationBuilder author(final String name) {
    author = name;
    return this;
  }

  @Override
  public String getAuthor() {
    return author;
  }

  @Override
  public ConfigurationBuilder company(final String name) {
    company = name;
    return this;
  }

  @Override
  public String getCompany() {
    return company;
  }

  @Override
  public ConfigurationBuilder title(final String title) {
    this.title = title;
    return this;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public ConfigurationBuilder description(final String description) {
    this.description = description;
    return this;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public ConfigurationBuilder date(final Date date) {
    this.date = new Date(date.getTime());
    return this;
  }

  @Override
  public ConfigurationBuilder currentDate() {
    date = new Date();
    return this;
  }

  @Override
  public Date getDate() {
    if (date == null) {
      currentDate();
    }
    return new Date(date.getTime());
  }

  @Override
  public ConfigurationBuilder logo(final File file) {
    logo = file;
    return this;
  }

  @Override
  public File getLogo() {
    return logo;
  }

  @Override
  public boolean listsIncremented() {
    return listIncremented;
  }

  @Override
  public ConfigurationBuilder incrementalLists() {
    listIncremented = true;
    return this;
  }

  @Override
  public ConfigurationBuilder incrementalLists(final boolean state) {
    listIncremented = state;
    return this;
  }

  @Override
  public ConfigurationBuilder includeNotes() {
    notesIncluded = true;
    return this;
  }

  @Override
  public ConfigurationBuilder includeNotes(final boolean state) {
    notesIncluded = state;
    return this;
  }

  @Override
  public ConfigurationBuilder excludeNotes() {
    notesIncluded = false;
    return this;
  }

  @Override
  public boolean notesIncluded() {
    return notesIncluded;
  }

  @Override
  public ConfigurationBuilder theme(final String name) {
    theme = name;
    return this;
  }

  @Override
  public String getTheme() {
    return theme;
  }

  @Override
  public ConfigurationBuilder font(final String name, final URL link) {
    font = name;
    fontLink = link;
    return this;
  }

  @Override
  public ConfigurationBuilder font(final String name) {
    font = name;
    fontLink = null;
    return this;
  }

  @Override
  public String getFontName() {
    return font;
  }

  @Override
  public URL getFontLink() {
    return fontLink;
  }

  @Override
  public ConfigurationBuilder backgroundColor(final String color) {
    colors.put("background", color);
    return this;
  }

  @Override
  public ConfigurationBuilder fontColor(final String color) {
    colors.put("font", color);
    return this;
  }

  @Override
  public ConfigurationBuilder color(final String ref, final String color) {
    colors.put(ref, color);
    return this;
  }

  @Override
  public String getBackgroundColor() {
    return colors.get("background");
  }

  @Override
  public String getFontColor() {
    return colors.get("font");
  }

  @Override
  public String getColor(final String ref) {
    return colors.get(ref);
  }

  @Override
  public ConfigurationBuilder transitionRule(final String selector, final String rule) {
    transitionRules.put(selector, rule);
    return this;
  }

  @Override
  public ConfigurationBuilder stylingRule(final String selector, final String rule) {
    stylingRules.put(selector, rule);
    return this;
  }

  @Override
  public Multimap<String, String> getTransitionRules() {
    return Multimaps.unmodifiableMultimap(transitionRules);
  }

  @Override
  public Multimap<String, String> getStylingRules() {
    return Multimaps.unmodifiableMultimap(stylingRules);
  }

  @Override
  public boolean isSplitOutput() {
    return splitOutput;
  }

  @Override
  public ConfigurationBuilder splitOutput() {
    splitOutput = true;
    return this;
  }

  @Override
  public ConfigurationBuilder splitOutput(final boolean state) {
    splitOutput = state;
    return this;
  }

  @Override
  public ConfigurationBuilder inputFile(final File inputFile, final Transition transition) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConfigurationBuilder inputFile(final File inputFile, final Styling styling) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConfigurationBuilder inputFile(final File inputFile, final Transition transition, final Styling styling) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConfigurationBuilder snippet(final Location location, final String snippet) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConfigurationBuilder defaultTransition(final Transition transition) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConfigurationBuilder defaultStyling(final Styling styling) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConfigurationBuilder templateFile(final String targetName, final File templateFile) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConfigurationBuilder templateFiles(final Collection<File> templateFile) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConfigurationBuilder verbose() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ConfigurationBuilder verbose(final boolean state) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Transition getTransition(final File inputFile) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Styling getStyling(final File inputFile) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<String, String> getOptions() {
    // TODO Auto-generated method stub
    return null;
  }

  private static FileNameComparator fileNames() {
    return new FileNameComparator();
  }

  private final static class FileNameComparator implements Comparator<File> {

    @Override
    public int compare(final File o1, final File o2) {
      return o1.getName().compareTo(o2.getName());
    }
  }

}
