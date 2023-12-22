package com.ym.gameoflife.command.impl;

import com.ym.gameoflife.command.Command;
import com.ym.gameoflife.input.ConsoleReader;
import com.ym.gameoflife.output.ConsolePrinter;

/**
 * @author Yahor Makedon
 */
public class BeginCommand implements Command {
  @Override
  public void execute(ConsoleReader consoleReader) {
    ConsolePrinter.printTitle();
  }
}
