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
