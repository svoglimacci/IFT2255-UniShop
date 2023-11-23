package org.udem.unishop.validation;

public class PasswordValidator implements Validator {

  @Override
  public boolean isValid(String password) {
    return password.length() >= 8 && containsUppercase(password) && containsDigit(password);
  }

  private boolean containsUppercase(String input) {
    return input.matches(".*[A-Z].*");
  }

  private boolean containsDigit(String input) {
    return input.matches(".*\\d.*");
  }

  public String getErrorMessage() {
    return "Le mot de passe doit contenir au moins 8 caractères, une lettre majuscule et un chiffre.";
  }
}