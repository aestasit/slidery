package com.aestasit.markdown.slidery.converters;

import static com.aestasit.markdown.Resources.classpath;

/**
 * Presentation converter that is based on <a href="https://github.com/pepelsbey/shower">shower</a> framework.
 * 
 * @author Andrey Adamovich
 * 
 */
public class ShowerConverter extends TextTemplateConverter {

  public static final String CONVERTER_ID = "shower";

  protected void beforeStart(Configuration config) {
    config.templateFile(classpath("shower/template.html"));
    config.staticFile("scripts", classpath("shower/scripts/script.js"));
    config.staticFile("themes/ribbon/images", classpath("shower/themes/ribbon/images/grid.png"));
    config.staticFile("themes/ribbon/images", classpath("shower/themes/ribbon/images/linen.png"));
    config.staticFile("themes/ribbon/images", classpath("shower/themes/ribbon/images/ribbon.svg"));
    config.staticFile("themes/ribbon/styles", classpath("shower/themes/ribbon/styles/print.css"));
    config.staticFile("themes/ribbon/styles", classpath("shower/themes/ribbon/styles/reset.css"));
    config.staticFile("themes/ribbon/styles", classpath("shower/themes/ribbon/styles/style.css"));
  }

}
