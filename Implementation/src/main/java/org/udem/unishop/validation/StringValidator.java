package org.udem.unishop.validation;

/**
 * A validator for checking the validity of a string.
 */
public class StringValidator implements Validator {

  /**
   * Checks if the input string is an empty string.
   *
   * @param input The input string to be validated.
   * @return true if the input string is not an empty string, false otherwise.
   */
  public boolean isValid(String input) {
    return !input.isEmpty();
  }

  /**
   * Gets the error message to be displayed when the string is invalid.
   *
   * @return The error message for invalid string input.
   */
  public String getErrorMessage() {
    return "Le champ ne peut pas être vide.";
  }
}