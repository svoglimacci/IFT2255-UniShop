package org.udem.unishop.validation;

public class EmailValidator implements Validator {

  @Override
  public boolean isValid(String input) {
    return input.matches(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
  }


  @Override
  public String getErrorMessage() {
    return "Veuillez entrer une adresse email valide.";
  }
}