package org.udem.unishop.validation;

public class PhoneValidator implements Validator {

  @Override
  public boolean isValid(String input) {
    return input.matches("[0-9]{10}");
  }

  @Override
  public String getErrorMessage() {
    return "Le numéro de téléphone doit contenir 10 chiffres.";
  }
}