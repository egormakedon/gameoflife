package com.ym.gameoflife.command;

import com.ym.gameoflife.input.ConsoleReader;

/**
 * @author Yahor Makedon
 */
public interface Command {
  void execute(ConsoleReader consoleReader);
}
