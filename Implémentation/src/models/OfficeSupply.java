package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class OfficeSupply extends Product {

    private String brand;
    private String model;
    private String subCategory;

    @JsonCreator
    public OfficeSupply(
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

    // toString
    @Override
    public String toString() {
        return "Marque : " + brand +
                "\n Modèle : " + model +
                "\n Sous-catégorie : " + subCategory;
    }

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
    }

}