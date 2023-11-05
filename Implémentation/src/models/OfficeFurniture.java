package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class OfficeFurniture extends Product {

    private final String brand;
    private final String model;
    private final String subCategory;

    @JsonCreator
    public OfficeFurniture(
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

    @Override
    public String productDetailsToString() {
        return super.productDetailsToString() +
                "Marque : " + brand + "\n" +
                "Modèle : " + model + "\n" +
                "Sous-catégorie : " + subCategory + "\n";
    }

}