package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;

import java.util.HashMap;

import org.jsoup.nodes.Document;

public class ImpressJSConverter extends TextTemplateConverter {

  @Override
  protected void beforeStart(Configuration config) {

    config.templateFile(classpath("impress.js/index.html"));

    config.staticFile("css", classpath("impress.js/css/impress-demo.css"));
    config.staticFile("js", classpath("impress.js/js/impress.js"));

  }

  @Override
  protected void expandBinding(HashMap<String, Object> binding, Document slidesDocument, Configuration config) {
  }

  @Override
  protected void transformDocument(Document slidesDocument) {
  }

}
