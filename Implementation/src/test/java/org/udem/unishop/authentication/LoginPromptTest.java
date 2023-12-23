package org.udem.unishop.authentication;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginPromptTest {

  @Test
  void createLoginPrompt() {
    LoginPrompt loginPrompt = new LoginPrompt();
    assertNotNull(loginPrompt.createLoginPrompt());
  }
}