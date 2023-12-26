package com.ym.gameoflife.controller.application;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Yahor Makedon
 */
public class ListenEventTest {
  @ParameterizedTest(name = "Should check field 'code': [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_checkCode")
  void parameterizedTest_checkCode(ListenEvent input, String expected) {
    //when
    String actual = input.getCode();

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_checkCode() {
    //given
    return Stream.of(
      Arguments.of(ListenEvent.EXIT, "e"),
      Arguments.of(ListenEvent.RESTART, "r"),
      Arguments.of(ListenEvent.PAUSE, "p"),
      Arguments.of(ListenEvent.CONTINUE, "c"),
      Arguments.of(ListenEvent.GRID_ON, "gn"),
      Arguments.of(ListenEvent.GRID_OFF, "gf"),
      Arguments.of(ListenEvent.DELAY, "d")
    );
  }

  @ParameterizedTest(name = "Should return Optional<ListenEvent> by provided code: [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_of")
  void parameterizedTest_of(String input, Optional<ListenEvent> expected) {
    //when
    Optional<ListenEvent> actual = ListenEvent.of(input);

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_of() {
    //given
    return Stream.of(
      Arguments.of("e", Optional.of(ListenEvent.EXIT)),
      Arguments.of("r", Optional.of(ListenEvent.RESTART)),
      Arguments.of("p", Optional.of(ListenEvent.PAUSE)),
      Arguments.of("c", Optional.of(ListenEvent.CONTINUE)),
      Arguments.of("gn", Optional.of(ListenEvent.GRID_ON)),
      Arguments.of("gf", Optional.of(ListenEvent.GRID_OFF)),
      Arguments.of("d1000", Optional.of(ListenEvent.DELAY)),
      Arguments.of("d100", Optional.of(ListenEvent.DELAY)),
      Arguments.of("d1", Optional.of(ListenEvent.DELAY)),
      Arguments.of("d300", Optional.of(ListenEvent.DELAY)),
      Arguments.of("d500", Optional.of(ListenEvent.DELAY)),
      Arguments.of("d2000", Optional.of(ListenEvent.DELAY)),

      Arguments.of(null, Optional.empty()),
      Arguments.of("", Optional.empty()),
      Arguments.of(" ", Optional.empty()),
      Arguments.of("null", Optional.empty()),
      Arguments.of("1d", Optional.empty()),
      Arguments.of("e1", Optional.empty()),
      Arguments.of("dasd", Optional.empty()),
      Arguments.of("1", Optional.empty()),
      Arguments.of("0", Optional.empty()),
      Arguments.of("gff", Optional.empty()),
      Arguments.of("ng", Optional.empty()),
      Arguments.of("a", Optional.empty()),
      Arguments.of("b", Optional.empty()),
      Arguments.of("n", Optional.empty()),
      Arguments.of("f", Optional.empty()),

      Arguments.of("d", Optional.empty()),
      Arguments.of("d-1", Optional.empty()),
      Arguments.of("d-1000", Optional.empty()),
      Arguments.of("d0", Optional.empty()),
      Arguments.of("d1000d200", Optional.empty()),
      Arguments.of("d100d", Optional.empty()),
      Arguments.of("d1000d", Optional.empty()),
      Arguments.of("dd1000", Optional.empty()),
      Arguments.of("ddd1000", Optional.empty()),
      Arguments.of("d100dd1000d1000d", Optional.empty()),
      Arguments.of("d100ddd1000d1000d", Optional.empty()),
      Arguments.of("d1000.2", Optional.empty()),
      Arguments.of("d1000.20", Optional.empty()),
      Arguments.of("d1000,10", Optional.empty())
    );
  }
}
