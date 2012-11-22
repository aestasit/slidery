package com.aestasit.markdown;

import java.io.IOException;

import org.junit.Assert;

import org.junit.Test;
import org.pegdown.ast.RootNode;

public class MarkdownTest extends BaseTest {

  @Test
  public void testLoadingMethods() throws IOException {
    RootNode rootNode = Markdown.toAst(testData("01_simple_slides.md"));
    Assert.assertNotNull(rootNode);
    
    // TODO: extend method coverage    
  }

}
