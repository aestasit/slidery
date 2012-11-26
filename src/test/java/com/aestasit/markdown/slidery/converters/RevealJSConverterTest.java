package com.aestasit.markdown.slidery.converters;

import java.io.IOException;

import org.junit.Test;

import com.aestasit.markdown.slidery.converters.ConverterFactory;
import com.aestasit.markdown.slidery.converters.RevealJSConverter;

/**
 * @author Andrey Adamovich
 *
 */
public class RevealJSConverterTest extends BaseConverterTest {

  @Test
  public void testDirectConversion() throws IOException {
    new RevealJSConverter().render(createConfiguration());
  }

  @Test
  public void testFactoryConversion() throws IOException {
    ConverterFactory.createConverter(RevealJSConverter.CONVERTER_ID).render(createConfiguration());
  }

}
