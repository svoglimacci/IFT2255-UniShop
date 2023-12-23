package org.udem.unishop.validation;

/**
 * A generic interface for implementing input validation.
 * Classes that implements this interface provides methods for checking the validity of input and gives an error
 * message when the validation fails.
 */
public interface Validator {

  /**
   * Checks if the input string is valid.
   *
   * @param input The input string to be validated.
   * @return true if the input string is valid, false otherwise.
   */
  boolean isValid(String input);

  /**
   * Gets the error message to be displayed when the input is invalid.
   *
   * @return The error message for the invalid input.
   */
  String getErrorMessage();

}