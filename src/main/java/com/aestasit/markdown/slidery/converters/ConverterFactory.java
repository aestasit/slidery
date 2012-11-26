package com.aestasit.markdown.slidery.converters;

import java.io.IOException;
import java.util.Properties;

/**
 * Converter factory that relies on <code>*.properties</code> files located 
 * on the class path in <code>META-INF/slidery-converters</code> directory.
 * 
 * @author Andrey Adamovich
 *
 */
public final class ConverterFactory {

  /**
   * Lookups <code>&lt;id&gt;.properties</pre></code> under <code>META-INF/slidery-converters</code> directory 
   * in the class path and based on <code>converter-class</code> property value creates {@link Converter} instance.  
   * 
   * @param id converter identifier.
   * @return converter instance.
   */
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
      properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF/slidery-converters/" + id + ".properties"));
      String converterClassName = properties.getProperty("converter-class");
      return converterClassName;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
