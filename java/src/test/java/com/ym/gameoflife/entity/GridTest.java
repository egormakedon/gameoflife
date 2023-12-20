package com.ym.gameoflife.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Yahor Makedon
 */
public class GridTest {
  CellFactory factory = CellFactory.getInstance();

  Grid grid;

  @BeforeEach
  void init() {
    grid = new Grid(3, List.of(
      factory.make(0, 0, CellState.LIVE),
      factory.make(0, 1, CellState.DEAD),
      factory.make(0, 2, CellState.DEAD),
      factory.make(1, 0, CellState.DEAD),
      factory.make(1, 1, CellState.LIVE),
      factory.make(1, 2, CellState.DEAD),
      factory.make(2, 0, CellState.DEAD),
      factory.make(2, 1, CellState.DEAD),
      factory.make(2, 2, CellState.LIVE))
    );
  }

  @Test
  void test_init() {
    //given
    Cell[][] expectedGrid = new Cell[3][3];
    expectedGrid[0][0] = factory.make(0, 0, CellState.LIVE);
    expectedGrid[0][1] = factory.make(0, 1, CellState.DEAD);
    expectedGrid[0][2] = factory.make(0, 2, CellState.DEAD);
    expectedGrid[1][0] = factory.make(1, 0, CellState.DEAD);
    expectedGrid[1][1] = factory.make(1, 1, CellState.LIVE);
    expectedGrid[1][2] = factory.make(1, 2, CellState.DEAD);
    expectedGrid[2][0] = factory.make(2, 0, CellState.DEAD);
    expectedGrid[2][1] = factory.make(2, 1, CellState.DEAD);
    expectedGrid[2][2] = factory.make(2, 2, CellState.LIVE);

    Set<Cell> expectedLiveCells = Set.of(
      factory.make(0, 0, CellState.LIVE),
      factory.make(1, 1, CellState.LIVE),
      factory.make(2, 2, CellState.LIVE)
    );

    //when
    Cell[][] actualGrid = grid.getGrid();
    Set<Cell> actualLiveCells = grid.getLiveCells();

    //then
    assertThat(actualGrid).isEqualTo(expectedGrid);
    assertThat(actualLiveCells).isEqualTo(expectedLiveCells);
  }

  @Test
  void test_toPrintString() {
    //given
    String expected = "100\n010\n001\n";

    //when
    String actual = grid.toPrintString();

    //then
    assertThat(actual).isEqualTo(expected);
  }

  @ParameterizedTest(name = "Should check and return correct index: [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_checkAndGetCorrectIndex_validIndex")
  void parameterizedTest_checkAndGetCorrectIndex_validIndex(int input, int expected) {
    //when
    int actual = grid.checkAndGetCorrectIndex(input);

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_checkAndGetCorrectIndex_validIndex() {
    //given
    return Stream.of(
      Arguments.of(0, 0),
      Arguments.of(1, 1),
      Arguments.of(2, 2),
      Arguments.of(-1, 2),
      Arguments.of(3, 0)
    );
  }

  @Test
  void test_checkAndGetCorrectIndex_invalidIndex() {
    //given
    int input1 = -2;
    String expected1 = "Provided index=`-2` is incorrect";

    int input2 = 4;
    String expected2 = "Provided index=`4` is incorrect";

    //when & then
    IllegalArgumentException actual1 = assertThrows(IllegalArgumentException.class, () -> grid.checkAndGetCorrectIndex(input1));
    assertThat(actual1.getMessage()).isEqualTo(expected1);

    IllegalArgumentException actual2 = assertThrows(IllegalArgumentException.class, () -> grid.checkAndGetCorrectIndex(input2));
    assertThat(actual2.getMessage()).isEqualTo(expected2);
  }

  @Test
  void test_streamOfNeighbourCellsFrom() {
    //given
    Cell input = factory.make(1, 1, CellState.LIVE);
    List<Cell> expected = List.of(
      factory.make(0, 0, CellState.LIVE),
      factory.make(0, 1, CellState.DEAD),
      factory.make(0, 2, CellState.DEAD),
      factory.make(1, 0, CellState.DEAD),
      factory.make(1, 2, CellState.DEAD),
      factory.make(2, 0, CellState.DEAD),
      factory.make(2, 1, CellState.DEAD),
      factory.make(2, 2, CellState.LIVE)
    );

    //when
    List<Cell> actual = grid.streamOfNeighbourCellsFrom(input).toList();

    //then
    assertThat(actual).isEqualTo(expected);
  }
}
