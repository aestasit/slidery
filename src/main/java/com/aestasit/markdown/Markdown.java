package com.aestasit.markdown;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.pegdown.PegDownProcessor;
import org.pegdown.ast.RootNode;

import com.aestasit.markdown.visitors.EchoVisitor;
import com.aestasit.markdown.visitors.LoggingVisitor;
import com.aestasit.markdown.visitors.TextExtractor;

public class Markdown {

  // ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // AST
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////

  public static RootNode toAst(char[] text) throws IOException {
    return new PegDownProcessor().parser.parse(text);
  }

  public static RootNode toAst(char[] text, int options) throws IOException {
    return new PegDownProcessor(options).parser.parse(text);
  }

  public static RootNode toAst(String text) throws IOException {
    return toAst(text.toCharArray());
  }

  public static RootNode toAst(String text, int options) throws IOException {
    return toAst(text.toCharArray(), options);
  }

  public static RootNode toAst(InputStream stream) throws IOException {
    return toAst(IOUtils.toCharArray(stream));
  }

  public static RootNode toAst(InputStream stream, int options) throws IOException {
    return toAst(IOUtils.toCharArray(stream), options);
  }

  public static RootNode toAst(File filePath) throws IOException {
    return toAst(new FileInputStream(filePath));
  }

  public static RootNode toAst(File filePath, int options) throws IOException {
    return toAst(new FileInputStream(filePath), options);
  }

  public static RootNode toAst(URL url) throws IOException {
    return toAst(url.openStream());
  }

  public static RootNode toAst(URL url, int options) throws IOException {
    return toAst(url.openStream(), options);
  }

  // ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // MISC.
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////

  public static String extractText(RootNode node) {
    return new TextExtractor(System.out).getText(node);
  }

  public static void echoMarkdown(RootNode node) {
    new EchoVisitor(System.out).visit(node);
  }
 
  public static void logAst(RootNode node) {
    new LoggingVisitor().visit(node);
  }

}
