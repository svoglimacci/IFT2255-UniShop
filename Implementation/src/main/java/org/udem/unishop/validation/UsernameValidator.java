package org.udem.unishop.validation;

public class UsernameValidator implements Validator {

  public boolean isValid(String input) {
    return input.matches("[a-zA-Z0-9]{3,20}");
  }

  public String getErrorMessage() {
    return "Le nom d'utilisateur doit contenir entre 3 et 20 caractères alphanumériques.";
  }
}