package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.slidedown.Slidedown.toDom;
import static org.apache.commons.io.FileUtils.copyFileToDirectory;
import static org.apache.commons.io.FileUtils.deleteDirectory;
import static org.apache.commons.io.FileUtils.deleteQuietly;
import static org.apache.commons.io.FileUtils.forceMkdir;
import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.FileUtils.readLines;
import static org.apache.commons.io.FileUtils.writeLines;
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

import com.google.common.collect.Multimap;

/**
 * <p>This is an abstract converter class that implements the following flow:</p>
 * 
 *   <ul>
 *     <li>validate configuration data</li>
 *     <li>join mark-down input files</li>
 *     <li>create output directory</li>
 *     <li>copy static files e.g. images, <i>CSS</i> files, <i>JavaScript</i> files etc.</li>
 *     <li>convert presentation to final format</li> 
 *     <li>clean up after conversion</li>
 *   </ul>
 * 
 * <p>Actual conversion is delegated to the subclasses, which need to override abstract {@link #convert(Document, Writer, Configuration)} method. 
 *    It also provides a number of hook methods for the subclasses to extend default behavior:</p> 
 * 
 *   <ul>
 *    <li>{@link #beforeStart(Configuration)}</li>
 *    <li>{@link #beforeConversion(File, Configuration)}</li>
 *    <li>{@link #afterConversion(File, Configuration)}</li>
 *   </ul>
 *   
 * @author Andrey Adamovich
 * 
 */
public abstract class BaseConverter implements Converter {

  public void render(Configuration config) throws IOException {
    beforeStart(config);
    config.validate();
    copyStaticFiles(config.getStaticFiles(), createOutputDirectory(config));
    createOutput(config, joinInputFiles(config));
  }

  @SuppressWarnings("unchecked")
  private File joinInputFiles(Configuration config) throws IOException {
    File joinedFile = File.createTempFile("slidedown", "joined");
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

  private void createOutput(Configuration config, File joinedFile) throws IOException {
    beforeConversion(joinedFile, config);
    FileOutputStream outputStream = null;
    try {
      outputStream = openOutputStream(config.getOutputFile());
      convert(toDom(joinedFile), new OutputStreamWriter(outputStream), config);
    } finally {
      closeQuietly(outputStream);
    }
    afterConversion(joinedFile, config);
    deleteTemporaryFiles(joinedFile, config);
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

  protected void beforeConversion(File inputFile, Configuration config) {
  }

  protected abstract void convert(Document slidesDocument, Writer writer, Configuration config) throws IOException;

  protected void afterConversion(File inputFile, Configuration config) {
  }

}
