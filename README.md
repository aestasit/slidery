Introduction
=====================

*Slidery* is a pure-*Java* library to support creation of presentation slides written in *Markdown* format.

Getting started
======================


Command line usage
-----------------------

`slidery slides.md`


`slidery *.md`


`slidery --output-file=result/slides.html *.md`


`slidery --output-file=result/slides.html --format=dzslides  *.md`



Library usage
------------------------


**TODO**

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


Converters
======================



**TODO**


Themes
======================

**TODO**


Images
======================

**TODO**


Advanced configuration
======================

**TODO**


Recommendations
======================

**TODO**


Custom converters
======================

**TODO**


