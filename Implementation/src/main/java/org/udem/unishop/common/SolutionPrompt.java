package org.udem.unishop.common;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.StringValidator;

public class SolutionPrompt {

    public Prompt createSolutionPrompt() {

      Prompt solutionPrompt = new Prompt();

      solutionPrompt.addPromptComponent(new PromptItem("Solution", new StringValidator()));

      return solutionPrompt;
    }

}