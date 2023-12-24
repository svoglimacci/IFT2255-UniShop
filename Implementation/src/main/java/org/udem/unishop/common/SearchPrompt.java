package org.udem.unishop.common;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.utilities.SearchType;
import org.udem.unishop.validation.PriceValidator;
import org.udem.unishop.validation.StringValidator;

/**
 * The SearchPrompt class generates prompt components based on the specified search type.
 * It creates prompts for various search criteria used within the application.
 */
public class SearchPrompt {

  /**
   * Creates a prompt based on the provided search type.
   *
   * @param searchType The type of search for which the prompt is generated.
   * @return The Prompt instance with prompt components based on the given search type.
   */
  public Prompt createSearchPrompt(SearchType searchType) {

    Prompt searchPrompt = new Prompt();

    switch (searchType) {
      case USERNAME:
        searchPrompt.addPromptComponent(new PromptItem("Nom de l'utilisateur", new StringValidator()));
        break;
      case BUSINESS_NAME:
        searchPrompt.addPromptComponent(new PromptItem("Nom de l'entreprise", new StringValidator()));
        break;
      case BUSINESS_ADDRESS:
        searchPrompt.addPromptComponent(new PromptItem("Adresse de l'entreprise", new StringValidator()));
        break;
        case PRODUCT_TYPE:
        searchPrompt.addPromptComponent(new PromptItem("Type de produit", new StringValidator()));
        break;
        case PRODUCT_NAME:
        searchPrompt.addPromptComponent(new PromptItem("Nom de produit", new StringValidator()));
                break;
        case PRODUCT_CATEGORY:
        searchPrompt.addPromptComponent(new PromptItem("Catégorie de produit", new StringValidator()));
                break;
        case PRODUCT_BRAND:
        searchPrompt.addPromptComponent(new PromptItem("Marque de produit", new StringValidator()));
                break;
        case PRODUCT_MODEL:
        searchPrompt.addPromptComponent(new PromptItem("Modèle de produit", new StringValidator()));
                break;
        case PRODUCT_PRICE:
        searchPrompt.addPromptComponent(new PromptItem("Prix Minimum", new PriceValidator()));
        searchPrompt.addPromptComponent(new PromptItem("Prix Maximum", new PriceValidator()));
                break;
    }
    return searchPrompt;
  }

}