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
