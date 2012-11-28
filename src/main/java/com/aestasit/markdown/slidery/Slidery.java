package com.aestasit.markdown.slidery;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.pegdown.Extensions;
import org.pegdown.LinkRenderer;
import org.pegdown.ast.RootNode;

import com.aestasit.markdown.Markdown;
import com.google.common.base.Preconditions;

/**
 * <code>Slidery</code> conversion methods from <i>Markdown</i> to <i>HTML</i> to <i>DOM</i>.
 * 
 * @author Andrey Adamovich
 *
 */
public final class Slidery {

  public static int DEFAULT_PEGDOWN_OPTIONS = Extensions.ALL + Extensions.SUPPRESS_ALL_HTML;

  public static String toSlides(RootNode node) {
    Preconditions.checkNotNull(node, "astRoot");
    return getConverter().toHtml(node);
  }

  public static String toSlides(InputStream stream) throws IOException {
    Preconditions.checkNotNull(stream, "inputStream");
    return getConverter().toHtml(Markdown.toAst(stream, DEFAULT_PEGDOWN_OPTIONS));
  }

  public static String toSlides(String text) throws IOException {
    Preconditions.checkNotNull(text, "text");
    return getConverter().toHtml(Markdown.toAst(new ByteArrayInputStream(text.getBytes()), DEFAULT_PEGDOWN_OPTIONS));
  }

  public static String toSlides(File file) throws IOException {
    Preconditions.checkNotNull(file, "file");
    return getConverter().toHtml(Markdown.toAst(file, DEFAULT_PEGDOWN_OPTIONS));
  }

  public static String toSlides(URL url) throws IOException {
    Preconditions.checkNotNull(url, "url");
    return getConverter().toHtml(Markdown.toAst(url.openStream(), DEFAULT_PEGDOWN_OPTIONS));
  }

  public static Document toDom(String text) throws IOException {
    return Jsoup.parse(toSlides(text));
  }

  public static Document toDom(File file) throws IOException {
    return Jsoup.parse(toSlides(file));
  }

  public static Document toDom(URL url) throws IOException {
    return Jsoup.parse(toSlides(url));
  }

  public static Document toDom(InputStream stream) throws IOException {
    return Jsoup.parse(toSlides(stream));
  }

  public static Document toDom(RootNode node) throws IOException {
    return Jsoup.parse(toSlides(node));
  }

  private static ToHtmlSlides getConverter() {
    return new ToHtmlSlides(new LinkRenderer());
  }

}