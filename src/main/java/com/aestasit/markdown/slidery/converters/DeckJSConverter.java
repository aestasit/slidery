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
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.aestasit.markdown.slidery.configuration.Configuration;
import com.aestasit.markdown.slidery.configuration.ConfigurationBuilder;

/**
 * Presentation converter that is based on <a href="http://imakewebthings.com/deck.js/">deck.js</a> framework.
 * 
 * @author Andrey Adamovich
 * 
 */
public class DeckJSConverter extends TextTemplateConverter {

  public static final String CONVERTER_ID = "deck-js";

  protected void beforeStart(final Configuration config) {

    config.templateFile(classpath("deck.js/boilerplate.html"));

    config.staticFile(classpath("deck.js/jquery-1.7.2.min.js"));
    config.staticFile(classpath("deck.js/modernizr.custom.js"));

    config.staticFile("core", classpath("deck.js/core/deck.core.css"));
    config.staticFile("core", classpath("deck.js/core/deck.core.js"));

    addExtension(config, "status");
    addExtension(config, "scale");
    addExtension(config, "navigation");
    addExtension(config, "hash");
    addExtension(config, "goto");
    addExtension(config, "menu");

    if (config.getLogo() != null) {
      addCssExtension(config, "logo");
    }
    addCssExtension(config, "title");
    addCssExtension(config, "highlight");

    if (isBlank(config.getTheme())) {
      config.theme("web-2.0");
    }

    config.staticFile("themes/style", classpath("deck.js/themes/style/" + config.getTheme() + ".css"));
    config.staticFile("themes/transition", classpath("deck.js/themes/transition/horizontal-slide.css"));

  }

  private void addExtension(final ConfigurationBuilder config, final String extension) {
    final String basePath = baseExtensionPath(extension);
    config.staticFile("extensions/" + extension, classpath(basePath + ".css"));
    config.staticFile("extensions/" + extension, classpath(basePath + ".js"));
  }

  private String baseExtensionPath(final String extension) {
    return "deck.js/extensions/" + extension + "/deck." + extension;
  }

  private void addCssExtension(final ConfigurationBuilder config, final String extension) {
    final String basePath = baseExtensionPath(extension);
    config.staticFile("extensions/" + extension, classpath(basePath + ".css"));
  }

  protected void transformDocument(final Document slidesDocument, final Configuration config) {
    super.transformDocument(slidesDocument, config);
    if (config.listsIncremented()) {
      for (final Element list : slidesDocument.select("div li")) {
        list.addClass("slide");
      }
    }
  }

  public String getId() {
    return CONVERTER_ID;
  }

  public String getDescription() {
    return "http://imakewebthings.com/deck.js/";
  }

}
