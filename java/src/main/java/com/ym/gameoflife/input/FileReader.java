package com.ym.gameoflife.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author Yahor Makedon
 */
public class FileReader implements AutoCloseable {
  private final Stream<String> lineStream;

  public FileReader(String filePath) throws IOException {
    Path path = Paths.get(filePath);
    this.lineStream = Files.lines(path);
  }

  public Stream<String> streamOfLines() {
    return lineStream;
  }

  @Override
  public void close() throws Exception {
    lineStream.close();
  }
}
