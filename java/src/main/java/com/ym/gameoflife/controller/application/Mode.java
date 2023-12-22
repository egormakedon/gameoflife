package com.ym.gameoflife.controller.application;

import java.util.NoSuchElementException;

/**
 * @author Yahor Makedon
 */
public enum Mode {
  CONSOLE_INPUT_MODE("0"),
  FILE_INPUT_MODE("1");

  private final String code;

  Mode(String code) {
    this.code = code;
  }

  public static Mode of(String code) {
    if (code != null && (code.isEmpty() || code.equals(CONSOLE_INPUT_MODE.code))) {
      return CONSOLE_INPUT_MODE;
    } else if (code != null && code.equals(FILE_INPUT_MODE.code)) {
      return FILE_INPUT_MODE;
    } else {
      throw new NoSuchElementException(String.format("No such Mode under code=`%s`", code));
    }
  }

  String getCode() {
    return code;
  }
}
