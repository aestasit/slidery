package com.aestasit.markdown.slidedown;

import java.io.IOException;
import java.io.InputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.pegdown.ast.RootNode;

import com.aestasit.markdown.MarkdownUtils;

public class ToHtmlSlidesTest {

  @Test
  public void test() throws IOException {

    String slides = Slidedown.toSlides(getTestDocument("test_slides.md"));
    System.out.println(slides);

    Document document = Jsoup.parse(slides);
    
    
    
  }

  private RootNode getTestDocument(String fileName) throws IOException {
    return MarkdownUtils.parse(getTestData(fileName));
  }

  private InputStream getTestData(String fileName) throws IOException {
    return getClass().getClassLoader().getResourceAsStream(fileName);
  }

}
