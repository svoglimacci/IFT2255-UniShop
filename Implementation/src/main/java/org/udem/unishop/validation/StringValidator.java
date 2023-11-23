package org.udem.unishop.validation;

public class StringValidator implements Validator {

  public boolean isValid(String input) {
    return !input.isEmpty();
  }

  public String getErrorMessage() {
    return "Le champ ne peut pas être vide.";
  }
}