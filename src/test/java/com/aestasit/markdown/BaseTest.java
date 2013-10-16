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
        "07_nested_lists.md",
        "08_titleless_slides.md",
    };
  }

}
