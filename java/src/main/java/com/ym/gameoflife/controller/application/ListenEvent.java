package com.ym.gameoflife.controller.application;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Optional;

/**
 * @author Yahor Makedon
 */
public enum ListenEvent {
  EXIT("e"),
  RESTART("r"),
  PAUSE("p"),
  CONTINUE("c"),
  GRID_ON("gn"),
  GRID_OFF("gf"),
  DELAY("d");

  private final String code;

  ListenEvent(String code) {
    this.code = code;
  }

  public static Optional<ListenEvent> of(String input) {
    if (input == null) {
      return Optional.empty();
    }

    if (input.startsWith(DELAY.code)) {
      if (input.length() == 1 || !NumberUtils.isParsable(input.substring(1))) {
        return Optional.empty();
      }
      int value = NumberUtils.toInt(input.substring(1));
      return value > 0 ? Optional.of(DELAY) : Optional.empty();
    }

    return switch (input) {
      case "e" -> Optional.of(EXIT);
      case "r" -> Optional.of(RESTART);
      case "p" -> Optional.of(PAUSE);
      case "c" -> Optional.of(CONTINUE);
      case "gn" -> Optional.of(GRID_ON);
      case "gf" -> Optional.of(GRID_OFF);
      default -> Optional.empty();
    };
  }

  String getCode() {
    return code;
  }
}
