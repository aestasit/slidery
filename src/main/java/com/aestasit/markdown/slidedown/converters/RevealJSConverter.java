package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;

import java.util.HashMap;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class RevealJSConverter extends TextTemplateConverter {

  @Override
  protected void beforeStart(Configuration config) {
    
    config.templateFile(classpath("reveal.js/index.html"));
    
    // CSS
    config.staticFile("css", classpath("reveal.js/css/reveal.css"));
    config.staticFile("css/print", classpath("reveal.js/css/print/paper.css"));
    config.staticFile("css/print", classpath("reveal.js/css/print/pdf.css"));    
    config.staticFile("css/theme", classpath("reveal.js/css/theme/simple.css"));
    config.staticFile("lib/css", classpath("reveal.js/lib/css/zenburn.css"));
    
    // JavaScript
    config.staticFile("js", classpath("reveal.js/js/reveal.min.js"));
    config.staticFile("lib/js", classpath("reveal.js/lib/js/classList.js"));
    config.staticFile("lib/js", classpath("reveal.js/lib/js/head.min.js"));
    config.staticFile("lib/js", classpath("reveal.js/lib/js/html5shiv.js"));
    config.staticFile("plugin/highlight", classpath("reveal.js/plugin/highlight/highlight.js"));
    config.staticFile("plugin/zoom-js", classpath("reveal.js/plugin/zoom-js/zoom.js"));    
    config.staticFile("plugin/notes", classpath("reveal.js/plugin/notes/notes.js"));
    config.staticFile("plugin/notes", classpath("reveal.js/plugin/notes/notes.html"));
    
  }

  @Override
  protected void expandBinding(HashMap<String, Object> binding, Document slidesDocument, Configuration config) {
  }

  @Override
  protected void transformDocument(Document slidesDocument) {
    for (Element list : slidesDocument.select("aside")) {
      list.addClass("notes");
    }

  }

}
