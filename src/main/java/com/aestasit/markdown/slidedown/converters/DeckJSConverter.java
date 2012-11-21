package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;

import java.util.HashMap;

import org.jsoup.nodes.Document;

public class DeckJSConverter extends TextTemplateConverter {

  @Override
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

    config.staticFile("themes/style", classpath("deck.js/themes/style/web-2.0.css"));
    config.staticFile("themes/transition", classpath("deck.js/themes/transition/horizontal-slide.css"));

  }

  private void addExtension(Configuration config, String extension) {
    String basePath = "deck.js/extensions/" + extension + "/deck." + extension;
    config.staticFile("extensions/" + extension, classpath(basePath + ".css"));
    config.staticFile("extensions/" + extension, classpath(basePath + ".js"));
  }

  @Override
  protected void expandBinding(HashMap<String, Object> binding, Document slidesDocument, Configuration config) {
  }

  @Override
  protected void transformDocument(Document slidesDocument) {
  }

}
