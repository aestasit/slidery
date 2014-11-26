/*
 * Copyright (C) 2011-2014 Aestas/IT
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

  public static int PEGDOWN_ENABLE_ALL_EXTENSIONS = Extensions.ALL;  
  public static int PEGDOWN_ENABLE_ALL_EXTENSIONS_AND_SUPPRESS_HTML = Extensions.ALL + Extensions.SUPPRESS_ALL_HTML;
  
  public static int DEFAULT_PEGDOWN_OPTIONS = PEGDOWN_ENABLE_ALL_EXTENSIONS_AND_SUPPRESS_HTML;

  public static String toSlides(RootNode node) {
    Preconditions.checkNotNull(node, "astRoot");
    return getConverter().toHtml(node);
  }

  public static String toSlides(InputStream stream, int options) throws IOException {
    Preconditions.checkNotNull(stream, "inputStream");
    return getConverter().toHtml(Markdown.toAst(stream, options));
  }

  public static String toSlides(InputStream stream) throws IOException {
    return toSlides(stream, DEFAULT_PEGDOWN_OPTIONS); 
  }

  public static String toSlides(String text, int options) throws IOException {
    Preconditions.checkNotNull(text, "text");
    return getConverter().toHtml(Markdown.toAst(new ByteArrayInputStream(text.getBytes()), options));
  }

  public static String toSlides(String text) throws IOException {
    return toSlides(text, DEFAULT_PEGDOWN_OPTIONS);
  }

  public static String toSlides(File file, int options) throws IOException {
    Preconditions.checkNotNull(file, "file");
    return getConverter().toHtml(Markdown.toAst(file, options));
  }

  public static String toSlides(File file) throws IOException {
    return toSlides(file, DEFAULT_PEGDOWN_OPTIONS);
  }

  public static String toSlides(URL url, int options) throws IOException {
    Preconditions.checkNotNull(url, "url");
    return getConverter().toHtml(Markdown.toAst(url.openStream(), options));
  }

  public static String toSlides(URL url) throws IOException {
    return toSlides(url, DEFAULT_PEGDOWN_OPTIONS);
  }

  public static Document toDom(String text) throws IOException {
    return Jsoup.parse(toSlides(text));
  }

  public static Document toDom(String text, int options) throws IOException {
    return Jsoup.parse(toSlides(text, options));
  }

  public static Document toDom(File file) throws IOException {
    return Jsoup.parse(toSlides(file));
  }

  public static Document toDom(File file, int options) throws IOException {
    return Jsoup.parse(toSlides(file, options));
  }

  public static Document toDom(URL url) throws IOException {
    return Jsoup.parse(toSlides(url));
  }

  public static Document toDom(URL url, int options) throws IOException {
    return Jsoup.parse(toSlides(url, options));
  }

  public static Document toDom(InputStream stream) throws IOException {
    return Jsoup.parse(toSlides(stream));
  }

  public static Document toDom(InputStream stream, int options) throws IOException {
    return Jsoup.parse(toSlides(stream, options));
  }

  public static Document toDom(RootNode node) throws IOException {
    return Jsoup.parse(toSlides(node));
  }

  private static ToHtmlSlides getConverter() {
    return new ToHtmlSlides(new LinkRenderer());
  }

}
