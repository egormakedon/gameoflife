package com.ym.gameoflife.output;

import com.ym.gameoflife.entity.CellState;
import com.ym.gameoflife.entity.Grid;
import com.ym.gameoflife.util.Printable;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Yahor Makedon
 */
public class GridFormatter {
  private static final String LIVE_SYMBOL = "■";
  private static final String SYMBOL_DELIMITER = "";
  private static final String VERTICAL_DASH = "|";
  private static final String DASH = "-";
  private static final String SPACE = " ";
  private boolean griding = true;

  public String format(Grid grid) {
    return formatGrid(grid);
  }
  private String formatGrid(Printable printable) {
    String notFormattedString = printable.toPrintString();
    String[] rows = notFormattedString.split(Grid.DELIMITER);
    CharSequence rowSeparator = rowSeparator(rows.length);

    final int capacity = notFormattedString.length() * 8;
    StringBuilder sb = new StringBuilder(capacity);

    Arrays.stream(rows).forEach(row -> {
      appendRowSeparator(sb, rowSeparator);
      sb.append(formattedRow(row));
    });
    appendRowSeparator(sb, rowSeparator);
    return sb.toString();
  }
  private CharSequence rowSeparator(int rowLength) {
    if (gridingOff()) {
      return "";
    }

    final int capacity = rowLength * 4;
    StringBuilder sb = new StringBuilder(capacity);

    IntStream.range(0, capacity).forEach(i -> sb.append(DASH));
    sb.append(DASH);
    return sb;
  }
  private void appendRowSeparator(StringBuilder sb, CharSequence rowSeparator) {
    if (gridingOn()) {
      sb.append(rowSeparator).append("\n");
    }
  }
  private CharSequence formattedRow(String row) {
    final int capacity = row.length() * 4;
    StringBuilder sb = new StringBuilder(capacity);

    Arrays.stream(row.split(SYMBOL_DELIMITER))
      .map(symbol -> symbol.equals(CellState.LIVE.toPrintString()) ? LIVE_SYMBOL : SPACE)
      .forEach(symbol -> appendCell(sb, symbol));
    appendEndingToFormattedRow(sb);
    return sb;
  }
  private void appendCell(StringBuilder sb, String symbol) {
    if (gridingOn()) {
      appendCellWithGriding(sb, symbol);
    } else {
      appendCellWithoutGriding(sb, symbol);
    }
  }
  private void appendCellWithGriding(StringBuilder sb, String symbol) {
    sb
      .append(VERTICAL_DASH)
      .append(SPACE)
      .append(symbol)
      .append(SPACE);
  }
  private void appendCellWithoutGriding(StringBuilder sb, String symbol) {
    sb
      .append(SPACE)
      .append(symbol);
  }
  private void appendEndingToFormattedRow(StringBuilder sb) {
    if (gridingOn()) {
      sb.append(VERTICAL_DASH);
    }
    sb.append("\n");
  }

  public void gridOn() {
    griding = true;
  }
  public void gridOff() {
    griding = false;
  }
  private boolean gridingOn() {
    return griding;
  }
  private boolean gridingOff() {
    return !gridingOn();
  }
}
