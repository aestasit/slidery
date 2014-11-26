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

package com.aestasit.markdown;

import java.io.IOException;

import org.junit.Assert;

import org.junit.Test;
import org.pegdown.ast.RootNode;

/**
 * @author Andrey Adamovich
 *
 */
public class MarkdownTest extends BaseTest {

  @Test
  public void testLoadingMethods() throws IOException {
    RootNode rootNode = Markdown.toAst(allTestData());
    Assert.assertNotNull(rootNode);
    
    // TODO: extend method coverage    
  }

}
