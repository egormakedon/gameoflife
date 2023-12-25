package com.ym.gameoflife.input;

import com.ym.gameoflife.controller.application.GameManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Yahor Makedon
 */
public class FileReader implements AutoCloseable {
  private final Stream<String> lineStream;

  public FileReader(String filePath) {
    try {
      Optional<String> preLoadedDataFileName = GameManager.getPreLoadedDataFileName(filePath);
      this.lineStream = preLoadedDataFileName.isPresent()
        ? lineStreamFromPreLoadedDataFile(preLoadedDataFileName.get()) : lineStreamFromExternalDataFile(filePath);
    } catch (NoSuchFileException e) {
      throw new IllegalArgumentException(String.format("Invalid filePath=`%s`", filePath), e);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  private static Stream<String> lineStreamFromPreLoadedDataFile(String preLoadedDataFileName) {
    InputStream inputStream = Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(preLoadedDataFileName));
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    return bufferedReader.lines();
  }

  private static Stream<String> lineStreamFromExternalDataFile(String filePath) throws IOException {
    Path path = Paths.get(filePath);
    return Files.lines(path);
  }

  public Stream<String> streamOfLines() {
    return lineStream;
  }

  @Override
  public void close() {
    lineStream.close();
  }
}
