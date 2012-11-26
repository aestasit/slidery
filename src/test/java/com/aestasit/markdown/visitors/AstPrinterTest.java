package com.aestasit.markdown.visitors;

import static com.aestasit.markdown.Markdown.printAst;
import static com.aestasit.markdown.Markdown.toAst;
import static com.aestasit.markdown.slidery.Slidery.DEFAULT_PEGDOWN_OPTIONS;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.aestasit.markdown.BaseTest;

/**
 * @author Andrey Adamovich
 * 
 */
public class AstPrinterTest extends BaseTest {

  @Test
  public void testAstPrinter() throws IOException {
    String astString = printAst(toAst(allTestData(), DEFAULT_PEGDOWN_OPTIONS));
    assertTrue(!isBlank(astString));
  }

}
