package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;

import org.junit.Test;

public class DZSlidesConverterTest extends BaseConverterTest {

  @Test
  public void testDirectConversion() throws IOException {
    new DZSlidesConverter().render(createConfiguration());
  }

  @Test
  public void testFactoryConversion() throws IOException {
    ConverterFactory.createConverter("dzslides-base").render(createConfiguration());
  }

}
