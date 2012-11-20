package com.aestasit.markdown;

import java.io.IOException;
import java.io.InputStream;

public class BaseTest {

  protected InputStream getTestData(String fileName) throws IOException {
    return getClass().getClassLoader().getResourceAsStream(fileName);
  }

}
