package org.udem.unishop.utilities;

public enum AccountType {
  BUYER("Acheteur"), SELLER("Revendeur"), GUEST("Invité");

  private final String displayName;

  AccountType(String displayName) {
    this.displayName = displayName;
  }

  public String displayName() {
    return displayName;
  }
}