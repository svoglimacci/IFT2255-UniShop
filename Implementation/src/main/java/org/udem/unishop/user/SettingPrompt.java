package org.udem.unishop.user;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.utilities.SettingType;
import org.udem.unishop.validation.EmailValidator;
import org.udem.unishop.validation.PasswordValidator;
import org.udem.unishop.validation.PhoneValidator;
import org.udem.unishop.validation.StringValidator;

/**
 * Utility class for creating prompts based on the specified SettingType.
 */
public class SettingPrompt {

  /**
   * Creates a prompt based on the specified SettingType.
   *
   * @param settingType The type of setting for which to create the prompt.
   * @return A prompt configured for the specified setting type.
   * @throws IllegalArgumentException if the provided setting type is unsupported.
   */
  public Prompt createSettingPrompt(SettingType settingType) {

    Prompt settingPrompt = new Prompt();

    switch (settingType) {
      case PASSWORD:
        settingPrompt.addPromptComponent(new PromptItem("Nouveau mot de passe", new PasswordValidator()));
        break;
      case EMAIL:
        settingPrompt.addPromptComponent(new PromptItem("Nouveau courriel", new EmailValidator()));
        break;
      case ADDRESS:
        settingPrompt.addPromptComponent(new PromptItem("Nouvelle adresse", new StringValidator()));
        break;
      case PHONE_NUMBER:
        settingPrompt.addPromptComponent(new PromptItem("Nouveau numéro de téléphone", new PhoneValidator()));
        break;

    }

    return settingPrompt;
  }

}