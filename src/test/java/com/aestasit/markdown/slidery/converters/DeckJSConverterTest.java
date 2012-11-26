package com.aestasit.markdown.slidery.converters;

import java.io.IOException;

import org.junit.Test;

import com.aestasit.markdown.slidery.converters.ConverterFactory;
import com.aestasit.markdown.slidery.converters.DeckJSConverter;

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
