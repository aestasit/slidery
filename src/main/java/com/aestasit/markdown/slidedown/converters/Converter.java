package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;

/**
 * Converter interface. 
 * 
 * @author Andrey Adamovich
 *
 */
public interface Converter {

  void render(Configuration config) throws IOException;

}