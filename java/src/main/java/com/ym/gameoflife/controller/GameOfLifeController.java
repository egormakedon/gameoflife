package com.ym.gameoflife.controller;

import com.ym.gameoflife.entity.Cell;
import com.ym.gameoflife.entity.Grid;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Yahor Makedon
 */
public class GameOfLifeController {
  private Grid grid;

  public void iterate() {
    checkGrid();
    doIteration();
  }

  private void checkGrid() {
    Objects.requireNonNull(grid);
  }

  private void doIteration() {
    prepareAndProcessCellsBeforeCommit();
//    commitUpdate();
  }

  private void prepareAndProcessCellsBeforeCommit() {
    Set<Cell> processedCellsCache = new HashSet<>();
    grid.streamOfLiveCells()
      .flatMap(cell -> Stream.concat(Stream.of(cell), grid.streamOfNeighbourCellsFrom(cell)))
      .forEach(cell -> processCellBeforeCommit(cell, processedCellsCache));
  }

  private void processCellBeforeCommit(Cell cell, Set<Cell> processedCellsCache) {
    if (shouldNotProceed(cell, processedCellsCache)) {
      return;
    }
    processedCellsCache.add(cell);

    Stream<Cell> neighbourCellStream = grid.streamOfNeighbourCellsFrom(cell);
    int numberOfLive = calculateNumberOfLiveFrom(neighbourCellStream);

    if (cellShouldBeUpdated(cell, numberOfLive)) {
      cell.pendingUpdate();
    }
  }

  private boolean shouldNotProceed(Cell cell, Set<Cell> processedCellsCache) {
    return processedCellsCache.contains(cell);
  }

  int calculateNumberOfLiveFrom(Stream<Cell> cellStream) {
    return (int) cellStream.filter(Cell::isLive).count();
  }

  boolean cellShouldBeUpdated(Cell cell, int numberOfLive) {
    return Rule.UNDERPOPULATION.check(cell, numberOfLive)
      || Rule.OVERPOPULATION.check(cell, numberOfLive)
      || Rule.REPRODUCTION.check(cell, numberOfLive);
  }

  public void setGrid(Grid grid) {
    this.grid = grid;
  }
}
