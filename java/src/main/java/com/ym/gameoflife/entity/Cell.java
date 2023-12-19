package com.ym.gameoflife.entity;

import com.ym.gameoflife.util.Mediator;
import com.ym.gameoflife.util.Printable;

import java.util.Objects;

/**
 * @author Yahor Makedon
 */
public class Cell implements Printable {
  public final int i;
  public final int j;

  private CellState state;
  private Mediator<Cell> mediator;

  public Cell(int i, int j, CellState state) {
    this.i = i;
    this.j = j;
    this.state = state;
  }

  public Cell(int i, int j, CellState state, Mediator<Cell> mediator) {
    this(i, j, state);
    this.mediator = mediator;
  }

  public Cell(Cell original) {
    this(original.i, original.j, original.state, original.mediator);
  }

  public boolean isLive() {
    return state.isLive();
  }

  public boolean isDead() {
    return state.isDead();
  }

  public void changeStateAndNotify() {
    changeState();
    notifyMediator();
  }

  private void changeState() {
    switch (state) {
      case LIVE -> state = CellState.DEAD;
      case DEAD -> state = CellState.LIVE;
      default -> throw new UnsupportedOperationException(String.format("Unknown provided state: %s", state));
    }
  }

  private void notifyMediator() {
    if (mediator != null) {
      mediator.notify(this);
    }
  }

  public void setMediator(Mediator<Cell> mediator) {
    this.mediator = mediator;
  }

  CellState getState() {
    return state;
  }

  @Override
  public String toPrintString() {
    return state.toPrintString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cell cell = (Cell) o;
    return i == cell.i && j == cell.j;
  }

  @Override
  public int hashCode() {
    return Objects.hash(i, j);
  }

  @Override
  public String toString() {
    return String.format("Cell={i=`%d`, j=`%d`, %s}", i, j, state.toString());
  }
}
