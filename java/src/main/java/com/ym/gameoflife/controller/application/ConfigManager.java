package com.ym.gameoflife.controller.application;

import com.ym.gameoflife.entity.Grid;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yahor Makedon
 */
final class ConfigManager {
  private final Map<ConfigParameter, Object> config = new HashMap<>();

  ConfigManager() {
  }

  void clearConfig() {
    config.clear();
  }

  void setMode(String input) {
    config.put(ConfigParameter.MODE, Mode.of(input));
  }

  Mode getMode() {
    return (Mode) config.get(ConfigParameter.MODE);
  }

  void setData(Grid grid) {
    config.put(ConfigParameter.DATA, grid);
  }

  Grid getData() {
    return (Grid) config.get(ConfigParameter.DATA);
  }
}
