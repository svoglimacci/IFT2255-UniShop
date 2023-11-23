package org.udem.unishop.authentication;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.PasswordValidator;
import org.udem.unishop.validation.UsernameValidator;

public class LoginPrompt {

  public Prompt createLoginPrompt() {
    Prompt loginPrompt = new Prompt();
    loginPrompt.addPromptComponent(new PromptItem("Nom d'utilisateur", new UsernameValidator()));
    loginPrompt.addPromptComponent(new PromptItem("Mot de passe", new PasswordValidator()));
    return loginPrompt;
  }
}