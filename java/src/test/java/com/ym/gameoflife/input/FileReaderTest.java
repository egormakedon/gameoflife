package com.ym.gameoflife.input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Yahor Makedon
 */
public class FileReaderTest {
  static final String INPUT_PATH = "src/test/resources/input.txt";

  @Test
  void test_readFile_valid() {
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

  @ParameterizedTest(name = "Should throw an exception: [input=`{0}`, expectedMessage=`{1}`]")
  @MethodSource("given_readFile_invalid")
  void parameterizedTest_readFile_invalid(String input, String expectedMessage) {
    //when & then
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      try (FileReader reader = new FileReader(input)) {
      }
    });
    String actualMessage = exception.getMessage();
    assertThat(actualMessage).isEqualTo(expectedMessage);
  }

  static Stream<Arguments> given_readFile_invalid() {
    //given
    return Stream.of(
      Arguments.of(";", "Invalid filePath=`;`"),
      Arguments.of("file.txt", "Invalid filePath=`file.txt`"),
      Arguments.of(" ", "Invalid filePath=` `")
    );
  }
}
