package com.aestasit.markdown.visitors;

import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.junit.Assert;
import org.junit.Test;
import org.pegdown.ast.RootNode;

import com.aestasit.markdown.BaseTest;
import com.aestasit.markdown.Markdown;
import com.aestasit.markdown.slidedown.Slidedown;

/**
 * @author Andrey Adamovich
 * 
 */
public class EchoVisitorTest extends BaseTest {

  @Test
  public void testEcho() throws IOException {
    ByteArrayOutputStream data = new ByteArrayOutputStream();
    RootNode inputAst = Markdown.toAst(allTestData(), Slidedown.DEFAULT_PEGDOWN_OPTIONS);
    new EchoVisitor(new PrintStream(data)).visit(inputAst);
    RootNode echoedAst = Markdown.toAst(new String(data.toByteArray()), Slidedown.DEFAULT_PEGDOWN_OPTIONS);
    Assert.assertNotNull(echoedAst);
    // TODO: Compare AST trees
  }

}
