package com.aestasit.markdown.slidedown.converters;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

public class SimpleConfiguration implements Configuration {

  private File                     templateFile;
  private Collection<File>         inputFiles      = new LinkedHashSet<File>();
  private Charset                  inputEncoding   = Charset.forName("UTF-8");
  private Multimap<String, File>   staticFiles     = HashMultimap.create();
  private Map<String, String>      properties      = new HashMap<String, String>();
  private File                     outputFile;

  private String                   author;
  private String                   company;
  private String                   title;
  private String                   description;
  private Date                     date;
  private File                     logo;

  private String                   theme;
  private String                   font;
  private URL                      fontLink;
  private Map<String, String>      colors          = new HashMap<String, String>();
  private Multimap<String, String> transitionRules = HashMultimap.create();
  private Multimap<String, String> stylingRules    = HashMultimap.create();

  private boolean                  notesIncluded   = true;
  private boolean                  listIncremented = true;

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

  public Configuration author(String name) {
    this.author = name;
    return this;
  }

  public String getAuthor() {
    return author;
  }

  public Configuration company(String name) {
    this.company = name;
    return this;
  }

  public String getCompany() {
    return company;
  }

  public Configuration title(String title) {
    this.title = title;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public Configuration description(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Configuration date(Date date) {
    this.date = new Date(date.getTime());
    return this;
  }

  public Configuration currentDate() {
    this.date = new Date(date.getTime());
    return this;
  }

  public Date getDate() {
    return new Date(date.getTime());
  }

  public Configuration logo(File file) {
    this.logo = file;
    return this;
  }

  public File getLogo() {
    return logo;
  }

  public boolean listsIncremented() {
    return listIncremented;
  }

  public Configuration incrementalLists() {
    listIncremented = true;
    return this;
  }

  public Configuration incrementalLists(boolean state) {
    listIncremented = state;
    return this;
  }

  public Configuration includeNotes() {
    notesIncluded = true;
    return this;
  }

  public Configuration excludeNotes() {
    notesIncluded = false;
    return this;
  }

  public boolean notesIncluded() {
    return notesIncluded;
  }

  public Configuration theme(String name) {
    this.theme = name;
    return this;
  }

  public String getTheme() {
    return theme;
  }

  public Configuration font(String name, URL link) {
    font = name;
    fontLink = link;
    return this;
  }

  public Configuration font(String name) {
    font = name;
    fontLink = null;
    return this;
  }

  public String getFontName() {
    return font;
  }

  public URL getFontLink() {
    return fontLink;
  }

  public Configuration backgroundColor(String color) {
    colors.put("background", color);
    return this;
  }

  public Configuration fontColor(String color) {
    colors.put("font", color);
    return this;
  }

  public Configuration color(String ref, String color) {
    colors.put(ref, color);
    return this;
  }

  public String getBackgroundColor() {
    return colors.get("background");
  }

  public String getFontColor() {
    return colors.get("font");
  }

  public String getColor(String ref) {
    return colors.get(ref);
  }

  public Configuration transitionRule(String selector, String rule) {
    transitionRules.put(selector, rule);
    return this;
  }

  public Configuration stylingRule(String selector, String rule) {
    stylingRules.put(selector, rule);
    return this;
  }

  public Multimap<String, String> getTransitionRules() {
    return Multimaps.unmodifiableMultimap(transitionRules);
  }

  public Multimap<String, String> getStylingRules() {
    return Multimaps.unmodifiableMultimap(stylingRules);
  }

}
