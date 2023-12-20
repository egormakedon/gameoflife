package com.ym.gameoflife.entity;

import com.ym.gameoflife.util.Event;
import com.ym.gameoflife.util.Mediator;
import com.ym.gameoflife.util.Printable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Yahor Makedon
 */
public class Grid implements Mediator<Cell>, Printable {
  public static final String DELIMITER = "\n";

  private final Cell[][] grid;
  private final Set<Cell> liveCells;
  private final Set<Cell> pendingUpdateCells;

  public Grid(int n, Iterable<Cell> cells) {
    grid = new Cell[n][n];
    liveCells = new HashSet<>();
    pendingUpdateCells = new HashSet<>();
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

  public Stream<Cell> streamOfLiveCells() {
    return liveCells.stream();
  }

  public Stream<Cell> streamOfPendingUpdateCells() {
    return pendingUpdateCells.stream();
  }

  public Stream<Cell> streamOfNeighbourCellsFrom(Cell cell) {
    return Stream.of(
      getTopLeftCellFrom(cell),
      getTopCellFrom(cell),
      getTopRightCellFrom(cell),
      getLeftCellFrom(cell),
      getRightCellFrom(cell),
      getBottomLeftCellFrom(cell),
      getBottomCellFrom(cell),
      getBottomRightCellFrom(cell)
    );
  }

  private Cell getTopLeftCellFrom(Cell cell) {
    return getCell(cell.i-1, cell.j-1);
  }

  private Cell getTopCellFrom(Cell cell) {
    return getCell(cell.i-1, cell.j);
  }

  private Cell getTopRightCellFrom(Cell cell) {
    return getCell(cell.i-1, cell.j+1);
  }

  private Cell getLeftCellFrom(Cell cell) {
    return getCell(cell.i, cell.j-1);
  }

  private Cell getRightCellFrom(Cell cell) {
    return getCell(cell.i, cell.j+1);
  }

  private Cell getBottomLeftCellFrom(Cell cell) {
    return getCell(cell.i+1, cell.j-1);
  }

  private Cell getBottomCellFrom(Cell cell) {
    return getCell(cell.i+1, cell.j);
  }

  private Cell getBottomRightCellFrom(Cell cell) {
    return getCell(cell.i+1, cell.j+1);
  }

  private Cell getCell(int i, int j) {
    i = checkAndGetCorrectIndex(i);
    j = checkAndGetCorrectIndex(j);
    return grid[i][j];
  }

  int checkAndGetCorrectIndex(int index) {
    if (index >= 0 && index < grid.length) {
      return index;
    } else if (index == -1) {
      return grid.length - 1;
    } else if (index == grid.length) {
      return 0;
    } else {
      throw new IllegalArgumentException(String.format("Provided index=`%d` is incorrect", index));
    }
  }

  @Override
  public void notify(Cell sender, Event event) {
    switch (event) {
      case PENDING_UPDATE -> handlePendingUpdate(sender);
      case COMMIT_UPDATE -> handleCommitUpdate(sender);
      default -> throw new UnsupportedOperationException(String.format("Event=`%s` is not supported", event));
    }
  }

  private void handlePendingUpdate(Cell cell) {
    pendingUpdateCells.add(cell);
  }

  private void handleCommitUpdate(Cell cell) {
    if (cell.isLive()) {
      liveCells.add(cell);
    } else if (cell.isDead()) {
      liveCells.remove(cell);
    }
  }

  public void flush() {
    pendingUpdateCells.clear();
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

  Set<Cell> getPendingUpdateCells() {
    return Set.copyOf(pendingUpdateCells);
  }
}
