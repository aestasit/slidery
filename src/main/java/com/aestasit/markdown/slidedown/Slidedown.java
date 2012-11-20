package com.aestasit.markdown.slidedown;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.pegdown.LinkRenderer;
import org.pegdown.ast.RootNode;

import com.aestasit.markdown.MarkdownUtils;
import com.google.common.base.Preconditions;

public final class Slidedown {

  public static String toSlides(RootNode node) {
    Preconditions.checkNotNull(node, "astRoot");
    return getConverter().toXml(node);
  }

  public static String toSlides(InputStream stream) throws IOException {
    Preconditions.checkNotNull(stream, "inputStream");
    return getConverter().toXml(MarkdownUtils.parse(stream));
  }

  public static String toSlides(String text) throws IOException {
    Preconditions.checkNotNull(text, "text");
    return getConverter().toXml(MarkdownUtils.parse(new ByteArrayInputStream(text.getBytes())));
  }

  public static String toSlides(File file) throws IOException {
    Preconditions.checkNotNull(file, "file");
    return getConverter().toXml(MarkdownUtils.parse(file.getAbsolutePath()));
  }

  public static String toSlides(URL url) throws IOException {
    Preconditions.checkNotNull(url, "url");
    return getConverter().toXml(MarkdownUtils.parse(url.openStream()));
  }

  private static ToXmlSlides getConverter() {
    return new ToXmlSlides(new LinkRenderer());
  }

}
