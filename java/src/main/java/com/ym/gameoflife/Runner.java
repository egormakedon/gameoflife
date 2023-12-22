package com.ym.gameoflife;

import com.ym.gameoflife.command.CommandType;
import com.ym.gameoflife.input.ConsoleReader;

import java.util.concurrent.TimeUnit;

/**
 * @author Yahor Makedon
 */
public class Runner {
  private static volatile boolean isRunning = true;

  public static void main(String[] args) throws Exception {
    try (ConsoleReader consoleReader = new ConsoleReader()) {
      while (isRunning) {
        try {
          CommandType.BEGIN.execute(consoleReader);
        } catch (Exception e) {
          System.err.println(e.getMessage() + "\n");
          TimeUnit.MILLISECONDS.sleep(500);
        }
      }
    }
  }

  public static void finish() {
    isRunning = false;
  }
}
