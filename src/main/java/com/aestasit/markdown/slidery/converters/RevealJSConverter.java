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

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.aestasit.markdown.slidery.configuration.Configuration;

/**
 * Presentation converter that is based on <a href="http://lab.hakim.se/reveal-js/">reveal.js</a> framework.
 * 
 * @author Andrey Adamovich
 * 
 */
public class RevealJSConverter extends TextTemplateConverter {

  public static final String CONVERTER_ID = "reveal-js";

  protected void beforeStart(final Configuration config) {

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

  protected void transformDocument(final Document slidesDocument, final Configuration config) {
    super.transformDocument(slidesDocument, config);
    if (config.notesIncluded()) {
      for (final Element notesElement : slidesDocument.select("aside")) {
        notesElement.addClass("notes");
      }
    }
    if (config.listsIncremented()) {
      for (final Element list : slidesDocument.select("div li")) {
        list.addClass("fragment").addClass("roll-in");
      }
    }
  }

  public String getId() {
    return CONVERTER_ID;
  }

  public String getDescription() {
    return "http://lab.hakim.se/reveal-js/";
  }

}
