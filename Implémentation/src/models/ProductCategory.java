package models;

import java.util.ArrayList;
import java.util.List;

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

    public static List<CategoryWithIndex> getCategoriesWithIndices() {
        List<CategoryWithIndex> categories = new ArrayList<>();
        int idx = 1;
        for (ProductCategory category : values()) {
            categories.add(new CategoryWithIndex(idx, category));
            idx++;
        }
        return categories;
    }

    public static String categoriesToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Veuillez choisir une catégorie :\n");
        for (CategoryWithIndex category : ProductCategory.getCategoriesWithIndices()) {
            sb.append(category.getIndex()).append(". ").append(category.getCategory().getDisplayName()).append("\n");
        }
        return sb.toString();
    }

    public String getDisplayName() {
        return displayName;
    }

    public Class<? extends Product> getProductClass() {
        return switch (this) {
            case BOOKS -> Book.class;
            case LEARNING_MATERIALS -> LearningMaterial.class;
            case OFFICE_SUPPLIES -> OfficeSupply.class;
            case OFFICE_FURNITURES -> OfficeFurniture.class;
            case ELECTRONICS -> Electronic.class;
        };
    }
}