package models;

public enum ProductCategory {
    LEARNING_MATERIALS("Ressources d'apprentissage"),
    OFFICE_SUPPLIES("Articles de papeterie"),
    BOOKS("Livres et manuels"),
    OFFICE_FURNITURES("Équipement de bureau"),
    ELECTRONICS("Matériel informatique");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}