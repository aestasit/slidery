package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DZSlidesConverter extends TextTemplateConverter {

  @Override
  protected void beforeStart(Configuration config) {
    config.templateFile(classpath("dzslides/template.html"));
  }

  @Override
  protected void expandBinding(HashMap<String, Object> binding, Document slidesDocument, Configuration config) {
  }

  @Override
  protected void transformDocument(Document slidesDocument) {
    for (Element list : slidesDocument.select("div ul, div ol")) {
      list.addClass("incremental");
    }
  }

}
