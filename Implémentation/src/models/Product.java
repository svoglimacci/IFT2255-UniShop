package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Product {

    public UUID id;
    public Set<UUID> instances;

    public String name;
    public String description;
    public double price;

    public int likes;

    public List<Review> reviews;

    public float rating;

    public String category;

    public boolean isPromoted;


    public Product(UUID id,
            Set<UUID> instances,
                  String name,
                    String description,
                   double price,
                    int likes,
                    List<Review> reviews,
                  float rating,
              String category,
              boolean isPromoted) {
        this.id = id;
        this.instances = instances;
        this.name = name;
        this.description = description;
        this.price = price;
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

        if (reviews == null) {
            return 0;
        }
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
                getRating() + "/5 " + "(" + reviews.size() + ")" + "\n";

    }

    public List<String> propertiesToString() {
        return List.of("nom", "description", "prix");
    }


    public String productDetailsToString() {
        rating = getRating();
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


    public UUID getId() {
        return id;
    }

    public Set<UUID> getInstances() {
        return instances;
    }

    public void addLike() {
        this.likes++;
    }


    public void removeLike() {
        this.likes--;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}