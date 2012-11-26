package com.aestasit.markdown.slidery.converters;

import java.io.IOException;

import org.junit.Test;

import com.aestasit.markdown.slidery.converters.ConverterFactory;
import com.aestasit.markdown.slidery.converters.ShowerConverter;

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
