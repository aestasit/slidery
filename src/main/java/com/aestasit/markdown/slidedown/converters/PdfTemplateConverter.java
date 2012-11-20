package com.aestasit.markdown.slidedown.converters;

import java.io.IOException;
import java.io.Writer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PdfTemplateConverter extends BaseConverter {

  @Override
  protected void convert(Document slidesDocument, Writer writer, Configuration config) throws IOException {
    Elements slides = slidesDocument.getElementsByTag("slide");
    for (Element slide : slides) {
      
      
      
    }
    
    // TODO Auto-generated method stub
    
  }

}
