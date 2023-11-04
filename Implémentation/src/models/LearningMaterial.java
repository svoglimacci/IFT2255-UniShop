package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LearningMaterial extends Product {
    private String brand;
    private String model;
    private String subCategory;
    private String isbn;
    private String author;
    private String organization;
    private String publicationDate;
    private String edition;


    @JsonCreator
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
            @JsonProperty("subCategory") String subCategory,
            @JsonProperty("isbn") String isbn,
            @JsonProperty("author") String author,
            @JsonProperty("organization") String organization,
            @JsonProperty("publicationDate") String publicationDate,
            @JsonProperty("Édition") String edition) {
        super(name, description, price, likes, comments, rating, category, isPromoted);
        this.brand = brand;
        this.model = model;
        this.subCategory = subCategory;
        this.author = author;
        this.isbn = isbn;
        this.organization = organization;
        this.publicationDate = publicationDate;
        this.edition = edition;

    }

    // toString
    @Override
    public String toString() {
        return "Marque : " + brand +
                "\n Modèle : " + model +
                "\n Sous-catégorie : " + subCategory +
                "\n Auteur : " + author +
                "\n ISBN : " + isbn +
                "\n Organisation : " + organization +
                "\n Date de publication : " + publicationDate +
                "\n Édition : " + edition +
                "\n Type : " + subCategory;
    }

    enum SubCategory {
        PRINTED("Imprimé"),
        DIGITAL("Électronique");

        private final String displayName;

        SubCategory(String displayName) {
            this.displayName = displayName;
        }

    }

}