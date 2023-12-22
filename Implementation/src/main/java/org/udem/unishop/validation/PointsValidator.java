package org.udem.unishop.validation;

import org.udem.unishop.models.Product;

public class PointsValidator implements Validator {

  private final Product product;


  public PointsValidator(Product product) {
    this.product = product;
  }

  public boolean isValid(String input) {

    return !input.isEmpty() && input.matches("[0-9]+([,.][0-9]{1,2})?") && Double.parseDouble(input) <= 20 * product.getBonusPoints();

  }


  public String getErrorMessage() {
    return "Veuillez entrer un prix valide.";


  }
}