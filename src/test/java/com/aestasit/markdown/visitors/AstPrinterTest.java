/*
 * Copyright (C) 2011-2014 Aestas/IT
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
