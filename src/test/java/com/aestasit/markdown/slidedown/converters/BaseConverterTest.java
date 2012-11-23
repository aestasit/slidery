package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;
import static com.aestasit.markdown.Resources.file;
import static org.apache.commons.io.FileUtils.deleteDirectory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        .author("Andrey Adamovich")
        .company("Aestas IT")
        .title("Test presentation")
        .description("Conversion library testing")
        .date(createDate())
        .logo(classpath("LOGO_300dpi.png"))
        .includeNotes()
        .incrementalLists()
        .splitOutput()
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
