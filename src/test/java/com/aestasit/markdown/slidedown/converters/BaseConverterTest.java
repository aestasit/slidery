package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;
import static com.aestasit.markdown.Resources.file;

public class BaseConverterTest {

  protected Configuration createConfiguration() {
    // Configure converter.
    Configuration config = new SimpleConfiguration();
    config.inputFile(classpath("test_slides.md"))
        .outputFile(file("tmp/presentation.html"))
        .staticFile(classpath("AESTAS_SITE_TRAINING.png"))
        .staticFile(classpath("BETTER_DEVELOPER_TRACK.png"))
        .staticFile(classpath("AESTAS_TRAINING.png"))
        .encoding("UTF-8");
    return config;
  }

}
