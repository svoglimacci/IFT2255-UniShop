package org.udem.unishop.validation;

public class PriceValidator implements Validator {


  public boolean isValid(String input) {
    return !input.isEmpty() && input.matches("[0-9]+([,.][0-9]{1,2})?");
  }


  public String getErrorMessage() {
    return "Veuillez entrer un prix valide.";


  }
}