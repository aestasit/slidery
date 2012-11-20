package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;
import static com.aestasit.markdown.Resources.file;

import java.io.IOException;

import org.junit.Test;

public class DZSlidesConverterTest {

  @Test
  public void testConversion() throws IOException {

    // Configure converter.
    Configuration config = new SimpleConfiguration();
    config.outputFile(file("tmp/presentation.html"));
    config.staticFile(classpath("AESTAS_SITE_TRAINING.png"));
    config.staticFile(classpath("BETTER_DEVELOPER_TRACK.png"));
    config.staticFile(classpath("AESTAS_TRAINING.png"));
    config.encoding("UTF-8");
    
    // Render.
    new DZSlidesConverter().render(config);
    
  }

}
