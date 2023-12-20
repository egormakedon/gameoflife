package com.ym.gameoflife.output;

import com.ym.gameoflife.entity.Grid;
import com.ym.gameoflife.util.Printable;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Yahor Makedon
 */
public class GridFormatter {
  private static final String SYMBOL_DELIMITER = "";
  private static final String VERTICAL_DASH = "|";
  private static final String DASH = "-";
  private static final String SPACE = " ";

  public String format(Grid grid) {
    return formatGrid(grid);
  }

  private String formatGrid(Printable printable) {
    String notFormattedString = printable.toPrintString();
    String[] rows = notFormattedString.split(Grid.DELIMITER);
    CharSequence rowSeparator = rowSeparator(rows.length);

    final int capacity = notFormattedString.length() * 8;
    StringBuilder sb = new StringBuilder(capacity);

    Arrays.stream(rows).forEach(
      row -> sb.append(rowSeparator).append("\n").append(formattedRow(row)));
    sb.append(rowSeparator).append("\n");
    return sb.toString();
  }

  private CharSequence rowSeparator(int rowLength) {
    final int capacity = rowLength * 4;
    StringBuilder sb = new StringBuilder(capacity);

    IntStream.range(0, capacity).forEach(i -> sb.append(DASH));
    sb.append(DASH);
    return sb;
  }

  private CharSequence formattedRow(String row) {
    final int capacity = row.length() * 4;
    StringBuilder sb = new StringBuilder(capacity);

    Arrays.stream(row.split(SYMBOL_DELIMITER)).forEach(
      symbol -> sb.append(VERTICAL_DASH).append(SPACE).append(symbol).append(SPACE));
    sb.append(VERTICAL_DASH).append("\n");
    return sb;
  }
}
