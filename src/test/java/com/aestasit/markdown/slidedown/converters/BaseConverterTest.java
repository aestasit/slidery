package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;
import static com.aestasit.markdown.Resources.file;
import static org.apache.commons.io.FileUtils.deleteDirectory;

import java.io.IOException;

import org.junit.Before;

import com.aestasit.markdown.BaseTest;

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
        .splitOutput()
        .encoding("UTF-8");
    for(String filePath: allTestFiles()) {
      config.inputFile(classpath(filePath));
    }
    return config;
  }

  @Before
  // @After
  public void clean() throws IOException {
    deleteDirectory(file("./tmp"));
  }

}
