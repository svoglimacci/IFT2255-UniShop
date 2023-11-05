package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LearningMaterial extends Product {

    private final String brand;
    private final String model;
    private final String subCategory;
    private final String isbn;
    private final String author;
    private final String organization;
    private final String publicationDate;
    private final String edition;

    @JsonCreator
    public LearningMaterial(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("price") double price,
            @JsonProperty("quantity") int quantity,
            @JsonProperty("likes") int likes,
            @JsonProperty("reviews") List<Review> reviews,
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
        super(name, description, price, quantity, likes, reviews, rating, category, isPromoted);
        this.brand = brand;
        this.model = model;
        this.subCategory = subCategory;
        this.author = author;
        this.isbn = isbn;
        this.organization = organization;
        this.publicationDate = publicationDate;
        this.edition = edition;
    }

    //getter
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getOrganization() {
        return organization;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getEdition() {
        return edition;
    }


    @Override
    public String productDetailsToString() {
        return super.productDetailsToString() +
                "Marque : " + brand + "\n" +
                "Modèle : " + model + "\n" +
                "Sous-catégorie : " + subCategory + "\n" +
                "Auteur : " + author + "\n" +
                "ISBN : " + isbn + "\n" +
                "Organisation : " + organization + "\n" +
                "Date de publication : " + publicationDate + "\n" +
                "Édition : " + edition + "\n";
    }

    @Override
    public List<String> propertiesToString() {
        //return super and append new properties
        List<String> propertiesNames = super.propertiesToString();
        propertiesNames.addAll(List.of("marque", "modèle", "sous-catégorie", "auteur", "isbn", "organisation", "date de publication", "édition"));
        return propertiesNames;
    }

}