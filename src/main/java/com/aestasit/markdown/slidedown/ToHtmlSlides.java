package com.aestasit.markdown.slidedown;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.parboiled.common.StringUtils;
import org.pegdown.LinkRenderer;
import org.pegdown.Printer;
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

import com.aestasit.markdown.visitors.BaseVisitor;
import com.google.common.base.Preconditions;

import static com.aestasit.markdown.slidedown.ToHtmlSlides.SlideComponent.*;

public class ToHtmlSlides extends BaseVisitor implements Visitor {

  protected Printer                          printer       = new Printer();

  // Link output handling.
  protected final Map<String, ReferenceNode> references    = new HashMap<String, ReferenceNode>();
  protected final Map<String, String>        abbreviations = new HashMap<String, String>();
  protected final LinkRenderer               linkRenderer;

  // Table output handling.
  protected TableNode                        currentTableNode;
  protected int                              currentTableColumn;
  protected boolean                          inTableHeader;

  // Slide output handling.
  private boolean                            inFirstSlide;
  private SlideComponent                     currentSlideComponent;
  private boolean                            startOfContent;

  protected enum SlideComponent {
    NONE, CONTENT, NOTES
  }

  public ToHtmlSlides(LinkRenderer linkRenderer) {
    this.linkRenderer = linkRenderer;
  }

  public String toHtml(RootNode astRoot) {
    Preconditions.checkNotNull(astRoot, "astRoot");
    astRoot.accept(this);
    return printer.getString();
  }

  public void visit(RootNode node) {

    for (ReferenceNode refNode : node.getReferences()) {
      visitChildren(refNode);
      references.put(normalize(printer.getString()), refNode);
      printer.clear();
    }

    for (AbbreviationNode abbrNode : node.getAbbreviations()) {
      visitChildren(abbrNode);
      String abbr = printer.getString();
      printer.clear();
      abbrNode.getExpansion().accept(this);
      String expansion = printer.getString();
      abbreviations.put(abbr, expansion);
      printer.clear();
    }

    printer.print("<body>").indent(+2);
    {
      startSlide();
      inFirstSlide = true;
      {
        visitChildren(node);
      }
      endSlide();
    }
    printer.indent(-2).println().print("</body>");

  }

  public void visit(HeaderNode node) {
    if (node.getLevel() == 1) {
      endSlide();
      startSlide();
      printer.println();
      printTag(node, "header");
      startSlideContent();
    } else {
      printer.println();
      printTag(node, "h" + node.getLevel());
    }
  }

  private void startSlide() {
    if (!inFirstSlide) {
      printer.println().print("<section>").indent(+2);
    }
    inFirstSlide = false;
    currentSlideComponent = NONE;
  }

  private void startSlideContent() {
    printer.println().print("<div>").indent(+2).println();
    currentSlideComponent = CONTENT;
    startOfContent = true;
  }

  private void startSlideNotes() {
    printer.indent(-2).println().print("</div>");
    printer.println().print("<aside>").indent(+2).println();
    currentSlideComponent = NOTES;
    startOfContent = true;
  }

  private void endSlide() {
    if (currentSlideComponent == CONTENT) {
      printer.indent(-2).println().print("</div>");
    }
    if (currentSlideComponent == NOTES) {
      printer.indent(-2).println().print("</aside>");
    }
    currentSlideComponent = NONE;
    if (!inFirstSlide) {
      printer.indent(-2).println().print("</section>");
    }
  }

  public void visit(AbbreviationNode node) {
  }

  public void visit(AutoLinkNode node) {
    printLink(linkRenderer.render(node));
  }

  public void visit(BlockQuoteNode node) {
    printIndentedTag(node, "blockquote");
  }

  public void visit(BulletListNode node) {
    printIndentedTag(node, "ul");
  }

  public void visit(CodeNode node) {
    printTag(node, "code");
  }

  public void visit(DefinitionListNode node) {
    printIndentedTag(node, "dl");
  }

  public void visit(DefinitionNode node) {
    printTag(node, "dd");
  }

  public void visit(DefinitionTermNode node) {
    printTag(node, "dt");
  }

  public void visit(EmphNode node) {
    printTag(node, "em");
  }

  public void visit(ExpImageNode node) {
    printImageTag(node, node.url, node.title);
  }

  public void visit(ExpLinkNode node) {
    String text = printChildrenToString(node);
    printLink(linkRenderer.render(node, text));
  }

  public void visit(HtmlBlockNode node) {
    String text = node.getText();
    if (text.length() > 0)
      printer.println();
    printer.print(text);
  }

