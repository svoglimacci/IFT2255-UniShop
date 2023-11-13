package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;
import java.util.UUID;


public class OfficeFurniture extends Product {

    private final String brand;
    private final String model;
    private final String subCategory;

    @JsonCreator
    public OfficeFurniture(
            @JsonProperty("id") UUID id,
            @JsonProperty("instances") Set<UUID> instances,
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
            @JsonProperty("subCategory") String subCategory
    ) {
        super(id, instances, name, description, price, likes, reviews, rating, category, isPromoted);
        this.brand = brand;
        this.model = model;
        this.subCategory = subCategory;
    }

    public String getModel() {
        return model;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String productDetailsToString() {
        return super.productDetailsToString() +
                "Marque : " + brand + "\n" +
                "Modèle : " + model + "\n" +
                "Sous-catégorie : " + subCategory + "\n";
    }

    @Override
    public List<String> propertiesToString() {
        List<String> propertiesNames = super.propertiesToString();
        propertiesNames.addAll(List.of("marque", "modèle", "sous-catégorie"));
        return propertiesNames;
    }
}