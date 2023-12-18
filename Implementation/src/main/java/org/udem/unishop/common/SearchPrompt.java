package org.udem.unishop.common;

import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.utilities.SearchType;
import org.udem.unishop.validation.PriceValidator;
import org.udem.unishop.validation.StringValidator;

public class SearchPrompt {

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