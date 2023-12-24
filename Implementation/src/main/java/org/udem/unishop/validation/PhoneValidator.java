package org.udem.unishop.validation;

/**
 * A validator for checking the validity of a phone number.
 */
public class PhoneValidator implements Validator {

  /**
   * Checks if the input string is a valid phone number.
   *
   * @param input The input string to be validated.
   * @return true if the input string is a valid phone number, false otherwise.
   */
  @Override
  public boolean isValid(String input) {
    return input.matches("[0-9]{10}");
  }

  /**
   * Gets the error message for to be displayed when the phone number is invalid.
   *
   * @return The error message for invalid phone number input.
   */
  @Override
  public String getErrorMessage() {
    return "Le numéro de téléphone doit contenir 10 chiffres.";
  }
}