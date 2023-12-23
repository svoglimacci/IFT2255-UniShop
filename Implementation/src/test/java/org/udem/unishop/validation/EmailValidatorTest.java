package org.udem.unishop.validation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EmailValidatorTest {

  private final EmailValidator emailValidator = new EmailValidator();

  @Test
  void isValid_withValidEmail_returnsTrue() {
    assertTrue(emailValidator.isValid("test@example.com"));
  }

  @Test
  void isValid_withInvalidEmail_returnsFalse() {
    assertFalse(emailValidator.isValid("invalidEmail"));
  }

  @Test
  void isValid_withEmptyString_returnsFalse() {
    assertFalse(emailValidator.isValid(""));
  }


  @Test
  void getErrorMessage_returnsExpectedMessage() {
    assertEquals("Veuillez entrer une adresse email valide.", emailValidator.getErrorMessage());
  }
}