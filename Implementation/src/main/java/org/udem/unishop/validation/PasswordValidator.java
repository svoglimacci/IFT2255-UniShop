package org.udem.unishop.validation;

/**
 * A validator for checking the validity of a password.
 */
public class PasswordValidator implements Validator {

  /**
   * Checks if the input string is a valid password.
   *
   * @param password The input string to be validated.
   * @return true if the input string is a valid password, false otherwise.
   */
  @Override
  public boolean isValid(String password) {
    return password.length() >= 8 && containsUppercase(password) && containsDigit(password);
  }

  /**
   *
   * @param input
   * @return
   */
  private boolean containsUppercase(String input) {
    return input.matches(".*[A-Z].*");
  }

  /**
   *
   * @param input
   * @return
   */
  private boolean containsDigit(String input) {
    return input.matches(".*\\d.*");
  }

  /**
   * Gets the error message to be displayed when the password is invalid.
   *
   * @return The error message for invalid password input.
   */
  public String getErrorMessage() {
    return "Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule et un chiffre.";
  }
}