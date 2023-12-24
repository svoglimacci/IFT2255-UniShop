package org.udem.unishop.product;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.IntegerValidator;

/**
 * The QuantityPrompt class provides a method to create a prompt for collecting quantity information.
 */
public class QuantityPrompt {

    /**
     * Creates a prompt for collecting quantity information.
     *
     * @return The prompt for collecting quantity information.
     */
    public Prompt createQuantityPrompt() {
    Prompt quantityPrompt = new Prompt();
    quantityPrompt.addPromptComponent(new PromptItem("Quantité", new IntegerValidator()));
    return quantityPrompt;
  }

}