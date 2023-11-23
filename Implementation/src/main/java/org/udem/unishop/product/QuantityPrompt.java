package org.udem.unishop.product;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.IntegerValidator;


public class QuantityPrompt {

    public Prompt createQuantityPrompt() {
    Prompt quantityPrompt = new Prompt();
    quantityPrompt.addPromptComponent(new PromptItem("Quantité", new IntegerValidator()));
    return quantityPrompt;
  }

}