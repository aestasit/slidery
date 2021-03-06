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

package com.aestasit.markdown.slidery.converters;

import static com.aestasit.markdown.slidery.Slidery.PEGDOWN_ENABLE_ALL_EXTENSIONS;
import static com.aestasit.markdown.slidery.Slidery.PEGDOWN_ENABLE_ALL_EXTENSIONS_AND_SUPPRESS_HTML;
import static com.aestasit.markdown.slidery.Slidery.toDom;
import static org.apache.commons.io.FileUtils.copyFileToDirectory;
import static org.apache.commons.io.FileUtils.deleteDirectory;
import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.apache.commons.io.FileUtils.forceMkdir;
import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.FileUtils.readLines;
import static org.apache.commons.io.FileUtils.writeLines;
import static org.apache.commons.io.FilenameUtils.getBaseName;
import static org.apache.commons.io.FilenameUtils.getExtension;
import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.jsoup.nodes.Document;

import com.aestasit.markdown.slidery.configuration.Configuration;
import com.aestasit.markdown.slidery.configuration.ConfigurationBuilder;
import com.google.common.collect.Multimap;

/**
 * <p>
 * This is an abstract converter class that implements the following flow:
 * </p>
 * 
 * <ul>
 * <li>validate configuration data</li>
 * <li>join mark-down input files</li>
 * <li>create output directory</li>
 * <li>copy static files e.g. images, <i>CSS</i> files, <i>JavaScript</i> files
 * etc.</li>
 * <li>convert presentation to final format</li>
 * <li>clean up after conversion</li>
 * </ul>
 * 
 * <p>
 * Actual conversion is delegated to the subclasses, which need to override
 * abstract {@link #convert(Writer, Document, Configuration)} method. It also
 * provides a number of hook methods for the subclasses to extend default
 * behavior:
 * </p>
 * 
 * <ul>
 * <li>{@link #beforeStart(Configuration)}</li>
 * <li>{@link #beforeConversion(File, Configuration)}</li>
 * <li>{@link #afterConversion(File, Configuration)}</li>
 * </ul>
 * 
 * @author Andrey Adamovich
 * 
 */
public abstract class BaseConverter implements Converter {

  public void render(Configuration config) throws IOException {
    beforeStart(config);
    config.validate();
    if (config.getLogo() != null) {
      config.staticFile("images", config.getLogo());
    }
    copyStaticFiles(config.getStaticFiles(), createOutputDirectory(config));
    createOutput(joinInputFiles(config), config);
  }

  @SuppressWarnings("unchecked")
  private File joinInputFiles(Configuration config) throws IOException {
    File joinedFile = File.createTempFile("slidery", "joined");
    joinedFile.deleteOnExit();
    List<String> lines = new ArrayList<String>();
    for (File inputFile : config.getInputFiles()) {
      lines.addAll(readLines(inputFile, config.getInputEncoding().name()));
    }
    writeLines(joinedFile, lines);
    return joinedFile;
  }

  private File createOutputDirectory(Configuration config) throws IOException {
    File parentDir = config.getOutputFile().getParentFile();
    forceMkdir(parentDir);
    return parentDir;
  }

  private void copyStaticFiles(Multimap<String, File> staticFiles, File outputDir) throws IOException {
    for (Entry<String, File> templateFile : staticFiles.entries()) {
      String relativePath = templateFile.getKey();
      File destDir = new File(outputDir, relativePath);
      forceMkdir(destDir);
      copyFileToDirectory(templateFile.getValue(), destDir);
    }
  }

  private void createOutput(File joinedFile, Configuration config) throws IOException {
    beforeConversion(joinedFile, config);
    convertFile(config, joinedFile, config.getOutputFile());
    if (config.isSplitOutput()) {
      for (File inputFile : config.getInputFiles()) {
        convertFile(config, inputFile, getSplitOutputFile(config.getOutputFile(), inputFile));
      }
    }
    afterConversion(joinedFile, config);
    deleteTemporaryFiles(joinedFile, config);
  }

  protected File getSplitOutputFile(File outputFile, File inputFile) {
    String inputFileName = getBaseName(inputFile.getName()) + '.' + getExtension(outputFile.getName());
    return new File(outputFile.getParentFile(), inputFileName);
  }

  private void convertFile(Configuration config, File inputFile, File outputFile) throws IOException {
    FileOutputStream outputStream = null;
    try {
      outputStream = openOutputStream(outputFile);
      int parserOptions = PEGDOWN_ENABLE_ALL_EXTENSIONS_AND_SUPPRESS_HTML;
      if (!config.htmlStripped()) {
        parserOptions = PEGDOWN_ENABLE_ALL_EXTENSIONS;
      }
      convert(new OutputStreamWriter(outputStream), toDom(inputFile, parserOptions), config);
    } finally {
      closeQuietly(outputStream);
    }
  }

  private void deleteTemporaryFiles(File joinedFile, Configuration config) {
    for (File staticFile : config.getStaticFiles().values()) {
      deleteTemporaryFile(staticFile);
    }
    deleteQuietly(joinedFile);
  }

  private void deleteTemporaryFile(File staticFile) {
    File systemTempDir = new File(System.getProperty("java.io.tmpdir"));
    if (staticFile.getAbsolutePath().contains(systemTempDir.getAbsolutePath())) {
      try {
        deleteDirectory(staticFile.getParentFile());
      } catch (IOException e) {
      }
    }
  }

  protected void beforeStart(Configuration config) {
  }

  protected void beforeConversion(File inputFile, ConfigurationBuilder config) {
  }

  protected abstract void convert(Writer writer, Document slidesDocument, Configuration config) throws IOException;

  protected void afterConversion(File inputFile, Configuration config) {
  }

}
