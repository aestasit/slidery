/*
 * Copyright (C) 2011-2014 Aestas/IT
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

import static com.aestasit.markdown.Resources.classpath;

import com.aestasit.markdown.slidery.configuration.Configuration;

/**
 * Presentation converter that is based on <a href="https://github.com/shower/shower">shower</a> framework.
 * 
 * @author Andrey Adamovich
 * 
 */
public class ShowerConverter extends TextTemplateConverter {

  public static final String CONVERTER_ID = "shower";

  protected void beforeStart(final Configuration config) {
    config.templateFile(classpath("shower/template.html"));
    config.staticFile("scripts", classpath("shower/scripts/script.js"));
    config.staticFile("themes/ribbon/images", classpath("shower/themes/ribbon/images/grid.png"));
    config.staticFile("themes/ribbon/images", classpath("shower/themes/ribbon/images/linen.png"));
    config.staticFile("themes/ribbon/images", classpath("shower/themes/ribbon/images/ribbon.svg"));
    config.staticFile("themes/ribbon/styles", classpath("shower/themes/ribbon/styles/print.css"));
    config.staticFile("themes/ribbon/styles", classpath("shower/themes/ribbon/styles/reset.css"));
    config.staticFile("themes/ribbon/styles", classpath("shower/themes/ribbon/styles/style.css"));
  }

  public String getId() {
    return CONVERTER_ID;
  }

  public String getDescription() {
    return "https://github.com/shower/shower";
  }

}
