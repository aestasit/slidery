package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;

import org.junit.Test;

public class ImpressJSConverterTest extends BaseConverterTest {

  @Test
  public void testDirectConversion() throws IOException {
    new ImpressJSConverter().render(createConfiguration());
  }

  @Test
  public void testFactoryConversion() throws IOException {
    ConverterFactory.createConverter("impress-js-base").render(createConfiguration());
  }

}
