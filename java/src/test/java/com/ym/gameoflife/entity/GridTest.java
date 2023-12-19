package com.ym.gameoflife.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

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
}
