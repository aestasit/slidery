
# Java Example

```java
  new SimpleConfiguration()
      .inputFile(classpath("01_simple_slides.md"))
      .inputFile(classpath("02_image_slides.md"))
      .inputFile(classpath("03_code_slides.md"))
      .inputFile(classpath("04_slide_notes.md"))
      .staticFile(classpath("LOGO_300dpi.png"))
      .staticFile(classpath("LOGO_FULL_300dpi.png"))
      .outputFile(file("tmp/presentation.html"))        
      .encoding("UTF-8");
```

# Groovy Example

```groovy
  SimpleConfiguration config = new SimpleConfiguration()
  def files = source.files.sort { it.name }
  for (File file : files) {
    config.inputFile(file)
  }
  config.outputFile(getDestination())  
```