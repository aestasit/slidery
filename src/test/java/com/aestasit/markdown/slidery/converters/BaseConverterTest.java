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

import static com.aestasit.markdown.Resources.classpath;
import static com.aestasit.markdown.Resources.file;
import static org.apache.commons.io.FileUtils.deleteDirectory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;

import com.aestasit.markdown.BaseTest;
import com.aestasit.markdown.slidery.configuration.Configuration;
import com.aestasit.markdown.slidery.configuration.SimpleConfiguration;

/**
 * @author Andrey Adamovich
 *
 */
public class BaseConverterTest extends BaseTest {

  protected Configuration createConfiguration() {    
    Configuration config = new SimpleConfiguration()
        .staticFile(classpath("LOGO_300dpi.png"))
        .staticFile(classpath("LOGO_FULL_300dpi.png"))
        .outputFile(file("./tmp/presentation.html"))
        .author("Andrey Adamovich")
        .company("Aestas/IT")
        .title("Test presentation")
        .description("Conversion library testing")
        .date(createDate())
        .logo(classpath("LOGO_FULL_352x103.png"))
        .includeNotes()
        .option("test", "true")
        .incrementalLists()
        .splitOutput()
        .option("renderSyntaxHighlighting", "true")
        .encoding("UTF-8");
    for(String filePath: allTestFiles()) {
      config.inputFile(classpath(filePath));
    }
    return config;
  }

  protected Date createDate() {
    try {
      return new SimpleDateFormat("dd-mm-yyyy").parse("11-11-2012");
    } catch (ParseException e) {
      return new Date();
    }
  }

  @Before
  // @After
  public void clean() throws IOException {
    deleteDirectory(file("./tmp"));
  }

}
