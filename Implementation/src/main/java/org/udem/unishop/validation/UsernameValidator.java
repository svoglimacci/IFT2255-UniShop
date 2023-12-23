package org.udem.unishop.validation;

/**
 * A validator for checking the validity of a username.
 * A valid username should be a string containing between 3 to 20 alphanumeric characters.
 */
public class UsernameValidator implements Validator {

  /**
   * Checks if the input string is a valid username containing between 3 to 20 alphanumeric characters.
   *
   * @param input The input string to be validated.
   * @return true if the input string is a valid username, false otherwise.
   */
  public boolean isValid(String input) {
    return input.matches("[a-zA-Z0-9]{3,20}");
  }

  /**
   * Gets the error message to be displayed when the username is invalid.
   *
   * @return The error message for invalid username input.
   */
  public String getErrorMessage() {
    return "Le nom d'utilisateur doit contenir entre 3 et 20 caractères alphanumériques.";
  }
}