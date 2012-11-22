package com.aestasit.markdown.visitors;

import java.io.IOException;

import org.junit.Test;

import com.aestasit.markdown.BaseTest;
import com.aestasit.markdown.Markdown;

public class LoggingVisitorTest extends BaseTest {

  @Test
  public void test() throws IOException {
    new LoggingVisitor().visit(Markdown.toAst(testData("01_simple_slides.md")));
    // TODO: add a way to test it works
  }

}
