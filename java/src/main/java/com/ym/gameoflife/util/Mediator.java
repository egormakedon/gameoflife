package com.ym.gameoflife.util;

/**
 * @author Yahor Makedon
 */
public interface Mediator<T> {
  void notify(T sender);
}
