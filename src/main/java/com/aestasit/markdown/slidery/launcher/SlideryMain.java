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

package com.aestasit.markdown.slidery.launcher;

import java.io.IOException;

import com.aestasit.markdown.slidery.configuration.Configuration;
import com.aestasit.markdown.slidery.configuration.SimpleConfiguration;
import com.aestasit.markdown.slidery.converters.ConverterFactory;
import com.lexicalscope.jewel.cli.CliFactory;

/**
 * Command-line launcher for Slidery.
 * 
 * @author Andrey Adamovich
 * 
 */
public final class SlideryMain {

  public static void main(final String[] args) {
    final Configuration config = new SimpleConfiguration();
    try {
      final SlideryOptions options = CliFactory.parseArguments(SlideryOptions.class, args);
      config.configuration(options);
      ConverterFactory.createConverter(options.getFormat()).render(config);
    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

}
