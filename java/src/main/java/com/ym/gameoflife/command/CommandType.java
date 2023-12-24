package com.ym.gameoflife.command;

import com.ym.gameoflife.command.impl.*;
import com.ym.gameoflife.input.ConsoleReader;

/**
 * @author Yahor Makedon
 */
public enum CommandType implements Command {
  LISTEN(new ListenCommand()),
  RUN(new RunCommand(), LISTEN),
  FILE_INPUT(new FileInputCommand()),
  CONSOLE_INPUT(new ConsoleInputCommand()),
  INIT(new InitCommand(), RUN),
  BEGIN(new BeginCommand(), INIT);

  private final Command command;
  private final CommandType nextCommand;

  CommandType(Command command) {
    this(command, null);
  }

  CommandType(Command command, CommandType nextCommand) {
    this.command = command;
    this.nextCommand = nextCommand;
  }

  @Override
  public void execute(ConsoleReader consoleReader) {
    command.execute(consoleReader);
    if (nextCommand != null) {
      nextCommand.execute(consoleReader);
    }
  }
}
