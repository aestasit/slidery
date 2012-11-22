package com.aestasit.markdown.slidedown;

import static com.aestasit.markdown.Resources.classpath;
import static com.aestasit.markdown.slidedown.Slidedown.toSlides;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.junit.Assert;
import org.junit.Test;

import com.aestasit.markdown.BaseTest;

public class SlidedownTest extends BaseTest {

  @Test
  public void testLoadingMethods() throws IOException {
    // from InputStream
    validateSlides(toSlides(testData("01_simple_slides.md")));
    // from File
    validateSlides(toSlides(classpath("01_simple_slides.md")));
    // from URL
    validateSlides(toSlides(classpath("01_simple_slides.md").toURI().toURL()));
    // from String 
    validateSlides(toSlides(IOUtils.toString(testData("01_simple_slides.md"))));    
  }

  protected void validateSlides(String slides) {
    log.debug("Validating slides: " + slides);
    Assert.assertTrue("Output contains disallowed tags",
        Jsoup.isValid(slides, Whitelist.relaxed().addTags("section", "aside", "div", "figure", "figcaption", "header")));
  }

}
