package org.udem.unishop.product;

import org.udem.unishop.models.Product;
import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.PointsValidator;


public class PointsPrompt {

  Product product;

  public PointsPrompt(Product price) {
    this.product = price;
  }

  public Prompt createPointsPrompt() {
    Prompt pointsPrompt = new Prompt();
    pointsPrompt.addPromptComponent(new PromptItem("Quantité", new PointsValidator(product)));
    return pointsPrompt;
  }

}