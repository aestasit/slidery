package com.aestasit.markdown;

import java.io.PrintStream;

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
import org.pegdown.ast.Visitor;
import org.pegdown.ast.WikiLinkNode;

/**
 * Mark-down text extractor.
 * 
 */
public class TextPrinter extends BaseVisitor implements Visitor {

  private final PrintStream printer;

  public TextPrinter(PrintStream printer) {
    super();
    this.printer = printer;
  }

  public void visit(AutoLinkNode node) {
    logNode(node);
    printer.print(node.getText());
    visitChildren(node);
  }

  public void visit(TextNode node) {
    logNode(node);
    printer.print(node.getText());
    visitChildren(node);
  }

  public void visit(SpecialTextNode node) {
    logNode(node);
    printer.print(node.getText());
    visitChildren(node);
  }

  public void visit(AbbreviationNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(BlockQuoteNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(BulletListNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(CodeNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(DefinitionListNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(DefinitionNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(DefinitionTermNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(EmphNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(ExpImageNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(ExpLinkNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(HeaderNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(HtmlBlockNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(InlineHtmlNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(ListItemNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(MailLinkNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(OrderedListNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(ParaNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(QuotedNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(ReferenceNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(RefImageNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(RefLinkNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(RootNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(SimpleNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(StrongNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(TableBodyNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(TableCellNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(TableColumnNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(TableHeaderNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(TableNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(TableRowNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(VerbatimNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(WikiLinkNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(SuperNode node) {
    logNode(node);
    visitChildren(node);
  }

  public void visit(Node node) {
    logNode(node);
    visitChildren(node);
  }

}
