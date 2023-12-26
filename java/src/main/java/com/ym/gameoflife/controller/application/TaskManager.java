package com.ym.gameoflife.controller.application;

import com.ym.gameoflife.controller.GameOfLifeFacade;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Yahor Makedon
 */
final class TaskManager {
  private Task task;
  private Thread taskThread;

  TaskManager() {
  }

  void runNewTask(GameOfLifeFacade gameOfLifeFacade) {
    if (isTaskCreated()) {
      return;
    }

    task = new Task(gameOfLifeFacade);
    taskThread = new Thread(task);
    taskThread.setDaemon(true);
    taskThread.start();
  }
  private boolean isTaskCreated() {
    return task != null && taskThread != null;
  }
  private boolean isTaskNotCreated() {
    return !isTaskCreated();
  }

  void finishTaskWithoutWaiting() {
    if (isTaskNotCreated()) {
      return;
    }
    task.finishTask();
  }
  void finishTaskWithWaiting() {
    if (isTaskNotCreated()) {
      return;
    }
    try {
      task.finishTask();
      waitTask();
    } finally {
      flushTask();
    }
  }
  private void waitTask() {
    try {
      taskThread.join();
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }
  private void flushTask() {
    task = null;
    taskThread = null;
  }

  void pauseTask() {
    if (isTaskNotCreated() || task.isPaused()) {
      return;
    }
    task.pauseOn();
  }
  void continueTask() {
    if (isTaskNotCreated() || task.isNotPaused()) {
      return;
    }
    task.pauseOff();
  }
  void setIterationDelayMs(int delayMs) {
    if (isTaskNotCreated()) {
      return;
    }
    task.setDelayMs(delayMs);
  }

  private final class Task implements Runnable {
    private static final int DEFAULT_DELAY_MS = 1000;
    private final AtomicBoolean running = new AtomicBoolean(true);
    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final AtomicInteger delayMs = new AtomicInteger(DEFAULT_DELAY_MS);
    private final GameOfLifeFacade gameOfLifeFacade;

    Task(GameOfLifeFacade gameOfLifeFacade) {
      this.gameOfLifeFacade = gameOfLifeFacade;
    }

    @Override
    public void run() {
      try {
        gameOfLifeFacade.print();
        while (running.get()) {
          TimeUnit.MILLISECONDS.sleep(delayMs.get());
          gameOfLifeFacade.iterateAndPrint();
          while (paused.get()) {
          }
        }
      } catch (Exception e) {
        flushTask();
        throw new RuntimeException(e.getMessage(), e);
      }
    }

    void finishTask() {
      running.set(false);
      paused.set(false);
    }
    void setDelayMs(int delayMs) {
      this.delayMs.set(delayMs);
    }
    boolean isPaused() {
      return paused.get();
    }
    boolean isNotPaused() {
      return !isPaused();
    }
    void pauseOn() {
      paused.set(true);
    }
    void pauseOff() {
      paused.set(false);
    }
  }
}
