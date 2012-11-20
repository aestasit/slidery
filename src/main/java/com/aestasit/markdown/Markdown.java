package com.aestasit.markdown;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.pegdown.PegDownProcessor;
import org.pegdown.ast.RootNode;

import com.aestasit.markdown.visitors.EchoVisitor;
import com.aestasit.markdown.visitors.LoggingVisitor;
import com.aestasit.markdown.visitors.TextExtractor;

public class Markdown {

  public static RootNode toAst(File filePath) throws FileNotFoundException, IOException {
    return new PegDownProcessor().parser.parse(IOUtils.toCharArray(new FileInputStream(filePath)));
  }

  public static RootNode toAst(InputStream stream) throws FileNotFoundException, IOException {
    return new PegDownProcessor().parser.parse(IOUtils.toCharArray(stream));
  }

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
