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

package com.aestasit.markdown;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.pegdown.PegDownProcessor;
import org.pegdown.ast.RootNode;

import com.aestasit.markdown.visitors.AstPrinter;
import com.aestasit.markdown.visitors.LoggingVisitor;
import com.aestasit.markdown.visitors.TextExtractor;

/**
 * Markdown processing utility methods that wrap around <a
 * href="https://github.com/sirthias/pegdown">Pegdown</a> parsing library.
 * 
 * @author Andrey Adamovich
 * 
 */
public class Markdown {

  // ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // AST
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////

  public static RootNode toAst(final char[] text) throws IOException {
    return new PegDownProcessor().parser.parse(text);
  }

  public static RootNode toAst(final char[] text, final int options) throws IOException {
    return new PegDownProcessor(options).parser.parse(text);
  }

  public static RootNode toAst(final String text) throws IOException {
    return toAst(text.toCharArray());
  }

  public static RootNode toAst(final String text, final int options) throws IOException {
    return toAst(text.toCharArray(), options);
  }

  public static RootNode toAst(final InputStream stream) throws IOException {
    return toAst(IOUtils.toCharArray(stream));
  }

  public static RootNode toAst(final InputStream stream, final int options) throws IOException {
    return toAst(IOUtils.toCharArray(stream), options);
  }

  public static RootNode toAst(final File filePath) throws IOException {
    return toAst(new FileInputStream(filePath));
  }

  public static RootNode toAst(final File filePath, final int options) throws IOException {
    return toAst(new FileInputStream(filePath), options);
  }

  public static RootNode toAst(final URL url) throws IOException {
    return toAst(url.openStream());
  }

  public static RootNode toAst(final URL url, final int options) throws IOException {
    return toAst(url.openStream(), options);
  }

  // ////////////////////////////////////////////////////////////////////////////////////////////////////////
  // MISC.
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////

  public static String extractText(final RootNode node) {
    final ByteArrayOutputStream data = new ByteArrayOutputStream();
    new TextExtractor(new PrintStream(data)).visit(node);
    return new String(data.toByteArray());
  }

  public static String printAst(final RootNode node) {
    final ByteArrayOutputStream data = new ByteArrayOutputStream();
    new AstPrinter(new PrintStream(data)).visit(node);
    return new String(data.toByteArray());
  }

  public static void logAst(final RootNode node) {
    new LoggingVisitor().visit(node);
  }

}
