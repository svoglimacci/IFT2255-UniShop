package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Product {

    public int quantity;
    public String name;
    public String description;
    public double price;

    public int likes;

    public List<Review> reviews;

    public float rating;

    public String category;

    public boolean isPromoted;

    @JsonCreator
    public Product(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("price") double price,
                   @JsonProperty("quantity") int quantity,
                   @JsonProperty("likes") int likes,
                   @JsonProperty("reviews") List<Review> reviews,
                   @JsonProperty("rating") float rating,
                   @JsonProperty("category") String category,
                   @JsonProperty("isPromoted") boolean isPromoted) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.likes = likes;
        this.reviews = reviews;
        this.rating = getRating();
        this.category = category;
        this.isPromoted = isPromoted;

    }

    //getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getLikes() {
        return likes;
    }

    public List<Review> getReviews() {
        return reviews;
    }


    public String getCategory() {
        return category;
    }

    public boolean getIsPromoted() {
        return isPromoted;
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

    public List<String> propertiesToString() {
        return List.of("nom", "description", "prix", "quantité");
    }


    public String productDetailsToString() {
        StringBuilder reviewString = new StringBuilder();
        for (Review review : reviews) {
            reviewString.append(review).append("\n");
        }

        return "Name: " + name + "\n" +
                "Description: " + description + "\n" +
                "Price: " + price + "$" + "\n" +
                "Quantity: " + quantity + "\n" +
                "Likes: " + likes + "\n" +
                "Rating: " + rating + "/5" + "\n" +
                "Reviews: " + reviewString + "\n";
    }


}