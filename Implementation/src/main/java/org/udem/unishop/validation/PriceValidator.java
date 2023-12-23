package org.udem.unishop.validation;

/**
 * A validator for checking the validity of a price.
 */
public class PriceValidator implements Validator {

  /**
   * Checks if the input string is a valid price.
   *
   * @param input The input string to be validated
   * @return true if the input string is a valid price, false otherwise.
   */
  public boolean isValid(String input) {
    return !input.isEmpty() && input.matches("[0-9]+([,.][0-9]{1,2})?");
  }

  /**
   * Gets the error message to be displayed when the price is invalid.
   *
   * @return The error message for invalid price input.
   */
  public String getErrorMessage() {
    return "Veuillez entrer un prix valide.";
  }
}