package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;

import org.junit.Test;

/**
 * @author Andrey Adamovich
 *
 */
public class DZSlidesConverterTest extends BaseConverterTest {

  @Test
  public void testDirectConversion() throws IOException {
    new DZSlidesConverter().render(createConfiguration());
  }

  @Test
  public void testFactoryConversion() throws IOException {
    ConverterFactory.createConverter(DZSlidesConverter.CONVERTER_ID).render(createConfiguration());
  }

}
