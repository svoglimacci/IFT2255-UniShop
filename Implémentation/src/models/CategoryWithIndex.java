package models;

public class CategoryWithIndex {
    private final int index;
    private final ProductCategory category;

    public CategoryWithIndex(int index, ProductCategory category) {
        this.index = index;
        this.category = category;
    }

    public int getIndex() {
        return index;
    }

    public ProductCategory getCategory() {
        return category;
    }
}