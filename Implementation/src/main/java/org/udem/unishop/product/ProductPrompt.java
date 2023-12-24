package org.udem.unishop.product;

import org.udem.unishop.utilities.ProductType;
import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.IntegerValidator;
import org.udem.unishop.validation.PriceValidator;
import org.udem.unishop.validation.StringValidator;

/**
 * The ProductPrompt class provides a method to create prompts for capturing product information based on the product type.
 */
public class ProductPrompt {

    /**
     * Creates a prompt for capturing product information based on the specified product type.
     *
     * @param productType The type of the product.
     * @return The prompt for capturing product information.
     * @throws IllegalArgumentException If the product type is not supported.
     */
    public Prompt createProductPrompt(ProductType productType) {
    Prompt productPrompt = new Prompt();
    productPrompt.addPromptComponent(new PromptItem("Nom", new StringValidator()));
    productPrompt.addPromptComponent(new PromptItem("Description", new StringValidator()));
    productPrompt.addPromptComponent(new PromptItem("Quantité Initiale", new IntegerValidator()));
    productPrompt.addPromptComponent(new PromptItem("Prix", new PriceValidator()));
    productPrompt.addPromptComponent(new PromptItem("Points bonus", new IntegerValidator()));
    productPrompt.addPromptComponent(new PromptItem("Lien vers une image ou vidéo", new StringValidator()));

    switch (productType) {
        case BOOK :
            productPrompt.addPromptComponent(new PromptItem("ISBN", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Auteur", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Maison d'édition", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Genre", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Date de parution", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Numéro d'édition", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Numéro de volume", new StringValidator()));
            break;

        case LEARNING_RESOURCE :
            productPrompt.addPromptComponent(new PromptItem("ISBN", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Auteur", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Organisation", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Date de parution", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Type (imprimé ou électronique)", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Numéro d'édition", new StringValidator()));
            break;

        case STATIONERY, OFFICE_EQUIPMENT :
            productPrompt.addPromptComponent(new PromptItem("Marque", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Modèle", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Sous-catégorie", new StringValidator()));
            break;

        case COMPUTER_HARDWARE :
            productPrompt.addPromptComponent(new PromptItem("Marque", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Modèle", new StringValidator()));
            productPrompt.addPromptComponent(new PromptItem("Date de lancement", new StringValidator()));
            break;

        default:
            throw new IllegalArgumentException("Unsupported category: " + productType);

    }

    return productPrompt;
  }
}