package com.ym.gameoflife.controller.game;

import com.ym.gameoflife.controller.game.Rule;
import com.ym.gameoflife.entity.Cell;
import com.ym.gameoflife.entity.CellFactory;
import com.ym.gameoflife.entity.CellState;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Yahor Makedon
 */
public class RuleTest {
  static CellFactory factory = CellFactory.getInstance();

  static final Cell liveCell = factory.make(0, 0, CellState.LIVE);
  static final Cell deadCell = factory.make(1, 1, CellState.DEAD);

  @ParameterizedTest(name = "Should return true if rule is executed, otherwise false: [rule=`{0}`, inputCell=`{1}`, inputNumberOfLive=`{2}`, expected=`{3}`]")
  @MethodSource("given_rule")
  void parameterizedTest_rule(Rule rule, Cell inputCell, int inputNumberOfLive, boolean expected) {
    //when
    boolean actual = rule.check(inputCell, inputNumberOfLive);

    //then
    assertThat(actual).isEqualTo(expected);
  }

  static Stream<Arguments> given_rule() {
    //given
    return Stream.of(
      Arguments.of(Rule.UNDERPOPULATION, liveCell, -1, true),
      Arguments.of(Rule.UNDERPOPULATION, liveCell, 0, true),
      Arguments.of(Rule.UNDERPOPULATION, liveCell, 1, true),
      Arguments.of(Rule.UNDERPOPULATION, liveCell, 2, false),
      Arguments.of(Rule.UNDERPOPULATION, liveCell, 3, false),
      Arguments.of(Rule.UNDERPOPULATION, liveCell, 4, false),
      Arguments.of(Rule.UNDERPOPULATION, liveCell, 5, false),

      Arguments.of(Rule.UNDERPOPULATION, deadCell, -1, false),
      Arguments.of(Rule.UNDERPOPULATION, deadCell, 0, false),
      Arguments.of(Rule.UNDERPOPULATION, deadCell, 1, false),
      Arguments.of(Rule.UNDERPOPULATION, deadCell, 2, false),
      Arguments.of(Rule.UNDERPOPULATION, deadCell, 3, false),
      Arguments.of(Rule.UNDERPOPULATION, deadCell, 4, false),
      Arguments.of(Rule.UNDERPOPULATION, deadCell, 5, false),

      Arguments.of(Rule.NEXT_GENERATION, liveCell, -1, false),
      Arguments.of(Rule.NEXT_GENERATION, liveCell, 0, false),
      Arguments.of(Rule.NEXT_GENERATION, liveCell, 1, false),
      Arguments.of(Rule.NEXT_GENERATION, liveCell, 2, true),
      Arguments.of(Rule.NEXT_GENERATION, liveCell, 3, true),
      Arguments.of(Rule.NEXT_GENERATION, liveCell, 4, false),
      Arguments.of(Rule.NEXT_GENERATION, liveCell, 5, false),

      Arguments.of(Rule.NEXT_GENERATION, deadCell, -1, false),
      Arguments.of(Rule.NEXT_GENERATION, deadCell, 0, false),
      Arguments.of(Rule.NEXT_GENERATION, deadCell, 1, false),
      Arguments.of(Rule.NEXT_GENERATION, deadCell, 2, false),
      Arguments.of(Rule.NEXT_GENERATION, deadCell, 3, false),
      Arguments.of(Rule.NEXT_GENERATION, deadCell, 4, false),
      Arguments.of(Rule.NEXT_GENERATION, deadCell, 5, false),

      Arguments.of(Rule.OVERPOPULATION, liveCell, -1, false),
      Arguments.of(Rule.OVERPOPULATION, liveCell, 0, false),
      Arguments.of(Rule.OVERPOPULATION, liveCell, 1, false),
      Arguments.of(Rule.OVERPOPULATION, liveCell, 2, false),
      Arguments.of(Rule.OVERPOPULATION, liveCell, 3, false),
      Arguments.of(Rule.OVERPOPULATION, liveCell, 4, true),
      Arguments.of(Rule.OVERPOPULATION, liveCell, 5, true),

      Arguments.of(Rule.OVERPOPULATION, deadCell, -1, false),
      Arguments.of(Rule.OVERPOPULATION, deadCell, 0, false),
      Arguments.of(Rule.OVERPOPULATION, deadCell, 1, false),
      Arguments.of(Rule.OVERPOPULATION, deadCell, 2, false),
      Arguments.of(Rule.OVERPOPULATION, deadCell, 3, false),
      Arguments.of(Rule.OVERPOPULATION, deadCell, 4, false),
      Arguments.of(Rule.OVERPOPULATION, deadCell, 5, false),

      Arguments.of(Rule.REPRODUCTION, liveCell, -1, false),
      Arguments.of(Rule.REPRODUCTION, liveCell, 0, false),
      Arguments.of(Rule.REPRODUCTION, liveCell, 1, false),
      Arguments.of(Rule.REPRODUCTION, liveCell, 2, false),
      Arguments.of(Rule.REPRODUCTION, liveCell, 3, false),
      Arguments.of(Rule.REPRODUCTION, liveCell, 4, false),
      Arguments.of(Rule.REPRODUCTION, liveCell, 5, false),

      Arguments.of(Rule.REPRODUCTION, deadCell, -1, false),
      Arguments.of(Rule.REPRODUCTION, deadCell, 0, false),
      Arguments.of(Rule.REPRODUCTION, deadCell, 1, false),
      Arguments.of(Rule.REPRODUCTION, deadCell, 2, false),
      Arguments.of(Rule.REPRODUCTION, deadCell, 3, true),
      Arguments.of(Rule.REPRODUCTION, deadCell, 4, false),
      Arguments.of(Rule.REPRODUCTION, deadCell, 5, false)
    );
  }
}
