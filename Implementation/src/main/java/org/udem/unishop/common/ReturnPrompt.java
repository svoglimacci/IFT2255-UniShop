package org.udem.unishop.common;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.StringValidator;

/**
 * Represents a prompt generator specifically designed for handling return reasons in exchange operations.
 * This class enables the creation of prompts related to reasons for returning items in an exchange.
 */
public class ReturnPrompt {

    /**
     * Creates a prompt for selecting the reason for an exchange operation.
     *
     * @return The prompt for selecting the reason for exchange.
     */
    public Prompt createReturnPrompt() {

      Prompt returnPrompt = new Prompt();

      returnPrompt.addPromptComponent(new PromptItem("Raison de l'échange :", new StringValidator()));

      return returnPrompt;
    }

}