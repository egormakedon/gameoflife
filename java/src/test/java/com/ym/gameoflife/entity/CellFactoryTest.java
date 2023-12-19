package com.ym.gameoflife.entity;

import com.ym.gameoflife.util.Mediator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Yahor Makedon
 */
@ExtendWith(MockitoExtension.class)
public class CellFactoryTest {
  @Mock
  Mediator<Cell> mediatorMock;

  CellFactory factory;

  @BeforeEach
  void init() {
    factory = CellFactory.getInstance(mediatorMock);
  }

  @Test
  void test_make() {
    //when
    Cell actual = factory.make(0, 1, CellState.LIVE);

    //then
    assertThat(actual).isNotNull();
    assertThat(actual.i).isEqualTo(0);
    assertThat(actual.j).isEqualTo(1);
    assertThat(actual.getState()).isSameAs(CellState.LIVE);
  }

  @Test
  void test_make_notSame() {
    //when
    Cell actual1 = factory.make(0, 1, CellState.LIVE);
    Cell actual2 = factory.make(0, 1, CellState.LIVE);

    //then
    assertThat(actual1).isNotSameAs(actual2);
  }

  @Test
  void test_clone() {
    //given
    Cell original = factory.make(0, 1, CellState.LIVE);

    //when
    Cell prototype = factory.clone(original);

    //then
    assertThat(prototype).isNotNull();
    assertThat(prototype)
      .isNotSameAs(original)
      .usingRecursiveComparison()
      .ignoringFields("mediator")
      .isEqualTo(original);
  }
}
