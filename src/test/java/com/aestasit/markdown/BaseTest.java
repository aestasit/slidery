package com.aestasit.markdown;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Andrey Adamovich
 * 
 */
public class BaseTest {

  protected Logger log = LoggerFactory.getLogger("com.aestasit.markdown.slidery.tests");

  protected static InputStream testData(String fileName) throws IOException {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
  }

  protected static InputStream allTestData() throws IOException {
    ByteArrayOutputStream data = new ByteArrayOutputStream();
    for (String fileName : allTestFiles()) {
      IOUtils.write(IOUtils.toString(testData(fileName)), data);
    }
    IOUtils.closeQuietly(data);
    return new ByteArrayInputStream(data.toByteArray());
  }

  protected static String[] allTestFiles() {
    return new String[] { 
        "01_simple_slides.md", 
        "02_image_slides.md", 
        "03_code_slides.md", 
        "04_slide_notes.md",
        "05_table_slides.md",
        "06_slides_with_links.md",        
    };
  }

}
