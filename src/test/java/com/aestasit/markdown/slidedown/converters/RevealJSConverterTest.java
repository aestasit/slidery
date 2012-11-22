package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;

import org.junit.Test;

public class RevealJSConverterTest extends BaseConverterTest {

  @Test
  public void testDirectConversion() throws IOException {
    new RevealJSConverter().render(createConfiguration());
  }

  @Test
  public void testFactoryConversion() throws IOException {
    ConverterFactory.createConverter("reveal-js-base").render(createConfiguration());
  }

}
