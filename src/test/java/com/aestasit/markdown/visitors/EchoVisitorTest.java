package com.aestasit.markdown.visitors;

import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Assert;
import org.junit.Test;
import org.pegdown.ast.RootNode;

import com.aestasit.markdown.BaseTest;
import com.aestasit.markdown.Markdown;

public class EchoVisitorTest extends BaseTest {

  @Test
  public void testEcho() throws IOException {
    ByteArrayOutputStream data = new ByteArrayOutputStream();
    RootNode inputAst = Markdown.toAst(testData("01_simple_slides.md"));
    new EchoVisitor(new PrintStream(data)).visit(inputAst);
    RootNode echoedAst = Markdown.toAst(new String(data.toByteArray()));
    Assert.assertNotNull(echoedAst);
    // TODO: Compare AST trees
  }

}
