package com.ym.gameoflife.input;

import com.ym.gameoflife.entity.Grid;

/**
 * @author Yahor Makedon
 */
public class GridFileReaderFacade {
  public Grid read(String filePath) throws Exception {
    try (FileReader fileReader = new FileReader(filePath)) {
      BufferedGridParser parser = new BufferedGridParser();
      fileReader.streamOfLines().forEach(parser::parseLine);
      return parser.buildGrid();
    }
  }
}
