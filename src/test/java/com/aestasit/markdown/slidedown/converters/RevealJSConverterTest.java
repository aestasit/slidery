package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;

import org.junit.Test;

public class RevealJSConverterTest extends BaseConverterTest {

  @Test
  public void testConversion() throws IOException {
    new RevealJSConverter().render(createConfiguration());
  }

}
