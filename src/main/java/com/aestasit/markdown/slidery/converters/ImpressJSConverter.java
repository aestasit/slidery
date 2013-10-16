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
