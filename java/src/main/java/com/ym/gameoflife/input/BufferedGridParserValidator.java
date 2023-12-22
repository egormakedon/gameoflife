package com.ym.gameoflife.input;

import java.util.regex.Pattern;

/**
 * @author Yahor Makedon
 */
public enum BufferedGridParserValidator {
  ROW_N("n=\\d+"),
  ROW_CELLS("[01]+");

  private final Pattern pattern;

  BufferedGridParserValidator(String regex) {
    pattern = Pattern.compile(regex);
  }

  public boolean validate(String line) {
    if (line == null || line.isEmpty()) {
      return false;
    }
    return pattern.asMatchPredicate().test(line);
  }
}
