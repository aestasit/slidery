package com.aestasit.markdown.slidedown.converters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ConverterFactoryTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testNotExistingConverter() {
    thrown.expect(RuntimeException.class);
    ConverterFactory.createConverter("not-existing");
  }

  @Test
  public void testMissingClass() {
    thrown.expect(RuntimeException.class);
    ConverterFactory.createConverter("wrong");
  }

}