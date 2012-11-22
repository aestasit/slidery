Slidedown
=====================

Java library to support creation of presentation slides written in Markdown.

Getting started
======================

```java

import static com.aestasit.markdown.Resources.classpath;
import static com.aestasit.markdown.Resources.file;

import com.aestasit.markdown.slidedown.converters.ConverterFactory;
import com.aestasit.markdown.slidedown.converters.DeckJSConverter;

...

Configuration config = new SimpleConfiguration()
   .inputFile(classpath("01_simple_slides.md"))
   .inputFile(classpath("02_image_slides.md"))
   .inputFile(classpath("03_code_slides.md"))
   .inputFile(classpath("04_slide_notes.md"))
   .staticFile(classpath("LOGO_300dpi.png"))
   .staticFile(classpath("LOGO_FULL_300dpi.png"))
   .outputFile(file("./tmp/presentation.html"))
   .encoding("UTF-8");

ConverterFactory
   .createConverter(DeckJSConverter.CONVERTER_ID)
   .render(config);

```

Advanced configuration
======================



Custom converters
======================

