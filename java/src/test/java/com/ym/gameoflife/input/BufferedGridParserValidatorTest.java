package com.ym.gameoflife.input;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Yahor Makedon
 */
public class BufferedGridParserValidatorTest {
  @ParameterizedTest(name = "Should validate and return true if correct, otherwise false: [validator=`{0}`, inputLine=`{1}`, expected=`{2}`]")
  @MethodSource("given_validate")
  void parameterizedTest_validate(BufferedGridParserValidator validator, String inputLine, boolean expected) {
    //when
    boolean actual = validator.validate(inputLine);

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_validate() {
    //given
    return Stream.of(
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=0", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=1", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=2", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=3", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=4", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=5", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=6", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=7", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=8", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=9", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=10", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=11", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=12", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=42", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=555", true),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=asd", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=1d", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=1123-", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "     ", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "   n=3  ", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n = 3", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=3\n", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, null, false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=-1", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "n=-42", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "123", false),
      Arguments.of(BufferedGridParserValidator.ROW_N, "asdf", false),

      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "0", true),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "1", true),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "010101", true),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "000000", true),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "111111", true),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "1111-11", false),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "22222", false),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "asf", false),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "1234", false),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "-1", false),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "", false),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, null, false),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "     ", false),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "   0   ", false),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "0 ", false),
      Arguments.of(BufferedGridParserValidator.ROW_CELLS, "0\n", false)
    );
  }
}
