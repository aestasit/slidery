package com.aestasit.markdown.slidery.converters;

import static com.aestasit.markdown.Resources.file;

import java.io.IOException;

import org.junit.Test;

import com.aestasit.markdown.slidery.configuration.Configuration;
import com.aestasit.markdown.slidery.converters.ConverterFactory;
import com.aestasit.markdown.slidery.converters.PdfConverter;

/**
 * @author Andrey Adamovich
 * 
 */
public class PdfConverterTest extends BaseConverterTest {

  @Test
  public void testDirectConversion() throws IOException {
    new PdfConverter().render(createConfiguration());
  }

  @Test
  public void testFactoryConversion() throws IOException {
    ConverterFactory.createConverter(PdfConverter.CONVERTER_ID).render(createConfiguration());
  }

  @Override
  protected Configuration createConfiguration() {
    return super
        .createConfiguration()
        .outputFile(file("./tmp/presentation.pdf"));
  }

}
