package com.ym.gameoflife.entity;

import com.ym.gameoflife.util.Mediator;
import com.ym.gameoflife.util.Printable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yahor Makedon
 */
public class Grid implements Mediator<Cell>, Printable {
  public static final String DELIMITER = "\n";

  private final Cell[][] grid;
  private final Set<Cell> liveCells;

  public Grid(int n, Iterable<Cell> cells) {
    grid = new Cell[n][n];
    liveCells = new HashSet<>();
    init(cells);
  }

  private void init(Iterable<Cell> cells) {
    cells.forEach(cell -> {
      cell.setMediator(this);
      grid[cell.i][cell.j] = cell;
      if (cell.isLive()) {
        liveCells.add(cell);
      }
    });
  }

  @Override
  public void notify(Cell sender) {
  }

  @Override
  public String toPrintString() {
    StringBuilder sb = new StringBuilder(grid.length * grid.length);
    for (Cell[] row : grid) {
      sb.append(Arrays.stream(row).map(Cell::toPrintString).collect(Collectors.joining()));
      sb.append(DELIMITER);
    }
    return sb.toString();
  }

  Cell[][] getGrid() {
    return Arrays.stream(grid).map(row -> Arrays.copyOf(row, row.length)).toArray(Cell[][]::new);
  }

  Set<Cell> getLiveCells() {
    return Set.copyOf(liveCells);
  }
}
