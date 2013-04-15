package com.aestasit.markdown.slidery.converters;

import java.io.IOException;
import java.util.Collection;

import com.aestasit.markdown.slidery.configuration.Configuration;
import com.aestasit.markdown.slidery.configuration.Location;

/**
 * Converter interface.
 * 
 * @author Andrey Adamovich
 * 
 */
public interface Converter {

  void render(Configuration config) throws IOException;

  String getId();

  String getDescription();

  Collection<String> getTransitionIds();

  String getDefaultTransitionId();

  Collection<String> getThemeIds();

  String getDefaultThemeId();

  Collection<Location> getLocations();

}