  public void visit(InlineHtmlNode node) {
    printer.print(node.getText());
  }

  public void visit(ListItemNode node) {
    printer.println();
    printTag(node, "li");
  }

  public void visit(MailLinkNode node) {
    printLink(linkRenderer.render(node));
  }

  public void visit(OrderedListNode node) {
    printIndentedTag(node, "ol");
  }

  public void visit(ParaNode node) {
    printTag(node, "p");
  }

  public void visit(QuotedNode node) {
    switch (node.getType()) {
      case DoubleAngle:
        printer.print("\"");
        visitChildren(node);
        printer.print("\"");
        break;
      case Double:
        printer.print("\"");
        visitChildren(node);
        printer.print("\"");
        break;
      case Single:
        printer.print("'");
        visitChildren(node);
        printer.print("'");
        break;
    }
  }

  public void visit(ReferenceNode node) {
    // reference nodes are not printed
  }

  public void visit(RefImageNode node) {
    String text = printChildrenToString(node);
    String key = node.referenceKey != null ? printChildrenToString(node.referenceKey) : text;
    ReferenceNode refNode = references.get(normalize(key));
    if (refNode == null) { // "fake" reference image link
      printer.print("![").print(text).print(']');
      if (node.separatorSpace != null) {
        printer.print(node.separatorSpace).print('[');
        if (node.referenceKey != null)
          printer.print(key);
        printer.print(']');
      }
    } else
      printImageTag(node, refNode.getUrl(), refNode.getTitle());
  }

  public void visit(RefLinkNode node) {
    String text = printChildrenToString(node);
    String key = node.referenceKey != null ? printChildrenToString(node.referenceKey) : text;
    ReferenceNode refNode = references.get(normalize(key));
    if (refNode == null) { // "fake" reference link
      printer.print('[').print(text).print(']');
      if (node.separatorSpace != null) {
        printer.print(node.separatorSpace).print('[');
        if (node.referenceKey != null)
          printer.print(key);
        printer.print(']');
      }
    } else
      printLink(linkRenderer.render(node, refNode.getUrl(), refNode.getTitle(), text));
  }

  public void visit(SimpleNode node) {
    switch (node.getType()) {
      case Apostrophe:
        printer.print("'");
        break;
      case Ellipsis:
        printer.print("&hellip;");
        break;
      case Emdash:
        printer.print("&mdash;");
        break;
      case Endash:
        printer.print("&ndash;");
        break;
      case HRule:
        startSlideNotes();
        break;
      case Linebreak:
        printer.print("<br/>");
        break;
      case Nbsp:
        printer.print("&nbsp;");
        break;
      default:
        throw new IllegalStateException();
    }
  }

  public void visit(StrongNode node) {
    printTag(node, "strong");
  }

  public void visit(TableBodyNode node) {
    printIndentedTag(node, "tbody");
  }

  public void visit(TableCellNode node) {
    String tag = inTableHeader ? "th" : "td";
    List<TableColumnNode> columns = currentTableNode.getColumns();
    TableColumnNode column = columns.get(Math.min(currentTableColumn, columns.size() - 1));

    printer.println().print('<').print(tag);
    column.accept(this);
    if (node.getColSpan() > 1)
      printer.print(" colspan=\"").print(Integer.toString(node.getColSpan())).print('"');
    printer.print('>');
    visitChildren(node);
    printer.print('<').print('/').print(tag).print('>');

    currentTableColumn += node.getColSpan();
  }

  public void visit(TableColumnNode node) {
    switch (node.getAlignment()) {
      case None:
        break;
      case Left:
        printer.print(" align=\"left\"");
        break;
      case Right:
        printer.print(" align=\"right\"");
        break;
      case Center:
        printer.print(" align=\"center\"");
        break;
      default:
        throw new IllegalStateException();
    }
  }

  public void visit(TableHeaderNode node) {
    inTableHeader = true;
    printIndentedTag(node, "thead");
    inTableHeader = false;
  }

  public void visit(TableNode node) {
    currentTableNode = node;
    printIndentedTag(node, "table");
    currentTableNode = null;
  }

  public void visit(TableRowNode node) {
    currentTableColumn = 0;
    printIndentedTag(node, "tr");
  }

  public void visit(VerbatimNode node) {
    printer.println().print("<pre><code");
    if (!StringUtils.isEmpty(node.getType())) {
      printAttribute("class", node.getType());
    }
    printer.print(">");
    String text = node.getText();
    // print HTML breaks for all initial newlines
    while (text.charAt(0) == '\n') {
      printer.print("<br/>");
      text = text.substring(1);
    }
    printer.printEncoded(text);
    printer.print("</code></pre>");
  }

