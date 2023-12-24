package com.ym.gameoflife.command.impl;

import com.ym.gameoflife.command.Command;
import com.ym.gameoflife.controller.application.GameManager;
import com.ym.gameoflife.input.ConsoleReader;
import com.ym.gameoflife.output.ConsolePrinter;

import java.util.concurrent.TimeUnit;

/**
 * @author Yahor Makedon
 */
public class RunCommand implements Command {
  private static final int DELAY_BEFORE_RUN_MS = 3000;
  private final GameManager gameManager = GameManager.getInstance();

  @Override
  public void execute(ConsoleReader consoleReader) {
    ConsolePrinter.printListenNote();
    delayBeforeRun();
    gameManager.run();
  }

  private void delayBeforeRun() {
    try {
      TimeUnit.MILLISECONDS.sleep(DELAY_BEFORE_RUN_MS);
    } catch (InterruptedException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
