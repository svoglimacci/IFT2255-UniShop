package org.udem.unishop.utilities;

/**
 * Enumeration representing the types of search.
 */
public enum SearchType {

  /**
   * Represents search by username.
   */
  USERNAME("nom d'utilisateur"),

  /**
   * Represents search by business name.
   */
  BUSINESS_NAME("nom d'entreprise"),

  /**
   * Represents search by product type.
   */
  PRODUCT_TYPE("type de produit"),

  /**
   * Represents search by product name.
   */
  PRODUCT_NAME("nom de produit"),

  /**
   * Represents search by category.
   */
  PRODUCT_CATEGORY("catégorie de produit"),

  /**
   * Represents search by product brand.
   */
  PRODUCT_BRAND("marque de produit"),

  /**
   * Represents search by product model.
   */
  PRODUCT_MODEL("modèle de produit"),

  /**
   * Represents search by price range.
   */
  PRODUCT_PRICE("tranche de prix"),

  /**
   * Represents search by business address
   */
  BUSINESS_ADDRESS("adresse d'entreprise"),

  /**
   * Represents search by followed users.
   */
  FOLLOWED_USERS("utilisateurs suivis");

  private final String searchType;

  /**
   * Constructor for SearchType enum.
   *
   * @param searchType The type of search associated with the enum constant.
   */
  SearchType(String searchType) {
    this.searchType = searchType;
  }

  /**
   * Gets the search type associated with the enum constant.
   *
   * @return The search type.
   */
  public String getSearchType() {
    return searchType;
  }
}