package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Presentation converter that is based on <a href="http://paulrouget.com/dzslides/">DZSlides</a> framework.
 * 
 * @author Andrey Adamovich
 *
 */
public class DZSlidesConverter extends TextTemplateConverter {

  public static final String CONVERTER_ID = "dzslides";

  protected void beforeStart(Configuration config) {
    config.templateFile(classpath("dzslides/template.html"));
    config.staticFile(classpath("dzslides/onstage.html"));
    config.staticFile(classpath("dzslides/embedder.html"));
  }

  protected void transformDocument(Document slidesDocument) {
    for (Element list : slidesDocument.select("div ul, div ol")) {
      list.addClass("incremental");
    }
  }

}
