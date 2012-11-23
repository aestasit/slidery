package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;

import org.junit.Test;

/**
 * @author Andrey Adamovich
 *
 */
public class ShowerConverterTest extends BaseConverterTest {

  @Test
  public void testDirectConversion() throws IOException {
    new ShowerConverter().render(createConfiguration());
  }

  @Test
  public void testFactoryConversion() throws IOException {
    ConverterFactory.createConverter(ShowerConverter.CONVERTER_ID).render(createConfiguration());
  }

}
