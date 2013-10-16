/*
 * Copyright (C) 2011-2013 Aestas/IT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
