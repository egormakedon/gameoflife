package com.ym.gameoflife.input;

import com.ym.gameoflife.entity.Cell;
import com.ym.gameoflife.entity.CellFactory;
import com.ym.gameoflife.entity.CellState;
import com.ym.gameoflife.entity.Grid;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yahor Makedon
 */
public class BufferedGridParser {
  private static final String EQUAL_DELIMITER = "=";
  private static final String SYMBOL_DELIMITER = "";
  private static final int INIT_N = -1;
  private static final int MIN_N = 3;
  private static final int MAX_N = 20;
  private static final int INIT_ROW_INDEX = -1;

  private static final CellFactory factory = CellFactory.getInstance();
  private final Set<Cell> cells = new HashSet<>();
  private int n = INIT_N;
  private int rowIndex = INIT_ROW_INDEX;

  public void parseLine(String line) {
    if (BufferedGridParserValidator.ROW_N.validate(line)) {
      parseNLine(line);
    } else if (BufferedGridParserValidator.ROW_CELLS.validate(line)) {
      parseCellsLine(line);
    } else {
      throw new IllegalArgumentException(String.format("Could not parse incorrect line=`%s`", line));
    }
  }

  private void parseNLine(String line) {
    if (this.n != INIT_N) {
      throw new IllegalArgumentException(String.format("N=`%d` has been already initialized", this.n));
    }

    int n = Integer.parseInt(line.split(EQUAL_DELIMITER)[1]);
    if (n < MIN_N || n > MAX_N) {
      throw new IllegalArgumentException(String.format("Incorrect n range=`%d`. The n valid range is [3, 20]", n));
    }
    this.n = n;
  }

  void parseCellsLine(String line) {
    if (n == INIT_N) {
      throw new IllegalArgumentException("Incorrect structure. The n has not been initialized");
    }

    String[] cellValues = line.split(SYMBOL_DELIMITER);
    if (cellValues.length != n) {
      throw new IllegalArgumentException(String.format("Incorrect length=`%d` of `%s`. The n=`%d`", line.length(), line, n));
    }

    int i = ++rowIndex;
    for (int j = 0; j < cellValues.length; j++) {
      int cellValue = Integer.parseInt(cellValues[j]);
      cells.add(factory.make(i, j, CellState.of(cellValue)));
    }
  }

  public Grid buildGrid() {
    if (cells.size() != n*n) {
      throw new RuntimeException(String.format("Incorrect number of parsed cells, actual=`%d`, expected=`%d`", cells.size(), n*n));
    }
    return new Grid(n, cells);
  }

  void setN(int n) {
    this.n = n;
  }

  int getN() {
    return n;
  }
}
