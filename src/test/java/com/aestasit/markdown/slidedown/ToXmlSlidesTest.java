package com.aestasit.markdown.slidedown;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.pegdown.ast.RootNode;

import com.aestasit.markdown.Markdown;

public class ToXmlSlidesTest {

  @Test
  public void test() throws IOException {

    String slides = Slidedown.toSlides(getTestDocument("test_slides.md"));
    System.out.println(slides);

  }

  private RootNode getTestDocument(String fileName) throws IOException {
    return Markdown.toAst(getTestData(fileName));
  }

  private InputStream getTestData(String fileName) throws IOException {
    return getClass().getClassLoader().getResourceAsStream(fileName);
  }

}
