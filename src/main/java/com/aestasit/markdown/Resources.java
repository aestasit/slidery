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

package com.aestasit.markdown;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.io.Files.createTempDir;
import static org.apache.commons.io.FileUtils.openOutputStream;
import static org.apache.commons.io.FileUtils.toFile;
import static org.apache.commons.io.IOUtils.copyLarge;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Utility methods to convert different content sources (e.g. class path
 * references, <i>URL</i>s) to existing file objects on the local file system.
 * 
 * @author Andrey Adamovich
 * 
 */
public final class Resources {

  public static File file(final String filePath) {
    checkPath(filePath);
    return new File(filePath);
  }

  public static Collection<File> dir(final String dirPath) {
    return Arrays.asList(checkDirectory(dirPath).listFiles());
  }

  public static Collection<File> dir(final File dir) {
    return Arrays.asList(checkDirectory(dir).listFiles());
  }

  public static Collection<File> dir(final String dirPath, final String pattern) {
    return listFiles(checkDirectory(dirPath), pattern);
  }

  public static Collection<File> dir(final File dir, final String pattern) {
    return listFiles(checkDirectory(dir), pattern);
  }

  public static File classpath(final String filePath) {
    checkPath(filePath);
    final File tempFile = tempFile(filePath);
    copy(classLoader().getResourceAsStream(filePath), silentOpen(tempFile));
    return tempFile;
  }

  public static File url(final URL url) {
    checkNotNull(url, "URL is not specified!");
    final File tempFile = tempFile(toFile(url).getName());
    copy(silentOpen(url), silentOpen(tempFile));
    return tempFile;
  }

  private static void checkPath(final String path) {
    checkNotNull(path, "Path is not specified!");
    checkArgument(!isBlank(path), "Path is not specified!");
  }

  private static File checkDirectory(final String dirPath) {
    return checkDirectory(file(dirPath));
  }

  private static File checkDirectory(final File dir) {
    checkArgument(dir.exists(), "Directory '%s' does not exist!", dir.getPath());
    checkArgument(dir.isDirectory(), "Path '%s' is not a directory!", dir.getPath());
    return dir;
  }

  private static List<File> listFiles(final File dir, final String pattern) {
    return Arrays.asList(dir.listFiles(new FilenameFilter() {
      @Override
      public boolean accept(final File dir, final String name) {
        return name.matches(pattern);
      }
    }));
  }

  private static ClassLoader classLoader() {
    return Thread.currentThread().getContextClassLoader();
  }

  private static InputStream silentOpen(final URL url) {
    try {
      return url.openStream();
    } catch (final IOException e) {
      throw new RuntimeException("Unable to download data from: " + url, e);
    }
  }

  private static OutputStream silentOpen(final File file) {
    try {
      checkNotNull(file, "File is not specified!");
      return openOutputStream(file);
    } catch (final IOException e) {
      throw new RuntimeException("Unable to open file!", e);
    }
  }

  private static void copy(final InputStream input, final OutputStream output) {
    try {
      copyLarge(input, output);
    } catch (final IOException e) {
      throw new RuntimeException("Unable to copy data!", e);
    }
  }

  private static File tempFile(final String filePath) {
    final File tempDir = createTempDir();
    return new File(tempDir, new File(filePath).getName());
  }

}
