package com.aestasit.markdown.visitors;

import static com.aestasit.markdown.Markdown.extractText;
import static com.aestasit.markdown.Markdown.toAst;
import static com.aestasit.markdown.slidedown.Slidedown.DEFAULT_PEGDOWN_OPTIONS;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.aestasit.markdown.BaseTest;

/**
 * @author Andrey Adamovich
 *
 */
public class TextExtractorTest extends BaseTest {

  @Test
  public void testExtraction() throws IOException {
    String text = extractText(toAst(allTestData(), DEFAULT_PEGDOWN_OPTIONS));
    Assert.assertFalse(isBlank(text));
    Assert.assertFalse(StringUtils.contains(text, "#"));
  }

}
