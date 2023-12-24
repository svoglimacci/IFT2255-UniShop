package org.udem.unishop.utilities;

/**
 * Enumeration representing different types of user settings
 */
public enum SettingType {

  /**
   * Represents the user's email address.
   */
  EMAIL("Courriel"),

  /**
   * Represents the user's delivery address.
   */
  ADDRESS("Adresse"),

  /**
   * Represents the user's phone number.
   */
  PHONE_NUMBER("Numéro de téléphone"),

  /**
   * Represents the user's password.
   */
  PASSWORD("Mot de passe");

  private final String displayName;

  /**
   * Constructor for the SettingType enum.
   *
   * @param displayName The display name of the associated setting type.
   */
  SettingType(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Gets the display name of the setting type.
   *
   * @return The display name.
   */
  public String displayName() {
    return displayName;
  }

}