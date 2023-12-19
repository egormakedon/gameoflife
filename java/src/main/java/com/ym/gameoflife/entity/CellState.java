package com.ym.gameoflife.entity;

import com.ym.gameoflife.util.Printable;

import java.util.NoSuchElementException;

/**
 * @author Yahor Makedon
 */
public enum CellState implements Printable {
  LIVE(1),
  DEAD(0);

  private final int value;

  CellState(int value) {
    this.value = value;
  }

  public static CellState of(int value) {
    if (value == LIVE.value) {
      return LIVE;
    } else if (value == DEAD.value) {
      return DEAD;
    } else {
      throw new NoSuchElementException(String.format("No such CellState under value=%d", value));
    }
  }

  public boolean isLive() {
    return this == CellState.LIVE;
  }

  public boolean isDead() {
    return this == CellState.DEAD;
  }

  int getValue() {
    return value;
  }

  @Override
  public String toPrintString() {
    return String.valueOf(value);
  }

  @Override
  public String toString() {
    return String.format("CellState=`%s(%d)`", name(), value);
  }
}
