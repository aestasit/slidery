package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;

import org.junit.Test;

public class DeckJSConverterTest extends BaseConverterTest {

  @Test
  public void testConversion() throws IOException {
    new DeckJSConverter().render(createConfiguration());
  }

}
