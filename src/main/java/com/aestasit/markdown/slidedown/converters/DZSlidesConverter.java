package com.aestasit.markdown.slidedown.converters;

import static com.aestasit.markdown.Resources.classpath;

import java.util.HashMap;

import org.jsoup.nodes.Document;

public class DZSlidesConverter extends TextTemplateConverter {

  @Override
  protected void beforeStart(Configuration config) {
    config.templateFile(classpath("dzslides/template.html"));
  }

  @Override
  protected void expandBinding(HashMap<String, Object> binding, Document slidesDocument) {
    binding.put("backgroundColor", "black");
    binding.put("webFont", "Oswald");
  }

  @Override
  protected void transformDocument(Document slidesDocument) {
  }

}
