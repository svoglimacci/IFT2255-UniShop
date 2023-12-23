package org.udem.unishop.authentication;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.PasswordValidator;
import org.udem.unishop.validation.UsernameValidator;

/**
 * The LoginPrompt class creates a login prompt with username and password components
 */
public class LoginPrompt {

  /**
   * Creates a login prompt with username and password components
   * @return a login prompt
   */
  public Prompt createLoginPrompt() {
    Prompt loginPrompt = new Prompt();
    loginPrompt.addPromptComponent(new PromptItem("Nom d'utilisateur", new UsernameValidator()));
    loginPrompt.addPromptComponent(new PromptItem("Mot de passe", new PasswordValidator()));
    return loginPrompt;
  }
}