package org.udem.unishop.buyer;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.utilities.SearchType;
import org.udem.unishop.validation.PhoneValidator;
import org.udem.unishop.validation.PriceValidator;
import org.udem.unishop.validation.StringValidator;

public class CheckoutPrompt {

  public Prompt createCheckoutPrompt( ) {

    Prompt checkoutPrompt = new Prompt();

    checkoutPrompt.addPromptComponent(new PromptItem("Adresse de livraison", new StringValidator()));
    checkoutPrompt.addPromptComponent(new PromptItem("Numéro de téléphone", new PhoneValidator()));
    return checkoutPrompt;
  }

}