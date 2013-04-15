package com.aestasit.markdown.slidery.launcher;

import org.junit.Test;

import com.aestasit.markdown.BaseTest;

/**
 * Slidery launcher test.
 * 
 * @author Andrey Adamovich
 * 
 */
public class SlideryMainTest extends BaseTest {

  @Test
  public void testMain() throws Exception {
    SlideryMain.main(params("", "", ""));
  }

  private String[] params(final String... params) {
    return params;
  }

  private String[] paramString(final String params) {
    return params.split("\\s");
  }

}
