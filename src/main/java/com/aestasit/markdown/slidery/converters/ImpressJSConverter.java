package com.aestasit.markdown.slidery.converters;

import static com.aestasit.markdown.Resources.classpath;

import com.aestasit.markdown.slidery.configuration.Configuration;

/**
 * Presentation converter that is based on <a href="http://bartaz.github.com/impress.js">impress.js</a> framework.
 * 
 * @author Andrey Adamovich
 * 
 */
public class ImpressJSConverter extends TextTemplateConverter {

  public static final String CONVERTER_ID = "impress-js";

  protected void beforeStart(final Configuration config) {
    config.templateFile(classpath("impress.js/index.html"));
    config.staticFile("css", classpath("impress.js/css/impress-demo.css"));
    config.staticFile("js", classpath("impress.js/js/impress.js"));
  }

  public String getId() {
    return CONVERTER_ID;
  }

  public String getDescription() {
    return "http://bartaz.github.com/impress.js";
  }

}
