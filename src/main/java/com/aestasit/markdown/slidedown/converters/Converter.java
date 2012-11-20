package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;

public interface Converter {

  public abstract void render(Configuration config) throws IOException;

}