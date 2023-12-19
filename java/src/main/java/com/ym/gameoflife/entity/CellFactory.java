package com.ym.gameoflife.entity;

import com.ym.gameoflife.util.Mediator;

/**
 * @author Yahor Makedon
 */
public final class CellFactory {
  private final Mediator<Cell> mediator;

  private CellFactory(Mediator<Cell> mediator) {
    this.mediator = mediator;
  }

  public static CellFactory getInstance() {
    return new CellFactory(null);
  }

  public static CellFactory getInstance(Mediator<Cell> mediator) {
    return new CellFactory(mediator);
  }

  public Cell make(int i, int j, CellState state) {
    return new Cell(i, j, state, mediator);
  }

  public Cell clone(Cell original) {
    return new Cell(original);
  }
}
