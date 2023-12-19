package com.ym.gameoflife.entity;

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
public class CellStateTest {
  @ParameterizedTest(name = "Should check field 'value': [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_checkValue")
  void parameterizedTest_checkValue(CellState input, int expected) {
    //when
    int actual = input.getValue();

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_checkValue() {
    //given
    return Stream.of(
      Arguments.of(CellState.LIVE, 1),
      Arguments.of(CellState.DEAD, 0)
    );
  }

  @ParameterizedTest(name = "Should call toPrintString() and return printable String value: [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_toPrintString")
  void parameterizedTest_toPrintString(CellState input, String expected) {
    //when
    String actual = input.toPrintString();

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_toPrintString() {
    //given
    return Stream.of(
      Arguments.of(CellState.LIVE, "1"),
      Arguments.of(CellState.DEAD, "0")
    );
  }

  @ParameterizedTest(name = "Should call toString(): [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_toString")
  void parameterizedTest_toString(CellState input, String expected) {
    //when
    String actual = input.toString();

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_toString() {
    //given
    return Stream.of(
      Arguments.of(CellState.LIVE, "CellState=`LIVE(1)`"),
      Arguments.of(CellState.DEAD, "CellState=`DEAD(0)`")
    );
  }

  @ParameterizedTest(name = "Should return boolean of live state: [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_isLive")
  void parameterizedTest_isLive(CellState input, boolean expected) {
    //when
    boolean actual = input.isLive();

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_isLive() {
    //given
    return Stream.of(
      Arguments.of(CellState.LIVE, true),
      Arguments.of(CellState.DEAD, false)
    );
  }

  @ParameterizedTest(name = "Should return boolean of dead state: [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_isDead")
  void parameterizedTest_isDead(CellState input, boolean expected) {
    //when
    boolean actual = input.isDead();

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_isDead() {
    //given
    return Stream.of(
      Arguments.of(CellState.LIVE, false),
      Arguments.of(CellState.DEAD, true)
    );
  }

  @ParameterizedTest(name = "Should return CellState by provided value: [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_of_valid")
  void parameterizedTest_of_valid(int input, CellState expected) {
    //when
    CellState actual = CellState.of(input);

    //then
    assertThat(actual).isSameAs(expected);
  }

  static Stream<Arguments> given_of_valid() {
    //given
    return Stream.of(
      Arguments.of(1, CellState.LIVE),
      Arguments.of(0, CellState.DEAD)
    );
  }

  @Test
  void test_of_invalid() {
    //given
    int input = -1;
    String expected = "No such CellState under value=-1";

    //when & then
    NoSuchElementException actual = assertThrows(NoSuchElementException.class, () -> CellState.of(input));
    assertThat(actual.getMessage()).isEqualTo(expected);
  }
}
