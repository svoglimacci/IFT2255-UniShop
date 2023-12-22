package org.udem.unishop.common;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.StringValidator;

public class ReturnPrompt {

    public Prompt createReturnPrompt() {

      Prompt returnPrompt = new Prompt();

      returnPrompt.addPromptComponent(new PromptItem("Raison de l'échange :", new StringValidator()));

      return returnPrompt;
    }

}