package org.udem.unishop.common;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.StringValidator;

/**
 * The SolutionPrompt class generates a prompt for adding solutions.
 * It creates a prompt component specifically for adding a solution.
 */
public class SolutionPrompt {

    /**
     * Creates a prompt for adding a solution.
     *
     * @return The Prompt instance designed for adding solutions.
     */
    public Prompt createSolutionPrompt() {

      Prompt solutionPrompt = new Prompt();

      solutionPrompt.addPromptComponent(new PromptItem("Solution", new StringValidator()));

      return solutionPrompt;
    }

}