package com.aestasit.markdown.slidedown;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.pegdown.Extensions;
import org.pegdown.LinkRenderer;
import org.pegdown.ast.RootNode;

import com.aestasit.markdown.Markdown;
import com.google.common.base.Preconditions;

public final class Slidedown {

  private static int DEFAULT_PEGDOWN_OPTIONS = Extensions.ALL + Extensions.SUPPRESS_ALL_HTML;

  public static String toSlides(RootNode node) {
    Preconditions.checkNotNull(node, "astRoot");
    return getConverter().toXml(node);
  }

  public static String toSlides(InputStream stream) throws IOException {
    Preconditions.checkNotNull(stream, "inputStream");
    return getConverter().toXml(Markdown.toAst(stream, DEFAULT_PEGDOWN_OPTIONS));
  }

  public static String toSlides(String text) throws IOException {
    Preconditions.checkNotNull(text, "text");
    return getConverter().toXml(Markdown.toAst(new ByteArrayInputStream(text.getBytes()), DEFAULT_PEGDOWN_OPTIONS));
  }

  public static String toSlides(File file) throws IOException {
    Preconditions.checkNotNull(file, "file");
    return getConverter().toXml(Markdown.toAst(file, DEFAULT_PEGDOWN_OPTIONS));
  }

  public static String toSlides(URL url) throws IOException {
    Preconditions.checkNotNull(url, "url");
    return getConverter().toXml(Markdown.toAst(url.openStream(), DEFAULT_PEGDOWN_OPTIONS));
  }

  private static ToXmlSlides getConverter() {
    return new ToXmlSlides(new LinkRenderer());
  }

}
