package org.udem.unishop.utilities;

/**
 * Enumerations representing different types of product.
 */
public enum ProductType {

  /**
   * Represents books and manuals.
   */
  BOOK("Livres et Manuels"),

  /**
   * Represents computer hardware.
   */
  COMPUTER_HARDWARE("Matériel informatique"),

  /**
   * Represents learning resources.
   */
  LEARNING_RESOURCE("Ressources d'apprentissage"),

  /**
   * Represents office equipments.
   */
  OFFICE_EQUIPMENT("Équipement de bureau"),

  /**
   * Represents represents stationery.
   */
  STATIONERY("Papeterie");


  private final String displayName;

  /**
   * Constructor for the ProductType enum.
   *
   * @param displayName The display name associated with the product type.
   */
  ProductType(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Gets the display name of the product type.
   *
   * @return The display name.
   */
  public String displayName() {
    return displayName;
  }
}