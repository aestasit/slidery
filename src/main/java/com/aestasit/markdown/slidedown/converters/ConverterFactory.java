package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;
import java.util.Properties;

public final class ConverterFactory {

  public static Converter createConverter(String id) {
    return doCreateConverter(getConverterClassName(id));
  }

  private static Converter doCreateConverter(String converterClassName) {
    try {
      Class<?> converterClass = Class.forName(converterClassName);
      Object converter = converterClass.newInstance();
      if (!(converter instanceof Converter)) {
        converter = null;
      }
      return (Converter) converter;
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } catch (InstantiationException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  private static String getConverterClassName(String id) {
    try {
      Properties properties = new Properties();
      properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/slidedown-converters/" + id + ".properties"));
      String converterClassName = properties.getProperty("converter-class");
      return converterClassName;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
