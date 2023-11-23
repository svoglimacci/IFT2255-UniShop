package org.udem.unishop.validation;

public class IntegerValidator implements Validator {

  public boolean isValid(String input) {
    return !input.isEmpty() && input.matches("[0-9]+");
  }

  public String getErrorMessage() {
    return "Veuillez entrer un nombre entier.";
  }

}