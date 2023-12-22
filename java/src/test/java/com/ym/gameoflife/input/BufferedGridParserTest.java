package com.ym.gameoflife.input;

import com.ym.gameoflife.entity.CellFactory;
import com.ym.gameoflife.entity.CellState;
import com.ym.gameoflife.entity.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Yahor Makedon
 */
public class BufferedGridParserTest {
  static CellFactory factory = CellFactory.getInstance();

  BufferedGridParser parser;

  @BeforeEach
  void init() {
    parser = new BufferedGridParser();
  }

  @ParameterizedTest(name = "Should throw an exception: [input=`{0}`, expectedMessage=`{1}`]")
  @MethodSource("given_parseLine_invalid")
  void parameterizedTest_parseLine_invalid(String input, String expectedMessage) {
    //when & then
    Exception exception = assertThrows(Exception.class, () -> parser.parseLine(input));
    String actualMessage = exception.getMessage();
    assertThat(actualMessage).isEqualTo(expectedMessage);

    assertThat(parser.getN()).isEqualTo(-1);
  }

  static Stream<Arguments> given_parseLine_invalid() {
    //given
    return Stream.of(
      Arguments.of(null, "Could not parse incorrect line=`null`"),
      Arguments.of("", "Could not parse incorrect line=``"),
      Arguments.of("   ", "Could not parse incorrect line=`   `"),
      Arguments.of("n=", "Could not parse incorrect line=`n=`"),
      Arguments.of("\n", "Could not parse incorrect line=`\n`"),
      Arguments.of("asd", "Could not parse incorrect line=`asd`"),
      Arguments.of("00l", "Could not parse incorrect line=`00l`"),
      Arguments.of("n=-3", "Could not parse incorrect line=`n=-3`"),
      Arguments.of("0101012", "Could not parse incorrect line=`0101012`"),
      Arguments.of("-999999", "Could not parse incorrect line=`-999999`"),
      Arguments.of(" 00000", "Could not parse incorrect line=` 00000`"),

      Arguments.of("n=0", "Incorrect n range=`0`. The n valid range is [3, 20]"),
      Arguments.of("n=1", "Incorrect n range=`1`. The n valid range is [3, 20]"),
      Arguments.of("n=2", "Incorrect n range=`2`. The n valid range is [3, 20]"),
      Arguments.of("n=21", "Incorrect n range=`21`. The n valid range is [3, 20]"),
      Arguments.of("n=42", "Incorrect n range=`42`. The n valid range is [3, 20]"),
      Arguments.of("n=100", "Incorrect n range=`100`. The n valid range is [3, 20]"),

      Arguments.of("0101", "Incorrect structure. The n has not been initialized")
    );
  }

  @Test
  void test_parseLine_invalidDoubleInit() {
    //given
    String input = "n=3";
    String expectedMessage = "N=`3` has been already initialized";

    //when & then
    parser.parseLine(input);
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parser.parseLine(input));
    String actualMessage = exception.getMessage();
    assertThat(actualMessage).isEqualTo(expectedMessage);
  }

  @Test
  void test_parseCellsLine_incorrectLength() {
    //given
    parser.setN(3);
    String input = "01";
    String expectedMessage = "Incorrect length=`2` of `01`. The n=`3`";

    //when & then
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> parser.parseCellsLine(input));
    String actualMessage = exception.getMessage();
    assertThat(actualMessage).isEqualTo(expectedMessage);
  }

  @Test
  void test_buildGrid_incorrectNumberOfCells() {
    //given
    String input = """
      n=4
      0000
      1111
      """;
    String expectedMessage = "Incorrect number of parsed cells, actual=`8`, expected=`16`";

    //when & then
    Arrays.stream(input.split("\n")).forEach(parser::parseLine);
    RuntimeException exception = assertThrows(RuntimeException.class, () -> parser.buildGrid());
    String actualMessage = exception.getMessage();
    assertThat(actualMessage).isEqualTo(expectedMessage);
  }

  @Test
  void test_buildGrid() {
    //given
    String input = """
      n=3
      000
      111
      000
      """;
    Grid expected = new Grid(3, List.of(
      factory.make(0, 0, CellState.DEAD),
      factory.make(0, 1, CellState.DEAD),
      factory.make(0, 2, CellState.DEAD),
      factory.make(1, 0, CellState.LIVE),
      factory.make(1, 1, CellState.LIVE),
      factory.make(1, 2, CellState.LIVE),
      factory.make(2, 0, CellState.DEAD),
      factory.make(2, 1, CellState.DEAD),
      factory.make(2, 2, CellState.DEAD)
    ));

    //when
    Arrays.stream(input.split("\n")).forEach(parser::parseLine);
    Grid actual = parser.buildGrid();

    //then
    assertThat(actual.toPrintString()).isEqualTo(expected.toPrintString());
  }
}
