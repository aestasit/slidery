package com.aestasit.markdown.slidery.converters;

import java.io.IOException;

import org.junit.Test;

import com.aestasit.markdown.slidery.converters.ConverterFactory;
import com.aestasit.markdown.slidery.converters.ImpressJSConverter;

/**
 * @author Andrey Adamovich
 *
 */
public class ImpressJSConverterTest extends BaseConverterTest {

  @Test
  public void testDirectConversion() throws IOException {
    new ImpressJSConverter().render(createConfiguration());
  }

  @Test
  public void testFactoryConversion() throws IOException {
    ConverterFactory.createConverter(ImpressJSConverter.CONVERTER_ID).render(createConfiguration());
  }

}
