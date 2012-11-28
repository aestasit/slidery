package com.aestasit.markdown.slidery.converters;

import static com.aestasit.markdown.Resources.classpath;
import static org.apache.commons.lang3.StringUtils.isBlank;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Presentation converter that is based on <a
 * href="http://imakewebthings.com/deck.js/">deck.js</a> framework.
 * 
 * @author Andrey Adamovich
 * 
 */
public class DeckJSConverter extends TextTemplateConverter {

  public static final String CONVERTER_ID = "deck-js";

  protected void beforeStart(Configuration config) {

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

    if (isBlank(config.getTheme())) {
      config.theme("web-2.0");
    }
    
    config.staticFile("themes/style", classpath("deck.js/themes/style/" + config.getTheme() + ".css"));
    config.staticFile("themes/transition", classpath("deck.js/themes/transition/horizontal-slide.css"));

  }

  private void addExtension(Configuration config, String extension) {
    String basePath = baseExtensionPath(extension);
    config.staticFile("extensions/" + extension, classpath(basePath + ".css"));
    config.staticFile("extensions/" + extension, classpath(basePath + ".js"));
  }

  private String baseExtensionPath(String extension) {
    return "deck.js/extensions/" + extension + "/deck." + extension;
  }

  private void addCssExtension(Configuration config, String extension) {
    String basePath = baseExtensionPath(extension);
    config.staticFile("extensions/" + extension, classpath(basePath + ".css"));
  }

  protected void transformDocument(Document slidesDocument, Configuration config) {
    super.transformDocument(slidesDocument, config);
    if (config.listsIncremented()) {
      for (Element list : slidesDocument.select("div li")) {
        list.addClass("slide");
      }
    }
  }

}
