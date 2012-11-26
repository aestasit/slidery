package com.aestasit.markdown.slidery.converters;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.aestasit.markdown.slidery.converters.ConverterFactory;

/**
 * @author Andrey Adamovich
 *
 */
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
