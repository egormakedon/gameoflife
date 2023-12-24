package com.ym.gameoflife.controller.game;

import com.ym.gameoflife.entity.Cell;
import com.ym.gameoflife.entity.CellFactory;
import com.ym.gameoflife.entity.CellState;
import com.ym.gameoflife.entity.Grid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Yahor Makedon
 */
public class GameOfLifeControllerTest {
  static CellFactory factory = CellFactory.getInstance();

  static final Cell liveCell = factory.make(0, 0, CellState.LIVE);
  static final Cell deadCell = factory.make(1, 1, CellState.DEAD);

  GameOfLifeController controller;

  @BeforeEach
  void init() {
    controller = new GameOfLifeController();
  }

  @ParameterizedTest(name = "Should calculate and return number of live cells: [input=`{0}`, expected=`{1}`]")
  @MethodSource("given_calculateNumberOfLiveFrom")
  void parameterizedTest_calculateNumberOfLiveFrom(List<Cell> input, int expected) {
    //when
    int actual = controller.calculateNumberOfLiveFrom(input.stream());

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_calculateNumberOfLiveFrom() {
    //given
    return Stream.of(
      Arguments.of(List.of(
        factory.make(0, 0, CellState.DEAD),
        factory.make(1, 1, CellState.LIVE),
        factory.make(2, 2, CellState.DEAD)
      ), 1),
      Arguments.of(List.of(
        factory.make(0, 0, CellState.LIVE),
        factory.make(1, 1, CellState.LIVE),
        factory.make(2, 2, CellState.LIVE)
      ), 3),
      Arguments.of(List.of(
        factory.make(0, 0, CellState.DEAD),
        factory.make(1, 1, CellState.DEAD),
        factory.make(2, 2, CellState.DEAD)
      ), 0)
    );
  }

  @ParameterizedTest(name = "Should return true if cell should be updated, otherwise false: [inputCell=`{0}`, inputNumberOfLive=`{1}`, expected=`{2}`]")
  @MethodSource("given_cellShouldBeUpdated")
  void parameterizedTest_cellShouldBeUpdated(Cell inputCell, int inputNumberOfLive, boolean expected) {
    //when
    boolean actual = controller.cellShouldBeUpdated(inputCell, inputNumberOfLive);

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_cellShouldBeUpdated() {
    //given
    return Stream.of(
      Arguments.of(liveCell, -1, true),
      Arguments.of(liveCell, 0, true),
      Arguments.of(liveCell, 1, true),
      Arguments.of(liveCell, 2, false),
      Arguments.of(liveCell, 3, false),
      Arguments.of(liveCell, 4, true),
      Arguments.of(liveCell, 5, true),

      Arguments.of(deadCell, -1, false),
      Arguments.of(deadCell, 0, false),
      Arguments.of(deadCell, 1, false),
      Arguments.of(deadCell, 2, false),
      Arguments.of(deadCell, 3, true),
      Arguments.of(deadCell, 4, false),
      Arguments.of(deadCell, 5, false)
    );
  }

  @Test
  void test_iterate() {
    //given
    Grid grid = given_iterate_createAndGetGrid();
    controller.setGrid(grid);

    String expectedInitial = "00000\n00000\n01110\n00000\n00000\n";
    String expectedAfterIteration1 = "00000\n00100\n00100\n00100\n00000\n";
    String expectedAfterIteration2 = "00000\n00000\n01110\n00000\n00000\n";

    //when & then
    assertThat(grid.toPrintString()).isEqualTo(expectedInitial);

    controller.iterate();
    assertThat(grid.toPrintString()).isEqualTo(expectedAfterIteration1);

    controller.iterate();
    assertThat(grid.toPrintString()).isEqualTo(expectedAfterIteration2);
  }

  static Grid given_iterate_createAndGetGrid() {
    return new Grid(5, List.of(
      factory.make(0, 0, CellState.DEAD),
      factory.make(0, 1, CellState.DEAD),
      factory.make(0, 2, CellState.DEAD),
      factory.make(0, 3, CellState.DEAD),
      factory.make(0, 4, CellState.DEAD),
      factory.make(1, 0, CellState.DEAD),
      factory.make(1, 1, CellState.DEAD),
      factory.make(1, 2, CellState.DEAD),
      factory.make(1, 3, CellState.DEAD),
      factory.make(1, 4, CellState.DEAD),
      factory.make(2, 0, CellState.DEAD),
      factory.make(2, 1, CellState.LIVE),
      factory.make(2, 2, CellState.LIVE),
      factory.make(2, 3, CellState.LIVE),
      factory.make(2, 4, CellState.DEAD),
      factory.make(3, 0, CellState.DEAD),
      factory.make(3, 1, CellState.DEAD),
      factory.make(3, 2, CellState.DEAD),
      factory.make(3, 3, CellState.DEAD),
      factory.make(3, 4, CellState.DEAD),
      factory.make(4, 0, CellState.DEAD),
      factory.make(4, 1, CellState.DEAD),
      factory.make(4, 2, CellState.DEAD),
      factory.make(4, 3, CellState.DEAD),
      factory.make(4, 4, CellState.DEAD)
    ));
  }
}
