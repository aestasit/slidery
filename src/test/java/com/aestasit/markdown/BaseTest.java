package com.aestasit.markdown;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

  protected Logger log = LoggerFactory.getLogger("com.aestasit.markdown.slidedown.tests");
  
  protected static InputStream testData(String fileName) throws IOException {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
  }

}
