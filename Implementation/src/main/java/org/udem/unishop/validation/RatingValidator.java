package org.udem.unishop.validation;

/**
 * A validator for checking the validity of a rating.
 * The valid rating should be an integer between 0 and 5.
 */
public class RatingValidator implements Validator{

  /**
   * Checks if the input string of the rating is a valid integer between 0 and 5.
   *
   * @param value The input string to be validated.
   * @return true if the input string is a valid rating, false otherwise.
   */
  @Override
  public boolean isValid(String value) {
    try {
      int rating = Integer.parseInt(value);
      return rating >= 0 && rating <= 5;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Gets the error message to be displayed when the rating is invalid.
   *
   * @return The error message for invalid rating input.
   */
  @Override
  public String getErrorMessage() {
    return "La note doit être un nombre entre 0 et 5";
  }

}