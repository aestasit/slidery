package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;

import org.junit.Test;

/**
 * @author Andrey Adamovich
 *
 */
public class DeckJSConverterTest extends BaseConverterTest {

  @Test
  public void testDirectConversion() throws IOException {
    new DeckJSConverter().render(createConfiguration());
  }

  @Test
  public void testFactoryConversion() throws IOException {
    ConverterFactory.createConverter(DeckJSConverter.CONVERTER_ID).render(createConfiguration());
  }

}
