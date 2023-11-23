package org.udem.unishop.validation;

public class RatingValidator implements Validator{
  @Override
  public boolean isValid(String value) {
    try {
      int rating = Integer.parseInt(value);
      return rating >= 0 && rating <= 5;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  @Override
  public String getErrorMessage() {
    return "La note doit être un nombre entre 0 et 5";
  }

}