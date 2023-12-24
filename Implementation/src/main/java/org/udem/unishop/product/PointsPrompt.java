package org.udem.unishop.product;

import org.udem.unishop.models.Product;
import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.PromptItem;
import org.udem.unishop.validation.PointsValidator;

/**
 * The PointsPrompt class is responsible for creating a prompt related to points.
 */
public class PointsPrompt {

  Product product;

  /**
   * Constructor for PointsPrompt.
   *
   * @param product The product for which the points prompt is created.
   */
  public PointsPrompt(Product price) {
    this.product = price;
  }

  /**
   * Creates a points prompt with a prompt item for obtaining the quantity of points associated
   * with the product.
   *
   * @return The created points prompt.
   */
  public Prompt createPointsPrompt() {
    Prompt pointsPrompt = new Prompt();
    pointsPrompt.addPromptComponent(new PromptItem("Quantité", new PointsValidator(product)));
    return pointsPrompt;
  }

}