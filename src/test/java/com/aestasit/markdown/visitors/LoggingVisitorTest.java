package com.aestasit.markdown.visitors;

import static com.aestasit.markdown.Markdown.logAst;
import static com.aestasit.markdown.Markdown.toAst;
import static com.aestasit.markdown.slidery.Slidery.DEFAULT_PEGDOWN_OPTIONS;

import java.io.IOException;

import org.junit.Test;

import com.aestasit.markdown.BaseTest;

/**
 * @author Andrey Adamovich
 *
 */
public class LoggingVisitorTest extends BaseTest {

  @Test
  public void test() throws IOException {
    logAst(toAst(allTestData(), DEFAULT_PEGDOWN_OPTIONS));
  }

}
