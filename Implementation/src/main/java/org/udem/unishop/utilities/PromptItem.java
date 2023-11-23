package org.udem.unishop.utilities;

import java.util.Scanner;
import org.udem.unishop.validation.Validator;

public class PromptItem implements PromptComponent {

  private final Validator validator;
  private final String prompt;

  public PromptItem(String prompt, Validator validator) {
    this.prompt = prompt;
    this.validator = validator;
  }

  @Override
  public String getPrompt() {
    return prompt;
  }

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

  @Override
  public boolean validateUserInput(String input) {
    return validator.isValid(input);
  }
}