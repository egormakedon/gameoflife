package com.ym.gameoflife.input;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

/**
 * @author Yahor Makedon
 */
public class FileReader implements AutoCloseable {
  private final Stream<String> lineStream;

  public FileReader(String filePath) {
    try {
      Path path = Paths.get(filePath);
      this.lineStream = Files.lines(path);
    } catch (NoSuchFileException e) {
      throw new IllegalArgumentException(String.format("Invalid filePath=`%s`", filePath), e);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public Stream<String> streamOfLines() {
    return lineStream;
  }

  @Override
  public void close() {
    lineStream.close();
  }
}
