/*
 * Copyright (C) 2011-2013 Aestas/IT
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

import org.apache.commons.lang3.StringUtils;
import org.pegdown.ast.Node;
import org.pegdown.ast.RootNode;
import org.pegdown.ast.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Base visitor class.
 * 
 * @author Andrey Adamovich
 * 
 */
public abstract class BaseVisitor implements Visitor {

  protected int                 level = 0;
  protected final static Logger LOG   = LoggerFactory.getLogger(BaseVisitor.class);

  protected void visitChildren(final Node node) {
    level++;
    for (final Node child : node.getChildren()) {
      if (child instanceof RootNode) {
        visitChildren(child);
      } else {
        child.accept(this);
      }
    }
    level--;
  }

  protected void unknownNode(final Node node) {
    LOG.error(">>> UNKNOWN NODE: " + node);
  }

  protected void logNode(Node node) {
    LOG.debug(StringUtils.repeat(" ", level) + node.toString());
  }

}
