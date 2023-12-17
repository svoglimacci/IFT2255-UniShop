package org.udem.unishop.utilities;

public enum SettingType {
  EMAIL("Courriel"), ADDRESS("Adresse"), PHONE_NUMBER("Numéro de téléphone"), PASSWORD("Mot de passe");



  private final String displayName;

  SettingType(String displayName) {
    this.displayName = displayName;
  }

  public String displayName() {
    return displayName;
  }

}