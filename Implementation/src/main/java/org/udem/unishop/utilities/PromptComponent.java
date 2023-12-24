package org.udem.unishop.utilities;

/**
 * Represents a component of a prompt.
 */
interface PromptComponent {

  /**
   * Gets the prompt message associated to the component.
   *
   * @return The prompt message.
   */
  String getPrompt();

  /**
   * Gets the value entered by the user from the prompt component.
   *
   * @return The value entered by the user.
   */
  String getValue();

  /**
   * Validates the user input for the prompt component.
   *
   * @param input The user input to be validated.
   * @return true if the user input is valid, false otherwise.
   */
  boolean validateUserInput(String input);
}