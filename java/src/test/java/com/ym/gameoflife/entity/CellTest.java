package com.ym.gameoflife.entity;

import com.ym.gameoflife.util.Mediator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Yahor Makedon
 */
@ExtendWith(MockitoExtension.class)
public class CellTest {
  @Mock
  Mediator<Cell> mediatorMock;

  Cell liveCell;
  Cell deadCell;

  @BeforeEach
  void init() {
    liveCell = new Cell(0, 0, CellState.LIVE, mediatorMock);
    deadCell = new Cell(1, 1, CellState.DEAD, mediatorMock);
  }

  @Test
  void test_changeStateAndNotify_liveToDead() {
    //given
    CellState expected = CellState.DEAD;
    doNothing().when(mediatorMock).notify(liveCell);

    //when
    liveCell.changeStateAndNotify();
    CellState actual = liveCell.getState();

    //then
    assertThat(actual).isSameAs(expected);

    //verify
    verify(mediatorMock, times(1)).notify(liveCell);
  }

  @Test
  void test_changeStateAndNotify_deadToLive() {
    //given
    CellState expected = CellState.LIVE;
    doNothing().when(mediatorMock).notify(deadCell);

    //when
    deadCell.changeStateAndNotify();
    CellState actual = deadCell.getState();

    //then
    assertThat(actual).isSameAs(expected);

    //verify
    verify(mediatorMock, times(1)).notify(deadCell);
  }

  @Test
  void test_toPrintString() {
    //given
    String expected1 = "1";
    String expected2 = "0";

    //when
    String actual1 = liveCell.toPrintString();
    String actual2 = deadCell.toPrintString();

    //then
    assertThat(actual1).isEqualTo(expected1);
    assertThat(actual2).isEqualTo(expected2);
  }

  @Test
  void test_toString() {
    //given
    String expected1 = "Cell={i=`0`, j=`0`, CellState=`LIVE(1)`}";
    String expected2 = "Cell={i=`1`, j=`1`, CellState=`DEAD(0)`}";

    //when
    String actual1 = liveCell.toString();
    String actual2 = deadCell.toString();

    //then
    assertThat(actual1).isEqualTo(expected1);
    assertThat(actual2).isEqualTo(expected2);
  }

  @Test
  void test_isLive() {
    //when
    boolean actual1 = liveCell.isLive();
    boolean actual2 = deadCell.isLive();

    //then
    assertThat(actual1).isTrue();
    assertThat(actual2).isFalse();
  }

  @Test
  void test_isDead() {
    //when
    boolean actual1 = liveCell.isDead();
    boolean actual2 = deadCell.isDead();

    //then
    assertThat(actual1).isFalse();
    assertThat(actual2).isTrue();
  }
}
