package com.ym.gameoflife.controller.application;

import com.ym.gameoflife.controller.GameOfLifeFacade;
import com.ym.gameoflife.entity.Grid;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * @author Yahor Makedon
 */
public final class GameManager {
  private static final String PRE_LOADED_DATA_FILE_EXTENSION = ".txt";
  private static final Set<String> preLoadedDataFileNames = createPreLoadedDataFileNames();
  private static final GameManager instance = new GameManager();
  private final Map<ConfigParameter, Object> config = new HashMap<>();
  private final GameOfLifeFacade gameOfLifeFacade = new GameOfLifeFacade();
  private Task task;

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
    task = new Task();
    startTask(task);
  }

  private Grid getData() {
    return (Grid) config.get(ConfigParameter.DATA);
  }

  private void startTask(Task task) {
    Thread thread = new Thread(task);
    thread.setDaemon(true);
    thread.start();
  }

  public void setMode(String input) {
    config.put(ConfigParameter.MODE, Mode.of(input));
  }

  public Mode getMode() {
    return (Mode) config.get(ConfigParameter.MODE);
  }

  public void setData(Grid grid) {
    config.put(ConfigParameter.DATA, grid);
  }

  private final class Task implements Runnable {
    private static final int DEFAULT_DELAY_MS = 1000;
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    private int delayMs = DEFAULT_DELAY_MS;

    @Override
    public void run() {
      try {
        gameOfLifeFacade.print();
        while (isRunning.get()) {
          TimeUnit.MILLISECONDS.sleep(delayMs);
          gameOfLifeFacade.iterateAndPrint();
        }
      } catch (Exception e) {
        throw new RuntimeException(e.getMessage(), e);
      }
    }
  }
}
