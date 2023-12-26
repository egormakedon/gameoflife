package com.ym.gameoflife.output;

import com.ym.gameoflife.entity.CellFactory;
import com.ym.gameoflife.entity.CellState;
import com.ym.gameoflife.entity.Grid;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Yahor Makedon
 */
public class GridFormatterTest {
  static CellFactory factory = CellFactory.getInstance();

  static GridFormatter formatter = new GridFormatter();

  @Test
  void test_format() {
    //given
    Grid grid = given_format_createAndGetGrid();
    String expected = """
      -------------
      | ■ |   | ■ |
      -------------
      |   | ■ |   |
      -------------
      | ■ |   | ■ |
      -------------
      """;

    //when
    String actual = formatter.format(grid);

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Grid given_format_createAndGetGrid() {
    return new Grid(3, List.of(
      factory.make(0, 0, CellState.LIVE),
      factory.make(0, 1, CellState.DEAD),
      factory.make(0, 2, CellState.LIVE),
      factory.make(1, 0, CellState.DEAD),
      factory.make(1, 1, CellState.LIVE),
      factory.make(1, 2, CellState.DEAD),
      factory.make(2, 0, CellState.LIVE),
      factory.make(2, 1, CellState.DEAD),
      factory.make(2, 2, CellState.LIVE))
    );
  }
}
