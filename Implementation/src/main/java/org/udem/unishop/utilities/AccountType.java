package org.udem.unishop.utilities;

/**
 * Enumeration representing different types of user accounts.
 * Each account type has its own display name.
 */
public enum AccountType {
  /**
   * Represents a buyer account type.
   */
  BUYER("Acheteur"),
  /**
   * Represents a seller account type.
   */
  SELLER("Revendeur"),
  /**
   * Represents a guest account type.
   */
  GUEST("Invité");

  private final String displayName;

  /**
   * Constructor for the AccountType enum.
   *
   * @param displayName The display name of the associated account type.
   */
  AccountType(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Gets the display name of the account type.
   *
   * @return The display name of the account type.
   */
  public String displayName() {
    return displayName;
  }
}