package models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LearningMaterial extends Product {
    private String brand;
    private String model;
    private String subCategory;

    enum SubCategory {

        PEN("Stylo"),
        NOTEBOOK("Cahier"),
        BINDER("Classeur"),
        PAPER("Feuilles de papier"),
        CAlCULATOR("Calculatrice"),
        HIGHLIGHTER("Surligneur");


        private final String displayName;

        SubCategory(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
        }

    public LearningMaterial(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("price") double price,
            @JsonProperty("likes") int likes,
            @JsonProperty("comments") List<Review> comments,
            @JsonProperty("rating") float rating,
            @JsonProperty("category") String category,
            @JsonProperty("isPromoted") boolean isPromoted,
            @JsonProperty("brand") String brand,
            @JsonProperty("model") String model,
            @JsonProperty("subCategory") String subCategory
    ) {
        super(name, description, price, likes, comments, rating, category, isPromoted);
        this.brand = brand;
        this.model = model;
        this.subCategory = subCategory;
    }

    // Getters and Setters for the new attributes

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}