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
 * Straight-forward in-memory implementation of the {@link Configuration} interface.
 * 
 * @author Andrey Adamovich
 * 
 */
public class SimpleConfiguration implements Configuration {

  private File                           templateFile;
  private final Collection<File>         inputFiles      = new LinkedHashSet<File>();
  private Charset                        inputEncoding   = Charset.forName("UTF-8");
  private final Multimap<String, File>   staticFiles     = HashMultimap.create();
  private final Map<String, String>      options         = new HashMap<String, String>();
  private File                           outputFile;

  private String                         author;
  private String                         company;
  private String                         title;
  private String                         description;
  private Date                           date;
  private File                           logo;

  private String                         theme;
  private String                         font;
  private URL                            fontLink;
  private final Map<String, String>      colors          = new HashMap<String, String>();
  private final Multimap<String, String> transitionRules = HashMultimap.create();
  private final Multimap<String, String> stylingRules    = HashMultimap.create();

  private boolean                        notesIncluded   = true;
  private boolean                        listIncremented = true;
  private boolean                        splitOutput     = false;

  public SimpleConfiguration() {
    super();
  }

  public SimpleConfiguration(final Configuration config) {
    super();
    configuration(config);
  }

  public ConfigurationBuilder configuration(final Configuration config) {
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
      config.inputFiles(config.getInputFiles());
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

  public ConfigurationBuilder clear() {
    clearInputFiles();
    clearStaticFiles();
    clearTemplateFiles();
    clearSnippets();
    return this;
  }

  public ConfigurationBuilder clearInputFiles() {
    inputFiles.clear();
    return this;
  }

  public ConfigurationBuilder clearStaticFiles() {
    staticFiles.clear();
    return this;
  }

  public ConfigurationBuilder clearTemplateFiles() {
    templateFile = null;
    return this;
  }

  public ConfigurationBuilder clearSnippets() {
    // TODO Auto-generated method stub
    return this;
  }

  public SimpleConfiguration inputFile(final File inputFile) {
    inputFiles.add(inputFile);
    return this;
  }

  public SimpleConfiguration inputFiles(final Collection<File> inputFiles) {
    this.inputFiles.addAll(Ordering.from(fileNames()).sortedCopy(inputFiles));
    return this;
  }

  public SimpleConfiguration staticFile(final File staticFile) {
    staticFiles.put(".", staticFile);
    return this;
  }

  public SimpleConfiguration staticFiles(final Collection<File> staticFiles) {
    this.staticFiles.putAll(".", staticFiles);
    return this;
  }

  public SimpleConfiguration staticFile(final String relativePath, final File staticFile) {
    staticFiles.put(relativePath, staticFile);
    return this;
  }

  public SimpleConfiguration staticFiles(final String relativePath, final Collection<File> staticFiles) {
    this.staticFiles.putAll(relativePath, staticFiles);
    return this;
  }

  public SimpleConfiguration outputFile(final File outputFile) {
    this.outputFile = outputFile;
    return this;
  }

  public SimpleConfiguration templateFile(final File templateFile) {
    this.templateFile = templateFile;
    return this;
  }

  public SimpleConfiguration encoding(final String encoding) {
    Preconditions.checkArgument(Charset.isSupported(encoding), "Charset is not supported!");
    inputEncoding = Charset.forName(encoding);
    return this;
  }

  public SimpleConfiguration encoding(final Charset encoding) {
    Preconditions.checkNotNull(encoding, "Charset is null!");
    Preconditions.checkArgument(Charset.isSupported(encoding.name()), "Charset is not supported!");
    inputEncoding = encoding;
    return this;
  }

  public SimpleConfiguration option(final String name, final String value) {
    options.put(name, value);
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

  public String getOption(final String name) {
    return options.get(name);
  }

  public ConfigurationBuilder author(final String name) {
    author = name;
    return this;
  }

  public String getAuthor() {
    return author;
  }

  public ConfigurationBuilder company(final String name) {
    company = name;
    return this;
  }

  public String getCompany() {
    return company;
  }

  public ConfigurationBuilder title(final String title) {
    this.title = title;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public ConfigurationBuilder description(final String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ConfigurationBuilder date(final Date date) {
    this.date = new Date(date.getTime());
    return this;
  }

  public ConfigurationBuilder currentDate() {
    date = new Date();
    return this;
  }

  public Date getDate() {
    if (date == null) {
      currentDate();
    }
    return new Date(date.getTime());
  }

  public ConfigurationBuilder logo(final File file) {
    logo = file;
    return this;
  }

  public File getLogo() {
    return logo;
  }

  public boolean listsIncremented() {
    return listIncremented;
  }

  public ConfigurationBuilder incrementalLists() {
    listIncremented = true;
    return this;
  }

  public ConfigurationBuilder incrementalLists(final boolean state) {
    listIncremented = state;
    return this;
  }

  public ConfigurationBuilder includeNotes() {
    notesIncluded = true;
    return this;
  }

  public ConfigurationBuilder includeNotes(final boolean state) {
    notesIncluded = state;
    return this;
  }

  public ConfigurationBuilder excludeNotes() {
    notesIncluded = false;
    return this;
  }

  public boolean notesIncluded() {
    return notesIncluded;
  }

  public ConfigurationBuilder theme(final String name) {
    theme = name;
    return this;
  }

  public String getTheme() {
    return theme;
  }

  public ConfigurationBuilder font(final String name, final URL link) {
    font = name;
    fontLink = link;
    return this;
  }

  public ConfigurationBuilder font(final String name) {
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

  public ConfigurationBuilder backgroundColor(final String color) {
    colors.put("background", color);
    return this;
  }

  public ConfigurationBuilder fontColor(final String color) {
    colors.put("font", color);
    return this;
  }

  public ConfigurationBuilder color(final String ref, final String color) {
    colors.put(ref, color);
    return this;
  }

  public String getBackgroundColor() {
    return colors.get("background");
  }

  public String getFontColor() {
    return colors.get("font");
  }

  public String getColor(final String ref) {
    return colors.get(ref);
  }

  public ConfigurationBuilder transitionRule(final String selector, final String rule) {
    transitionRules.put(selector, rule);
    return this;
  }

  public ConfigurationBuilder stylingRule(final String selector, final String rule) {
    stylingRules.put(selector, rule);
    return this;
  }

  public Multimap<String, String> getTransitionRules() {
    return Multimaps.unmodifiableMultimap(transitionRules);
  }

  public Multimap<String, String> getStylingRules() {
    return Multimaps.unmodifiableMultimap(stylingRules);
  }

  public boolean isSplitOutput() {
    return splitOutput;
  }

  public ConfigurationBuilder splitOutput() {
    splitOutput = true;
    return this;
  }

  public ConfigurationBuilder splitOutput(final boolean state) {
    splitOutput = state;
    return this;
  }

  private static FileNameComparator fileNames() {
    return new FileNameComparator();
  }

  private final static class FileNameComparator implements Comparator<File> {

    public int compare(final File o1, final File o2) {
      return o1.getName().compareTo(o2.getName());
    }
  }

  public ConfigurationBuilder inputFile(final File inputFile, final Transition transition) {
    // TODO Auto-generated method stub
    return null;
  }

  public ConfigurationBuilder inputFile(final File inputFile, final Styling styling) {
    // TODO Auto-generated method stub
    return null;
  }

  public ConfigurationBuilder inputFile(final File inputFile, final Transition transition, final Styling styling) {
    // TODO Auto-generated method stub
    return null;
  }

  public ConfigurationBuilder snippet(final Location location, final String snippet) {
    // TODO Auto-generated method stub
    return null;
  }

  public ConfigurationBuilder defaultTransition(final Transition transition) {
    // TODO Auto-generated method stub
    return null;
  }

  public ConfigurationBuilder defaultStyling(final Styling styling) {
    // TODO Auto-generated method stub
    return null;
  }

  public ConfigurationBuilder templateFile(final String targetName, final File templateFile) {
    // TODO Auto-generated method stub
    return null;
  }

  public ConfigurationBuilder templateFiles(final Collection<File> templateFile) {
    // TODO Auto-generated method stub
    return null;
  }

  public ConfigurationBuilder verbose() {
    // TODO Auto-generated method stub
    return null;
  }

  public ConfigurationBuilder verbose(final boolean state) {
    // TODO Auto-generated method stub
    return null;
  }

}
