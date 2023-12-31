package org.udem.unishop.authentication;

import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.EmailValidator;
import org.udem.unishop.validation.PasswordValidator;
import org.udem.unishop.validation.PhoneValidator;
import org.udem.unishop.validation.StringValidator;
import org.udem.unishop.validation.UsernameValidator;

/**
 * Represents a prompt for user registration providing components for various user details on the account type.
 */
public class RegisterPrompt {

  /**
   * Creates a registration prompt with components for user details based on the specified account type.
   *
   * @param accountType The type of account for which the registration prompt is created (buyer or seller).
   * @return The created registration prompt.
   */
  public Prompt createRegisterPrompt(AccountType accountType) {
    Prompt registerPrompt = new Prompt();
    registerPrompt.addPromptComponent(new PromptItem("Nom d'utilisateur", new UsernameValidator()));
    registerPrompt.addPromptComponent(new PromptItem("Mot de passe", new PasswordValidator()));
    registerPrompt.addPromptComponent(new PromptItem("Adresse courriel", new EmailValidator()));
    registerPrompt.addPromptComponent(new PromptItem("Numéro de téléphone", new PhoneValidator()));
    registerPrompt.addPromptComponent(new PromptItem("Adresse", new StringValidator()));

    // Additionnal prompt components based on account type
    switch (accountType) {
      case BUYER:
        registerPrompt.addPromptComponent(new PromptItem("Prénom", new StringValidator()));
        registerPrompt.addPromptComponent(new PromptItem("Nom", new StringValidator()));
        break;
      case SELLER:
        registerPrompt.addPromptComponent(
            new PromptItem("Nom de l'entreprise", new StringValidator()));
        break;
    }
    return registerPrompt;
  }
}