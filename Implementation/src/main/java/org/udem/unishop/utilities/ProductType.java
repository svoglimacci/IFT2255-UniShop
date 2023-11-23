package org.udem.unishop.utilities;

public enum ProductType {


  BOOK("Livres et Manuels"), COMPUTER_HARDWARE("Matériel informatique"), LEARNING_RESOURCE(
      "Ressources d'apprentissage"), OFFICE_EQUIPMENT("Équipement de bureau"), STATIONERY(
      "Papeterie");


  private final String displayName;

  ProductType(String displayName) {
    this.displayName = displayName;
  }

  public String displayName() {
    return displayName;
  }
}