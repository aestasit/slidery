package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;
import static com.aestasit.markdown.Resources.file;
import static org.apache.commons.io.FileUtils.deleteDirectory;

import java.io.IOException;

import org.junit.Before;

import com.aestasit.markdown.BaseTest;

public class BaseConverterTest extends BaseTest {

  protected Configuration createConfiguration() {
    return new SimpleConfiguration()
        .inputFile(classpath("01_simple_slides.md"))
        .inputFile(classpath("02_image_slides.md"))
        .inputFile(classpath("03_code_slides.md"))
        .inputFile(classpath("04_slide_notes.md"))
        .inputFile(classpath("05_table_slides.md"))
        .staticFile(classpath("LOGO_300dpi.png"))
        .staticFile(classpath("LOGO_FULL_300dpi.png"))
        .outputFile(file("./tmp/presentation.html"))
        .encoding("UTF-8");
  }

  @Before
  // @After
  public void clean() throws IOException {
    deleteDirectory(file("./tmp"));
  }

}
