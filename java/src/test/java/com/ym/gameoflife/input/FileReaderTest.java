package com.ym.gameoflife.input;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Yahor Makedon
 */
public class FileReaderTest {
  static final String INPUT_PATH = "src/test/resources/input.txt";

  @Test
  void test_readFile() throws Exception {
    //given
    String expected = """
      n=5
      00000
      00000
      01110
      00000
      00000""";

    //when
    String actual;
    try (FileReader reader = new FileReader(INPUT_PATH)) {
      actual = reader.streamOfLines().collect(Collectors.joining("\n"));
    }

    //then
    assertThat(actual).isEqualTo(expected);
  }
}
