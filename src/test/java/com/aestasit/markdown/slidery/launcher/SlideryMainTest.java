package com.aestasit.markdown.slidery.launcher;

import org.junit.Test;

import com.aestasit.markdown.BaseTest;

/**
 * @author Andrey Adamovich
 * 
 */
public class SlideryMainTest extends BaseTest {

  @Test
  public void testMain() throws Exception {
    SlideryMain.main(params("", "", ""));
    SlideryMain.main(paramString("--inputFile="));
  }

  private String[] params(final String... params) {
    return params;
  }

  private String[] paramString(final String params) {
    return params.split("\\s");
  }

}
