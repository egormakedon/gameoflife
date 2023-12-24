package com.ym.gameoflife.output;

import com.ym.gameoflife.entity.Grid;

import java.nio.file.Paths;

/**
 * @author Yahor Makedon
 */
public class ConsolePrinter {
  private static final String BORDER = "=========================";
  private static final String TITLE = "Conway's Game of Life";
  private static final String CONSOLE_INPUT = "Console Input [0]";
  private static final String FILE_INPUT = "File Input [1]";
  private static final String CHOOSE_MODE = "Choose Mode (default 0): ";
  private static final String SUCCESS = "Success!!";
  private static final String INPUT_N = "Input N [3-20]: ";
  private static final String ROW_NOTE = "!!Note: Should be provided `%d` Rows for `%d` Cells (In Total `%d` Cells). Row example: `01`, where `0` - Cell DEAD, `1` - Cell LIVE";
  private static final String INPUT_ROW = "Input Row %d: ";
  private static final String INPUT_PATH_TO_FILE;
  private static final String FILE_STRUCTURE_EXAMPLE;

  static {
    String defaultPath = Paths.get("").toAbsolutePath().normalize() + "/java/data.txt";
    INPUT_PATH_TO_FILE = String.format("Input path to `data.txt` file (default %s): ", defaultPath);

    String fileStructure = """
      n=5
      00000
      00000
      01110
      00000
      00000
      """;
    FILE_STRUCTURE_EXAMPLE = "File structure example:\n" + "\"\"\"\n" + fileStructure + "\"\"\"";
  }

  private final GridFormatter formatter = new GridFormatter();

  public static void printBorder() {
    System.out.println(BORDER + "\n");
  }

  public static void printTitle() {
    final int capacity = 100;
    StringBuilder sb = new StringBuilder(capacity);
    sb
      .append(BORDER).append("\n")
      .append(TITLE).append("\n")
      .append(BORDER).append("\n");

    System.out.println(sb);
  }

  public static void printChooseMode() {
    final int capacity = 100;
    StringBuilder sb = new StringBuilder(capacity);
    sb
      .append(BORDER).append("\n")
      .append(CONSOLE_INPUT).append("\n")
      .append(FILE_INPUT).append("\n")
      .append(CHOOSE_MODE);

    System.out.print(sb);
  }

  public static void printInputPathToFile() {
    final int capacity = 100;
    StringBuilder sb = new StringBuilder(capacity);
    sb
      .append(BORDER).append("\n")
      .append(FILE_INPUT).append("\n")
      .append(FILE_STRUCTURE_EXAMPLE).append("\n")
      .append(INPUT_PATH_TO_FILE);

    System.out.print(sb);
  }

  public static void printSuccess() {
    System.out.println(SUCCESS);
  }

  public static void printConsoleInput() {
    final int capacity = 100;
    StringBuilder sb = new StringBuilder(capacity);
    sb
      .append(BORDER).append("\n")
      .append(CONSOLE_INPUT).append("\n");

    System.out.print(sb);
  }

  public static void printInputN() {
    System.out.print(INPUT_N);
  }

  public static void printRowNote(int n) {
    System.out.printf(ROW_NOTE + "%n", n, n, n*n);
  }

  public static void printInputRow(int rowIndex) {
    System.out.printf(INPUT_ROW, rowIndex+1);
  }

  public void print(Grid grid) {
    System.out.println(formatter.format(grid));
  }
}
