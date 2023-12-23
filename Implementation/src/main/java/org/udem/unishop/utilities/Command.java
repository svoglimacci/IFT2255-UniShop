package org.udem.unishop.utilities;

/**
 * An interface representing a command to be executed.
 * Commands have a name and an execution behavior.
 */
public interface Command {

  /**
   * Gets the name of the command.
   *
   * @return The name of the command.
   */
  String getName();

  /**
   * Executes the command.
   */
  void execute();
}