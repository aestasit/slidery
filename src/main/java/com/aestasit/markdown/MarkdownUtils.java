package com.aestasit.markdown;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.pegdown.PegDownProcessor;
import org.pegdown.ast.RootNode;

public class MarkdownUtils {

  public static RootNode parse(String filePath) throws FileNotFoundException, IOException {
    return new PegDownProcessor().parser.parse(IOUtils.toCharArray(new FileInputStream(filePath)));
  }

  public static RootNode parse(InputStream stream) throws FileNotFoundException, IOException {
    return new PegDownProcessor().parser.parse(IOUtils.toCharArray(stream));
  }

}
