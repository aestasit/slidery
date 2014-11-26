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

package com.aestasit.markdown.slidery.launcher;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Collection;

import com.aestasit.markdown.slidery.configuration.ConfigurationReader;
import com.lexicalscope.jewel.cli.Option;
import com.lexicalscope.jewel.cli.Unparsed;

/**
 * Slidery options parsed from command-line parameters.
 * 
 * @author Andrey Adamovich
 * 
 */
public interface SlideryOptions extends ConfigurationReader {

  @Option(defaultValue = { "deck-js" }, shortName = { "f" })
  String getFormat();

  @Override
  @Unparsed
  Collection<File> getInputFiles();

  @Override
  @Option(defaultToNull = true, shortName = { "e" })
  Charset getInputEncoding();

  @Override
  @Option(defaultToNull = true, shortName = { "t" })
  File getTemplateFile();

  @Override
  @Option(defaultValue = { "presentation/slides.html" }, shortName = { "o" })
  File getOutputFile();

  @Override
  @Option(defaultToNull = true)
  String getAuthor();

  @Override
  @Option(defaultToNull = true)
  String getCompany();

  @Override
  @Option(defaultToNull = true)
  String getTitle();

  @Override
  @Option(defaultToNull = true)
  String getDescription();

  @Override
  @Option(defaultToNull = true)
  File getLogo();

  @Override
  @Option(defaultToNull = true)
  String getTheme();

  @Override
  @Option(defaultValue = { "true" })
  boolean notesIncluded();

  @Override
  @Option(defaultValue = { "true" })
  boolean listsIncremented();

}
