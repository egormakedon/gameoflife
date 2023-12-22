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
  private static final String INPUT_PATH_TO_FILE;

  static {
    String defaultPath = Paths.get("").toAbsolutePath().normalize() + "/java/data.txt";
    INPUT_PATH_TO_FILE = String.format("Input path to `data.txt` file (default %s): ", defaultPath);
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
      .append(INPUT_PATH_TO_FILE);

    System.out.print(sb);
  }

  public static void printSuccess() {
    System.out.println(SUCCESS);
  }

  public void print(Grid grid) {
    System.out.println(formatter.format(grid));
  }
}
