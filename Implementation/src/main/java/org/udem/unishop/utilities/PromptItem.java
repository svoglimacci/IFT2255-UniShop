package org.udem.unishop.utilities;

import java.util.Scanner;
import org.udem.unishop.validation.Validator;

/**
 * Represents a prompt item that includes a prompt message and a validator for user input.
 */
public class PromptItem implements PromptComponent {

  private final Validator validator;
  private final String prompt;

  /**
   * Constructor for PromptItem.
   *
   * @param prompt The prompt message.
   * @param validator The validator for user input.
   */
  public PromptItem(String prompt, Validator validator) {
    this.prompt = prompt;
    this.validator = validator;
  }

  /**
   * Gets the prompt message associated with the prompt item.
   *
   * @return The prompt message.
   */
  @Override
  public String getPrompt() {
    return prompt;
  }

  /**
   * Gets the value entered by the user for the prompt item.
   *
   * @return The value entered by the user.
   */
  @Override
  public String getValue() {
    System.out.print(prompt + ": ");
    Scanner scanner = new Scanner(System.in);
    String userInput = scanner.nextLine();

    while (!validateUserInput(userInput)) {
      System.out.println(validator.getErrorMessage());
      System.out.print(prompt + ": ");
      userInput = scanner.nextLine();
    }

    return userInput;

  }

  /**
   * Validates the user input using the associated validator.
   * @param input The user input to be validated.
   * @return true if the user input is valid, false otherwise.
   */
  @Override
  public boolean validateUserInput(String input) {
    return validator.isValid(input);
  }
}