  public void visit(WikiLinkNode node) {
    printLink(linkRenderer.render(node));
  }

  public void visit(TextNode node) {
    if (abbreviations.isEmpty()) {
      printer.print(node.getText());
    } else {
      printWithAbbreviations(node.getText());
    }
  }

  public void visit(SpecialTextNode node) {
    printer.printEncoded(node.getText());
  }

  public void visit(SuperNode node) {
    visitChildren(node);
  }

  public void visit(Node node) {
    // NOTE: Override this method for processing custom Node implementations
    unknownNode(node);
  }

  // //////////////////////////////////////////////////////////////////////////////////////////////
  // HELPERS
  // //////////////////////////////////////////////////////////////////////////////////////////////

  protected void printTag(TextNode node, String tag) {
    startOfContent = false;
    printer.print('<').print(tag).print('>');
    printer.printEncoded(node.getText());
    printer.print('<').print('/').print(tag).print('>');
  }

  protected void printTag(SuperNode node, String tag) {
    startOfContent = false;
    printer.print('<').print(tag).print('>');
    visitChildren(node);
    printer.print('<').print('/').print(tag).print('>');
  }

  protected void printIndentedTag(SuperNode node, String tag) {
    if (!startOfContent) {
      printer.println();
    }
    startOfContent = false;
    printer.print('<').print(tag).print('>').indent(+2);
    visitChildren(node);
    printer.indent(-2).println().print('<').print('/').print(tag).print('>');
  }

  protected void printImageTag(SuperNode imageNode, String url, String title) {
    startOfContent = false;
    printer.print("<figure>").indent(+2).println();
    String caption = printChildrenToString(imageNode);
    printer.print("<img src=\"").print(url).print("\"  alt=\"").printEncoded(caption).print("\"/>").println();
    printer.print("<figcaption>").print(caption).print("</figcaption>").indent(-2).println();
    printer.print("</figure>");
  }

  protected void printLink(LinkRenderer.Rendering rendering) {
    startOfContent = false;
    printer.print('<').print('a');
    printAttribute("href", rendering.href);
    for (LinkRenderer.Attribute attr : rendering.attributes) {
      printAttribute(attr.name, attr.value);
    }
    printer.print('>').print(rendering.text).print("</a>");
  }

  private void printAttribute(String name, String value) {
    printer.print(' ').print(name).print('=').print('"').print(value).print('"');
  }

  protected String printChildrenToString(SuperNode node) {
    Printer priorPrinter = printer;
    printer = new Printer();
    visitChildren(node);
    String result = printer.getString();
    printer = priorPrinter;
    return result;
  }

  protected String normalize(String string) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < string.length(); i++) {
      char c = string.charAt(i);
      switch (c) {
        case ' ':
        case '\n':
        case '\t':
          continue;
      }
      sb.append(Character.toLowerCase(c));
    }
    return sb.toString();
  }

  protected void printWithAbbreviations(String string) {
    Map<Integer, Map.Entry<String, String>> expansions = null;

    for (Map.Entry<String, String> entry : abbreviations.entrySet()) {
      // first check, whether we have a legal match
      String abbr = entry.getKey();

      int ix = 0;
      while (true) {
        int sx = string.indexOf(abbr, ix);
        if (sx == -1)
          break;

        // only allow whole word matches
        ix = sx + abbr.length();

        if (sx > 0 && Character.isLetterOrDigit(string.charAt(sx - 1)))
          continue;
        if (ix < string.length() && Character.isLetterOrDigit(string.charAt(ix))) {
          continue;
        }

        // ok, legal match so save an expansions "task" for all matches
        if (expansions == null) {
          expansions = new TreeMap<Integer, Map.Entry<String, String>>();
        }
        expansions.put(sx, entry);
      }
    }

    if (expansions != null) {
      int ix = 0;
      for (Map.Entry<Integer, Map.Entry<String, String>> entry : expansions.entrySet()) {
        int sx = entry.getKey();
        String abbr = entry.getValue().getKey();
        String expansion = entry.getValue().getValue();

        printer.printEncoded(string.substring(ix, sx));
        printer.print("<abbr");
        if (StringUtils.isNotEmpty(expansion)) {
          printer.print(" title=\"");
          printer.printEncoded(expansion);
          printer.print('"');
        }
        printer.print('>');
        printer.printEncoded(abbr);
        printer.print("</abbr>");
        ix = sx + abbr.length();
      }
      printer.print(string.substring(ix));
    } else {
      printer.print(string);
    }
  }

}