package org.udem.unishop.product;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.IntegerValidator;
import org.udem.unishop.validation.PriceValidator;

/**
 * The PromotionPrompt class provides a method to create a prompt for setting promotion information.
 */
public class PromotionPrompt {

    /**
     * Creates a prompt for setting promotion information.
     *
     * @return The prompt for setting promotion information.
     */
    public Prompt createPromotionPrompt() {
    Prompt promotionPrompt = new Prompt();
    promotionPrompt.addPromptComponent(new PromptItem("Points bonis", new IntegerValidator()));
    promotionPrompt.addPromptComponent(new PromptItem("Baisse de prix", new PriceValidator()));
    return promotionPrompt;
  }

}