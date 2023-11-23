package org.udem.unishop.utilities;

interface PromptComponent {

  String getPrompt();

  String getValue();

  boolean validateUserInput(String input);
}