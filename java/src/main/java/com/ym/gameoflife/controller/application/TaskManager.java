package com.ym.gameoflife.controller.application;

import com.ym.gameoflife.controller.GameOfLifeFacade;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

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

  void setIterationDelayMs(int delayMs) {
    if (isTaskNotCreated()) {
      return;
    }
    task.setDelayMs(delayMs);
  }

  private final class Task implements Runnable {
    private static final int DEFAULT_DELAY_MS = 1000;
    private final AtomicBoolean isRunning = new AtomicBoolean(true);
    private final GameOfLifeFacade gameOfLifeFacade;
    private int delayMs;

    Task(GameOfLifeFacade gameOfLifeFacade) {
      this.gameOfLifeFacade = gameOfLifeFacade;
      delayMs = DEFAULT_DELAY_MS;
    }

    @Override
    public void run() {
      try {
        gameOfLifeFacade.print();
        while (isRunning.get()) {
          TimeUnit.MILLISECONDS.sleep(delayMs);
          gameOfLifeFacade.iterateAndPrint();
        }
      } catch (Exception e) {
        flushTask();
        throw new RuntimeException(e.getMessage(), e);
      }
    }

    void finishTask() {
      isRunning.set(false);
    }

    void setDelayMs(int delayMs) {
      this.delayMs = delayMs;
    }
  }
}
