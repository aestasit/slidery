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

import java.io.PrintStream;

import org.apache.commons.lang3.StringUtils;
import org.pegdown.ast.AbbreviationNode;
import org.pegdown.ast.AutoLinkNode;
import org.pegdown.ast.BlockQuoteNode;
import org.pegdown.ast.BulletListNode;
import org.pegdown.ast.CodeNode;
import org.pegdown.ast.DefinitionListNode;
import org.pegdown.ast.DefinitionNode;
import org.pegdown.ast.DefinitionTermNode;
import org.pegdown.ast.EmphNode;
import org.pegdown.ast.ExpImageNode;
import org.pegdown.ast.ExpLinkNode;
import org.pegdown.ast.HeaderNode;
import org.pegdown.ast.HtmlBlockNode;
import org.pegdown.ast.InlineHtmlNode;
import org.pegdown.ast.ListItemNode;
import org.pegdown.ast.MailLinkNode;
import org.pegdown.ast.Node;
import org.pegdown.ast.OrderedListNode;
import org.pegdown.ast.ParaNode;
import org.pegdown.ast.QuotedNode;
import org.pegdown.ast.RefImageNode;
import org.pegdown.ast.RefLinkNode;
import org.pegdown.ast.ReferenceNode;
import org.pegdown.ast.RootNode;
import org.pegdown.ast.SimpleNode;
import org.pegdown.ast.SpecialTextNode;
import org.pegdown.ast.StrongNode;
import org.pegdown.ast.SuperNode;
import org.pegdown.ast.TableBodyNode;
import org.pegdown.ast.TableCellNode;
import org.pegdown.ast.TableColumnNode;
import org.pegdown.ast.TableHeaderNode;
import org.pegdown.ast.TableNode;
import org.pegdown.ast.TableRowNode;
import org.pegdown.ast.TextNode;
import org.pegdown.ast.VerbatimNode;
import org.pegdown.ast.WikiLinkNode;

import com.google.common.base.Preconditions;

/**
 * 
 * Visitor that prints AST to the given {@link PrintStream}. 
 * 
 * @author Andrey Adamovich
 *
 */
public class AstPrinter extends BaseVisitor {

  private final PrintStream printer;

  public AstPrinter(PrintStream printer) {
    super();
    Preconditions.checkNotNull(printer, "Printer is not given!");
    this.printer = printer;
  }

  protected void printNode(Node node) {
    printer.print(StringUtils.repeat(" ", level) + node.toString());
  }

  public void visit(AbbreviationNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(AutoLinkNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(BlockQuoteNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(BulletListNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(CodeNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(DefinitionListNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(DefinitionNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(DefinitionTermNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(EmphNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(ExpImageNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(ExpLinkNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(HeaderNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(HtmlBlockNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(InlineHtmlNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(ListItemNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(MailLinkNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(OrderedListNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(ParaNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(QuotedNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(ReferenceNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(RefImageNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(RefLinkNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(RootNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(SimpleNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(SpecialTextNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(StrongNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(TableBodyNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(TableCellNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(TableColumnNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(TableHeaderNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(TableNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(TableRowNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(VerbatimNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(WikiLinkNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(TextNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(SuperNode node) {
    printNode(node);
    visitChildren(node);
  }

  public void visit(Node node) {
    printNode(node);
    visitChildren(node);
  }

}
