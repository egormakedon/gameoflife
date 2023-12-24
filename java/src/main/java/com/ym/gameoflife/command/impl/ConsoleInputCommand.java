package com.ym.gameoflife.command.impl;

import com.ym.gameoflife.command.Command;
import com.ym.gameoflife.controller.application.GameManager;
import com.ym.gameoflife.entity.Grid;
import com.ym.gameoflife.input.BufferedGridParser;
import com.ym.gameoflife.input.ConsoleReader;
import com.ym.gameoflife.output.ConsolePrinter;

/**
 * @author Yahor Makedon
 */
public class ConsoleInputCommand implements Command {
  private final GameManager gameManager = GameManager.getInstance();

  @Override
  public void execute(ConsoleReader consoleReader) {
    ConsolePrinter.printConsoleInput();
    readAndSetupData(consoleReader);
    ConsolePrinter.printSuccess();
    ConsolePrinter.printBorder();
  }

  private void readAndSetupData(ConsoleReader consoleReader) {
    BufferedGridParser parser = new BufferedGridParser();
    readAndParseData(consoleReader, parser);
    Grid grid = parser.buildGrid();
    gameManager.setData(grid);
  }

  private void readAndParseData(ConsoleReader consoleReader, BufferedGridParser parser) {
    ConsolePrinter.printInputN();
    String inputN = consoleReader.readTrimmedLine();
    parser.parseLine(BufferedGridParser.PREFIX_N_LINE + inputN);

    int n = Integer.parseInt(inputN);
    ConsolePrinter.printRowNote(n);
    for (int i = 0; i < n; i++) {
      ConsolePrinter.printInputRow(i);
      String inputRow = consoleReader.readTrimmedLine();
      parser.parseLine(inputRow);
    }
  }
}
