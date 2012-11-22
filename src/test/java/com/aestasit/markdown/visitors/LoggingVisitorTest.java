package com.aestasit.markdown.visitors;

import java.io.IOException;

import org.junit.Test;

import com.aestasit.markdown.BaseTest;
import com.aestasit.markdown.Markdown;
import com.aestasit.markdown.slidedown.Slidedown;

/**
 * @author Andrey Adamovich
 *
 */
public class LoggingVisitorTest extends BaseTest {

  @Test
  public void test() throws IOException {
    new LoggingVisitor().visit(Markdown.toAst(allTestData(), Slidedown.DEFAULT_PEGDOWN_OPTIONS));
    // TODO: add a way to test it works
  }

}
