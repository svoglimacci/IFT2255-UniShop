package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Electronic extends Product {

    private String brand;
    private String model;
    private String releaseDate;
    private String subCategory;


    @JsonCreator
    public Electronic(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("price") double price,
            @JsonProperty("likes") int likes,
            @JsonProperty("reviews") List<Review> reviews,
            @JsonProperty("rating") float rating,
            @JsonProperty("category") String category,
            @JsonProperty("isPromoted") boolean isPromoted,
            @JsonProperty("brand") String brand,
            @JsonProperty("model") String model,
            @JsonProperty("releaseDate") String releaseDate,
            @JsonProperty("subCategory") String subCategory
    ) {
        super(name, description, price, likes, reviews, rating, category, isPromoted);
        this.brand = brand;
        this.model = model;
        this.releaseDate = releaseDate;
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Marque : " + brand +
                "\n Modèle : " + model +
                "\n Date de sortie : " + releaseDate +
                "\n Sous-catégorie : " + subCategory;
    }

    enum SubCategory {
        LAPTOP("Ordinateur portable"),
        MOUSE("Souris"),
        KEYBOARD("Clavier"),
        EXTERNAL_HDD("Disque dur externe"),
        HEADPHONES("Écouteurs");

        private final String displayName;

        SubCategory(String displayName) {
            this.displayName = displayName;
        }
    }
}