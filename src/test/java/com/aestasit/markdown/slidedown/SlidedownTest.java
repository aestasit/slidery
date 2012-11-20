package com.aestasit.markdown.slidedown;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.aestasit.markdown.BaseTest;

public class SlidedownTest extends BaseTest {

  @Test
  public void testLoadingMethods() throws IOException {
    InputStream inputStream = getTestData("test_slides.md");
    String slides = Slidedown.toSlides(inputStream);
    System.out.println(slides);
    // TODO: extend method coverage    
  }

}
