package de.samples;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("tag2")
public class Sample1Tests {

  @Test
  @Tag("tag1")
  void testSth() {

  }

  @Test
  @Tag("tag3\"")
  void testSth2() {

  }

  @Test
  @Tag("<h1>tag3</h1>")
  void testSth3() {

  }

}
