/*
 * Copyright (C) 2011-2014 Aestas/IT
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
import java.util.Date;

/**
 * Converter configuration writer interface.
 * 
 * @author Andrey Adamovich
 * 
 */
public interface ConfigurationBuilder {

  void validate() throws IllegalStateException;

  ConfigurationBuilder configuration(ConfigurationReader config);

  ConfigurationBuilder clear();

  ConfigurationBuilder clearInputFiles();

  ConfigurationBuilder clearStaticFiles();

  ConfigurationBuilder clearTemplateFiles();

  ConfigurationBuilder clearSnippets();

  ConfigurationBuilder inputFile(File inputFile);

  ConfigurationBuilder inputFile(File inputFile, Transition transition);

  ConfigurationBuilder inputFile(File inputFile, Styling styling);

  ConfigurationBuilder inputFile(File inputFile, Transition transition, Styling styling);

  ConfigurationBuilder snippet(Location location, String snippet);

  ConfigurationBuilder defaultTransition(Transition transition);

  ConfigurationBuilder defaultStyling(Styling styling);

  ConfigurationBuilder inputFiles(Collection<File> inputFiles);

  Configuration encoding(String encoding);

  ConfigurationBuilder encoding(Charset encoding);

  ConfigurationBuilder templateFile(File templateFile);

  ConfigurationBuilder templateFile(String targetName, File templateFile);

  ConfigurationBuilder templateFiles(Collection<File> templateFile);

  ConfigurationBuilder staticFile(File staticFile);

  ConfigurationBuilder staticFiles(Collection<File> staticFiles);

  ConfigurationBuilder staticFile(String relativePath, File staticFile);

  ConfigurationBuilder staticFiles(String relativePath, Collection<File> staticFiles);

  Configuration outputFile(File outputFile);

  // Meta-data
  ConfigurationBuilder author(String name);

  ConfigurationBuilder company(String name);

  ConfigurationBuilder title(String title);

  ConfigurationBuilder description(String description);

  ConfigurationBuilder date(Date date);

  ConfigurationBuilder currentDate();

  ConfigurationBuilder logo(File file);

  ConfigurationBuilder incrementalLists();

  ConfigurationBuilder incrementalLists(boolean state);

  ConfigurationBuilder includeNotes();

  ConfigurationBuilder includeNotes(boolean state);

  ConfigurationBuilder excludeNotes();

  ConfigurationBuilder stripHtml();

  ConfigurationBuilder stripHtml(boolean state);   
  
  // Styling
  ConfigurationBuilder theme(String name);

  ConfigurationBuilder font(String name, URL link);

  ConfigurationBuilder font(String name);

  ConfigurationBuilder backgroundColor(String color);

  ConfigurationBuilder fontColor(String color);

  ConfigurationBuilder color(String ref, String color);

  ConfigurationBuilder option(String name, String value);

  // Rules
  ConfigurationBuilder transitionRule(String selector, String rule);

  ConfigurationBuilder stylingRule(String selector, String rule);

  ConfigurationBuilder splitOutput();

  ConfigurationBuilder splitOutput(boolean state);

  ConfigurationBuilder verbose();

  ConfigurationBuilder verbose(boolean state);

}