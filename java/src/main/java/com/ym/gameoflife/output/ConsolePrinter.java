package com.ym.gameoflife.output;

import com.ym.gameoflife.entity.Grid;

/**
 * @author Yahor Makedon
 */
public class ConsolePrinter {
  private final GridFormatter formatter = new GridFormatter();

  public void print(Grid grid) {
    System.out.println(formatter.format(grid));
  }
}
