package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Product {

    public String name;
    public String description;
    public double price;

    public int likes;

    public List<Review> reviews;

    public float rating;

    public String category;

    public boolean isPromoted;

    public Product(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("price") double price,
                   @JsonProperty("likes") int likes,
                   @JsonProperty("comments") List<Review> reviews,
                   float rating,
                   @JsonProperty("category") String category,
                   @JsonProperty("isPromoted") boolean isPromoted) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.likes = likes;
        this.reviews = reviews;
        this.rating = rating;
        this.category = category;
        this.isPromoted = isPromoted;

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getLikes() {
        return likes;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public float getRating() {
        //average rating
        float sum = 0;
        for (Review review : reviews) {
            sum += review.getRating();
        }
        return sum / reviews.size();
    }


    @Override
    public String toString() {
        StringBuilder reviewString = new StringBuilder();
        for (Review review : reviews) {
            reviewString.append(review.toString()).append("\n");
        }
        return "Nom : + name +" +
                "\n Description : " + description +
                "\n Prix : " + price +
                "\n Évaluation : " + rating +
                "\n Nombre d'avis : " + reviews.size() +
                "\n Nombre de mentions j'aime : " + likes +
                "\n Reviews : " + reviewString;
    }


}