package org.udem.unishop.utilities;

public enum SearchType {

  USERNAME("nom d'utilisateur"),
  BUSINESS_NAME("nom d'entreprise"),

  PRODUCT_TYPE("type de produit"),

  PRODUCT_NAME("nom de produit"),

  PRODUCT_CATEGORY("catégorie de produit"),

  PRODUCT_BRAND("marque de produit"),

  PRODUCT_MODEL("modèle de produit"),

  PRODUCT_PRICE("tranche de prix"),

  BUSINESS_ADDRESS("adresse d'entreprise"),
  FOLLOWED_USERS("utilisateurs suivis");

  private final String searchType;

  SearchType(String searchType) {
    this.searchType = searchType;
  }

  public String getSearchType() {
    return searchType;
  }
}