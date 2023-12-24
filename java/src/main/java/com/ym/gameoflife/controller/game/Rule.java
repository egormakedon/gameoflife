package com.ym.gameoflife.controller.game;

import com.ym.gameoflife.entity.Cell;

/**
 * @author Yahor Makedon
 */
public enum Rule {
  /**
   * Any live cell with fewer than two live neighbours dies, as if by underpopulation.
   */
  UNDERPOPULATION {
    @Override
    public boolean check(Cell cell, int numberOfLive) {
      return cell.isLive() && numberOfLive < 2;
    }
  },

  /**
   * Any live cell with two or three live neighbours lives on to the next generation.
   */
  NEXT_GENERATION {
    @Override
    public boolean check(Cell cell, int numberOfLive) {
      return cell.isLive() && (numberOfLive == 2 || numberOfLive == 3);
    }
  },

  /**
   * Any live cell with more than three live neighbours dies, as if by overpopulation.
   */
  OVERPOPULATION {
    @Override
    public boolean check(Cell cell, int numberOfLive) {
      return cell.isLive() && numberOfLive > 3;
    }
  },

  /**
   * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
   */
  REPRODUCTION {
    @Override
    public boolean check(Cell cell, int numberOfLive) {
      return cell.isDead() && numberOfLive == 3;
    }
  };

  public abstract boolean check(Cell cell, int numberOfLive);
}
