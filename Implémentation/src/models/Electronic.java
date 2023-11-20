package models;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Electronic extends Product {

    private final String brand;
    private final String model;
    private final String releaseDate;
    private final String subCategory;


    public Electronic(
             UUID id,
             Set<UUID> instances,
             String name,
             String description,
             double price,
             int likes,
             List<Review> reviews,
             float rating,
             String category,
             boolean isPromoted,
             String brand,
             String model,
             String releaseDate,
             String subCategory
    ) {

        super(id, instances, name, description, price, likes, reviews, rating, category, isPromoted);
        this.brand = brand;
        this.model = model;
        this.releaseDate = releaseDate;
        this.subCategory = subCategory;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getSubCategory() {
        return subCategory;
    }

    @Override
    public String productDetailsToString() {
        return super.productDetailsToString() +
                "Marque : " + brand + "\n" +
                "Modèle : " + model + "\n" +
                "Date de sortie : " + releaseDate + "\n" +
                "Sous-catégorie : " + subCategory + "\n";
    }

    @Override
    public List<String> propertiesToString() {
        List<String> propertiesNames = super.propertiesToString();
        propertiesNames.addAll(List.of("marque", "modèle", "date de sortie", "sous-catégorie"));
        return propertiesNames;
    }
}