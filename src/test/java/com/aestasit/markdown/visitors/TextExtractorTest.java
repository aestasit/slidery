package com.aestasit.markdown.visitors;

import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.StringUtils;
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
public class TextExtractorTest extends BaseTest {

  @Test
  public void testExtraction() throws IOException {
    ByteArrayOutputStream data = new ByteArrayOutputStream();
    RootNode inputAst = Markdown.toAst(allTestData(), Slidedown.DEFAULT_PEGDOWN_OPTIONS);
    new TextExtractor(new PrintStream(data)).visit(inputAst);
    String text = new String(data.toByteArray());
    Assert.assertFalse(StringUtils.isEmpty(text.trim()));
    // TODO: test that text is correct
  }

}
