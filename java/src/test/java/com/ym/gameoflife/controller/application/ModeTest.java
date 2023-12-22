package com.ym.gameoflife.controller.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Yahor Makedon
 */
public class ModeTest {
  @ParameterizedTest(name = "Should check field 'code': [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_checkCode")
  void parameterizedTest_checkCode(Mode input, String expected) {
    //when
    String actual = input.getCode();

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_checkCode() {
    //given
    return Stream.of(
      Arguments.of(Mode.CONSOLE_INPUT_MODE, "0"),
      Arguments.of(Mode.FILE_INPUT_MODE, "1")
    );
  }

  @ParameterizedTest(name = "Should return Mode by provided code: [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_of_valid")
  void parameterizedTest_of_valid(String input, Mode expected) {
    //when
    Mode actual = Mode.of(input);

    //then
    assertThat(actual).isSameAs(expected);
  }

  static Stream<Arguments> given_of_valid() {
    //given
    return Stream.of(
      Arguments.of("", Mode.CONSOLE_INPUT_MODE),
      Arguments.of("0", Mode.CONSOLE_INPUT_MODE),
      Arguments.of("1", Mode.FILE_INPUT_MODE)
    );
  }

  @Test
  void test_of_invalid() {
    //given
    String input = " ";
    String expected = "No such Mode under code=` `";

    //when & then
    NoSuchElementException actual = assertThrows(NoSuchElementException.class, () -> Mode.of(input));
    assertThat(actual.getMessage()).isEqualTo(expected);
  }
}
