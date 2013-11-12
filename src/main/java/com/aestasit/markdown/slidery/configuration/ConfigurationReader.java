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
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Multimap;

/**
 * Converter configuration reader interface.
 * 
 * @author Andrey Adamovich
 * 
 */
public interface ConfigurationReader {

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
  
  boolean htmlStripped();

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
