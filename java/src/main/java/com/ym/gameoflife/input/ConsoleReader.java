package com.ym.gameoflife.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Yahor Makedon
 */
public class ConsoleReader implements AutoCloseable {
  private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  public String readTrimmedLine() {
    try {
      String line = reader.readLine();
      return line.trim();
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  @Override
  public void close() throws IOException {
    reader.close();
  }
}
