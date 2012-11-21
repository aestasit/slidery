package com.aestasit.markdown;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.io.Files.createTempDir;
import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.FileUtils.toFile;
import static org.apache.commons.io.IOUtils.copyLarge;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public final class Resources {

  public static File file(String filePath) {
    checkNotNull(filePath, "File path is not specified!");
    checkArgument(!isEmpty(filePath), "File path is not specified!");
    return new File(filePath);
  }

  public static File classpath(String filePath) {
    checkNotNull(filePath, "File path is not specified!");
    checkArgument(!isEmpty(filePath), "File path is not specified!");
    File tempFile = tempFile(filePath);
    copy(classLoader().getResourceAsStream(filePath), silentOpen(tempFile));
    return tempFile;
  }

  public static File url(URL url) {
    checkNotNull(url, "URL is not specified!");
    File tempFile = tempFile(toFile(url).getName());
    copy(silentOpen(url), silentOpen(tempFile));
    return tempFile;
  }

  private static ClassLoader classLoader() {
    return Thread.currentThread().getContextClassLoader();
  }

  private static InputStream silentOpen(URL url) {
    try {
      return url.openStream();
    } catch (IOException e) {
      throw new RuntimeException("Unable to download data from: " + url, e);
    }
  }

  private static OutputStream silentOpen(File file) {
    try {
      checkNotNull(file, "File is not specified!");
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

  private static File tempFile(String filePath) {
    File tempDir = createTempDir();
    return new File(tempDir, new File(filePath).getName());
  }

}
