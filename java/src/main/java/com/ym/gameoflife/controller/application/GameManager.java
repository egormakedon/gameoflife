package com.ym.gameoflife.controller.application;

import com.ym.gameoflife.entity.Grid;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yahor Makedon
 */
public final class GameManager {
  public static final String DEFAULT_FILE_PATH;
  private static final GameManager instance = new GameManager();
  private final Map<ConfigParameter, Object> config = new HashMap<>();

  static {
    DEFAULT_FILE_PATH = Paths.get("").toAbsolutePath().normalize() + "/java/data.txt";
  }

  private GameManager() {
  }

  public static GameManager getInstance() {
    return instance;
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

  public Grid getData() {
    return (Grid) config.get(ConfigParameter.DATA);
  }
}
