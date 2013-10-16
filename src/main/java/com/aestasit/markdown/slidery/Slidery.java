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
