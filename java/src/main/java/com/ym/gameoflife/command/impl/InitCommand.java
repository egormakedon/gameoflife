package com.ym.gameoflife.command.impl;

import com.ym.gameoflife.command.Command;
import com.ym.gameoflife.command.CommandType;
import com.ym.gameoflife.controller.application.GameManager;
import com.ym.gameoflife.controller.application.Mode;
import com.ym.gameoflife.input.ConsoleReader;
import com.ym.gameoflife.output.ConsolePrinter;

/**
 * @author Yahor Makedon
 */
public class InitCommand implements Command {
  private final GameManager gameManager = GameManager.getInstance();

  @Override
  public void execute(ConsoleReader consoleReader) {
    initMode(consoleReader);
    initData(consoleReader);
  }

  private void initMode(ConsoleReader consoleReader) {
    ConsolePrinter.printChooseMode();
    String input = consoleReader.readTrimmedLine();
    gameManager.setMode(input);
    ConsolePrinter.printBorder();
  }

  private void initData(ConsoleReader consoleReader) {
    Mode mode = gameManager.getMode();
    switch (mode) {
      case CONSOLE_INPUT_MODE -> CommandType.CONSOLE_INPUT.execute(consoleReader);
      case FILE_INPUT_MODE -> CommandType.FILE_INPUT.execute(consoleReader);
      default -> throw new UnsupportedOperationException(String.format("Mode=`%s` is not supported", mode));
    }
  }
}
