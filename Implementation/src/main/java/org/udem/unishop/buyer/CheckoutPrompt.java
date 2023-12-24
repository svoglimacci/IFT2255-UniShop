package org.udem.unishop.buyer;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.utilities.SearchType;
import org.udem.unishop.validation.PhoneValidator;
import org.udem.unishop.validation.PriceValidator;
import org.udem.unishop.validation.StringValidator;

/**
 * The CheckoutPrompt class provides a utility for creating a prompt to gather checkout-related information.
 * It enables the collection of delivery address and phone number during the checkout process.
 */
public class CheckoutPrompt {

  /**
   * Creates a prompt for gathering checkout-related information.
   *
   * @return The configured prompt for collecting delivery address and phone number.
   */
  public Prompt createCheckoutPrompt( ) {

    Prompt checkoutPrompt = new Prompt();

    checkoutPrompt.addPromptComponent(new PromptItem("Adresse de livraison", new StringValidator()));
    checkoutPrompt.addPromptComponent(new PromptItem("Numéro de téléphone", new PhoneValidator()));
    return checkoutPrompt;
  }

}