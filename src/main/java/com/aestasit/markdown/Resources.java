package com.aestasit.markdown;

import static org.apache.commons.io.FileUtils.toFile;

import java.io.File;
import java.net.URL;

public final class Resources {

  public static File file(String filePath) {
    return new File(filePath);
  }

  public static File classpath(String filePath) {
    return url(Thread.currentThread().getContextClassLoader().getResource(filePath));
  }

  public static File url(URL url) {
    return toFile(url);
  }

}
