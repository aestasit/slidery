package com.aestasit.markdown;


import org.apache.commons.lang3.StringUtils;
import org.pegdown.ast.Node;
import org.pegdown.ast.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
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
      child.accept(this);
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
