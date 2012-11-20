package com.aestasit.markdown;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class MarkdownTest extends BaseTest {

  @Test
  public void testLoadingMethods() throws IOException {
    
    InputStream inputStream = getTestData("test_slides.md");
    
    Markdown.toAst(inputStream);
    
    
  }

}
