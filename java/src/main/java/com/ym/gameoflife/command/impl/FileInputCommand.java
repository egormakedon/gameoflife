package com.ym.gameoflife.command.impl;

import com.ym.gameoflife.command.Command;
import com.ym.gameoflife.controller.application.GameManager;
import com.ym.gameoflife.entity.Grid;
import com.ym.gameoflife.input.ConsoleReader;
import com.ym.gameoflife.input.GridFileReaderFacade;
import com.ym.gameoflife.output.ConsolePrinter;

/**
 * @author Yahor Makedon
 */
public class FileInputCommand implements Command {
  private final GameManager gameManager = GameManager.getInstance();

  @Override
  public void execute(ConsoleReader consoleReader) {
    ConsolePrinter.printInputPathToFile();
    readFileAndSetupData(consoleReader);
    ConsolePrinter.printSuccess();
    ConsolePrinter.printBorder();
  }

  private void readFileAndSetupData(ConsoleReader consoleReader) {
    String filePath = consoleReader.readTrimmedLine();
    GridFileReaderFacade gridFileReaderFacade = new GridFileReaderFacade();
    Grid grid = gridFileReaderFacade.read(filePath);
    gameManager.setData(grid);
  }
}
