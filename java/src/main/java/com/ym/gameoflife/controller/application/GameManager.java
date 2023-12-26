package com.ym.gameoflife.controller.application;

import com.ym.gameoflife.Runner;
import com.ym.gameoflife.controller.GameOfLifeFacade;
import com.ym.gameoflife.entity.Grid;
import com.ym.gameoflife.output.ConsolePrinter;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Yahor Makedon
 */
public final class GameManager {
  private static final String PRE_LOADED_DATA_FILE_EXTENSION = ".txt";
  private static final Set<String> preLoadedDataFileNames = createPreLoadedDataFileNames();
  private static final GameManager instance = new GameManager();

  private final ConfigManager configManager = new ConfigManager();
  private final TaskManager taskManager = new TaskManager();
  private final GameOfLifeFacade gameOfLifeFacade = new GameOfLifeFacade();

  private GameManager() {
  }

  private static Set<String> createPreLoadedDataFileNames() {
    List<String> list = Arrays.asList(
      "block",
      "beehive",
      "loaf",
      "boat",
      "tub",
      "blinker",
      "toad",
      "beacon",
      "pulsar",
      "cross",
      "clock",
      "octagon",
      "kok's_galaxy",
      "glider",
      "lwss"
    );
    return new LinkedHashSet<>(list);
  }
  public static GameManager getInstance() {
    return instance;
  }
  public static Stream<String> streamOfPreLoadedDataFileNames() {
    return preLoadedDataFileNames.stream();
  }
  public static Optional<String> getPreLoadedDataFileName(String input) {
    if (input == null || input.isEmpty()) {
      return Optional.empty();
    }
    return preLoadedDataFileNames.contains(input)
      ? Optional.of(input + PRE_LOADED_DATA_FILE_EXTENSION) : Optional.empty();
  }

  public void run() {
    Grid grid = getData();
    gameOfLifeFacade.setGrid(grid);
    taskManager.runNewTask(gameOfLifeFacade);
  }
  public void exit() {
    taskManager.finishTaskWithoutWaiting();
    Runner.finish();
  }
  public void restart() {
    taskManager.finishTaskWithWaiting();
    configManager.clearConfig();
    gameOfLifeFacade.flushData();
    ConsolePrinter.clearConsole();
  }
  public void gridOn() {
    gameOfLifeFacade.gridOn();
  }
  public void gridOff() {
    gameOfLifeFacade.gridOff();
  }
  public void setIterationDelayMs(int delayMs) {
    taskManager.setIterationDelayMs(delayMs);
  }

  public void setMode(String input) {
    configManager.setMode(input);
  }
  public Mode getMode() {
    return configManager.getMode();
  }
  public void setData(Grid grid) {
    configManager.setData(grid);
  }
  public Grid getData() {
    return configManager.getData();
  }
}
