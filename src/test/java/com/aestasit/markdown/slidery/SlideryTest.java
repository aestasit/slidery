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

package com.aestasit.markdown.slidery;

import static com.aestasit.markdown.Resources.classpath;
import static com.aestasit.markdown.slidery.Slidery.toSlides;
import static org.jsoup.Jsoup.isValid;
import static org.jsoup.safety.Whitelist.relaxed;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.aestasit.markdown.BaseTest;

/**
 * @author Andrey Adamovich
 *
 */
public class SlideryTest extends BaseTest {

  @Test
  public void testLoadingMethods() throws IOException {
    // from InputStream
    validateSlides(toSlides(allTestData()));
    // from File
    validateSlides(toSlides(allTestData()));
    // from URL
    validateSlides(toSlides(classpath("01_simple_slides.md").toURI().toURL()));
    // from String 
    validateSlides(toSlides(IOUtils.toString(allTestData())));    
  }

  protected void validateSlides(String slides) {
    log.debug("Validating slides: " + slides);
    assertTrue("Output contains disallowed tags",
        isValid( slides
                   .replaceAll("src=\"", "src=\"http://")
                   .replaceAll("href=\"", "href=\"http://"), 
                 relaxed()
                   .addTags("section", "aside", "div", "figure", "figcaption", "header", "abbr")
                   .addAttributes("code", "class")
                   .addAttributes("abbr", "title")
                   .addAttributes("td", "abbr", "axis", "colspan", "rowspan", "width", "align")
                   .addAttributes("th", "abbr", "axis", "colspan", "rowspan", "scope", "width", "align")
            ));
  }

}
