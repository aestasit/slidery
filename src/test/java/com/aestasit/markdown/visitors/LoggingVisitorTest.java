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
