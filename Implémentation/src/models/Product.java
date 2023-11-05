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
        this.rating = getRating();
        this.category = category;
        this.isPromoted = isPromoted;

    }

    public float getRating() {
        //average rating
        float sum = 0;
        for (Review review : reviews) {
            sum += review.rating;
        }
        return sum / reviews.size();
    }

    public String productToString() {
        return name + " " +
                price + "$ " +
                rating + "/5 " + "(" + reviews.size() + ")" + "\n";

    }


    public String productDetailsToString() {
        StringBuilder reviewString = new StringBuilder();
        for (Review review : reviews) {
            reviewString.append(review).append("\n");
        }

        return "Name: " + name + "\n" +
                "Description: " + description + "\n" +
                "Price: " + price + "$" + "\n" +
                "Likes: " + likes + "\n" +
                "Rating: " + rating + "/5" + "\n" +
                "Reviews: " + reviewString + "\n";
    }


}