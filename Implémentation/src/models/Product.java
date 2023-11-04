package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

class Review {
    private String comment;
    private String author;

    private int rating;

    public Review(@JsonProperty("comment") String comment,
                   @JsonProperty("author") String author,
                   @JsonProperty("rating") int rating) {
        this.comment = comment;
        this.author = author;
        this.rating = rating;
    }
}

public class Product {

    private String name;
    private String description;
    private double price;

    private int likes;

    private List<Review> reviews;

    private float rating;

    private String category;

    private boolean isPromoted;

    public Product(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("price") double price,
                     @JsonProperty("likes") int likes,
                     @JsonProperty("comments") List<Review> reviews,
                   @JsonProperty("rating") float rating,
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
    //getter
    public String getName() {
        return this.name;
    }

}