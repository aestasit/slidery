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

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.aestasit.markdown.slidery.configuration.Configuration;

/**
 * Presentation converter that is based on <a href="http://paulrouget.com/dzslides/">DZSlides</a> framework.
 * 
 * @author Andrey Adamovich
 * 
 */
public class DZSlidesConverter extends TextTemplateConverter {

  public static final String CONVERTER_ID = "dzslides";

  protected void beforeStart(final Configuration config) {
    config.templateFile(classpath("dzslides/template.html"));
    config.staticFile(classpath("dzslides/onstage.html"));
    config.staticFile(classpath("dzslides/embedder.html"));
  }

  protected void transformDocument(final Document slidesDocument, final Configuration config) {
    super.transformDocument(slidesDocument, config);
    if (config.listsIncremented()) {
      for (final Element list : slidesDocument.select("div ul, div ol")) {
        list.addClass("incremental");
      }
    }
  }

  public String getId() {
    return CONVERTER_ID;
  }

  public String getDescription() {
    return "http://paulrouget.com/dzslides/";
  }

}
