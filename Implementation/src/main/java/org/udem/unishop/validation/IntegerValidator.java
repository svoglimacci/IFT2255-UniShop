package org.udem.unishop.validation;

/**
 * A validator for checking the validity of an integer.
 */
public class IntegerValidator implements Validator {

  /**
   * Checks if the input is a valid integer.
   *
   * @param input The input string to be validated.
   * @return true if the input is a valid integer, false otherwise.
   */
  public boolean isValid(String input) {
    return !input.isEmpty() && input.matches("[0-9]+");
  }

  /**
   * Gets the error message to be displayed when the integer is invalid.
   *
   * @return The error message for invalid integer input.
   */
  public String getErrorMessage() {
    return "Veuillez entrer un nombre entier.";
  }

}