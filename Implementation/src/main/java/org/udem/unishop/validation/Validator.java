package org.udem.unishop.validation;

public interface Validator {

  boolean isValid(String input);

  String getErrorMessage();

}