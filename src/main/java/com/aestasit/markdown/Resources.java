package com.aestasit.markdown;

import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.IOUtils.copyLarge;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

public final class Resources {

  public static File file(String filePath) {
    Preconditions.checkNotNull(filePath, "File path is not specified!");
    Preconditions.checkArgument(!StringUtils.isEmpty(filePath), "File path is not specified!");
    return new File(filePath);
  }

  public static File classpath(String filePath) {
    Preconditions.checkNotNull(filePath, "File path is not specified!");
    Preconditions.checkArgument(!StringUtils.isEmpty(filePath), "File path is not specified!");
    File tempFile = tempFile();
    copy(classLoader().getResourceAsStream(filePath), silentOpen(tempFile));
    return tempFile;
  }

  public static File url(URL url) {
    File tempFile = tempFile();
    copy(silentOpen(url), silentOpen(tempFile));
    return tempFile;
  }

  private static ClassLoader classLoader() {
    return Thread.currentThread().getContextClassLoader();
  }

  private static InputStream silentOpen(URL url) {
    try {
      Preconditions.checkNotNull(url, "URL is not specified!");
      return url.openStream();
    } catch (IOException e) {
      throw new RuntimeException("Unable to download data from: " + url, e);
    }
  }

  private static OutputStream silentOpen(File file) {
    try {
      Preconditions.checkNotNull(file, "File is not specified!");
      return openOutputStream(file);
    } catch (IOException e) {
      throw new RuntimeException("Unable to open file!", e);
    }
  }

  private static void copy(InputStream input, OutputStream output) {
    try {
      copyLarge(input, output);
    } catch (IOException e) {
      throw new RuntimeException("Unable to copy data!", e);
    }
  }

  private static File tempFile() {
    try {
      File tempFile = File.createTempFile("slidedown", "resource");
      tempFile.deleteOnExit();
      return tempFile;
    } catch (IOException e) {
      throw new RuntimeException("Unable to create temporary file!", e);
    }
  }

}
