package com.ym.gameoflife.command.impl;

import com.ym.gameoflife.command.Command;
import com.ym.gameoflife.controller.application.GameManager;
import com.ym.gameoflife.controller.application.ListenEvent;
import com.ym.gameoflife.input.ConsoleReader;

import java.util.Optional;

/**
 * @author Yahor Makedon
 */
public class ListenCommand implements Command {
  private final GameManager gameManager = GameManager.getInstance();
  private boolean isRunning;

  @Override
  public void execute(ConsoleReader consoleReader) {
    isRunning = true;

    while (isRunning) {
      String input = consoleReader.readTrimmedLine();
      Optional<ListenEvent> listenEventOptional = ListenEvent.of(input);
      if (listenEventOptional.isEmpty()) {
        continue;
      }

      ListenEvent listenEvent = listenEventOptional.get();
      switch (listenEvent) {
        case EXIT -> handleExitEvent();
        case RESTART -> handleRestartEvent();
//        case PAUSE -> handlePauseEvent();
//        case CONTINUE -> handleContinueEvent();
        case GRID_ON -> handleGridOnEvent();
        case GRID_OFF -> handleGridOffEvent();
//        case DELAY -> handleDelayEvent();
        default -> throw new UnsupportedOperationException(String.format("ListenEvent=`%s` is not supported", listenEvent));
      }
    }
  }

  private void finishListenCommand() {
    isRunning = false;
  }

  private void handleExitEvent() {
    gameManager.exit();
    finishListenCommand();
  }

  private void handleRestartEvent() {
    gameManager.restart();
    finishListenCommand();
  }

  private void handleGridOnEvent() {
    gameManager.gridOn();
  }

  private void handleGridOffEvent() {
    gameManager.gridOff();
  }
}
