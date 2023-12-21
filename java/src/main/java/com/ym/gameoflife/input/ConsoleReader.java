package com.ym.gameoflife.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Yahor Makedon
 */
public class ConsoleReader implements AutoCloseable {
  private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

  public String readTrimmedLine() throws IOException {
    String line = reader.readLine();
    return line.trim();
  }

  @Override
  public void close() throws Exception {
    reader.close();
  }
}
