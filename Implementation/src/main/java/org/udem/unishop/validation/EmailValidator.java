package org.udem.unishop.validation;

/**
 * A validator for checking the validity of email addresses.
 */
public class EmailValidator implements Validator {

  /**
   * Checks if the input is a valid email address.
   *
   * @param input The input string to be validated
   * @return true if the input is a valid email address, false otherwise
   */
  @Override
  public boolean isValid(String input) {
    return input.matches(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
  }

  /**
   * Gets the error message to be displayed when the email address is invalid.
   *
   * @return The error message for an invalid email input.
   */
  @Override
  public String getErrorMessage() {
    return "Veuillez entrer une adresse email valide.";
  }
}