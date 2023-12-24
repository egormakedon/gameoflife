package com.ym.gameoflife.controller;

import com.ym.gameoflife.controller.game.GameOfLifeController;
import com.ym.gameoflife.entity.Grid;
import com.ym.gameoflife.output.ConsolePrinter;

/**
 * @author Yahor Makedon
 */
public class GameOfLifeFacade {
  private final GameOfLifeController controller = new GameOfLifeController();
  private final ConsolePrinter printer = new ConsolePrinter();
  private Grid grid;
  private int iterationCounter;

  public void setGrid(Grid grid) {
    this.grid = grid;
    controller.setGrid(grid);
  }

  public void iterateAndPrint() {
    controller.iterate();
    ++iterationCounter;
    print();
  }

  public void print() {
    ConsolePrinter.clearConsole();
    ConsolePrinter.printListenNote();
    ConsolePrinter.printIteration(iterationCounter);
    printer.print(grid);
  }

  public void flush() {
    setGrid(null);
    iterationCounter = 0;
  }
}
