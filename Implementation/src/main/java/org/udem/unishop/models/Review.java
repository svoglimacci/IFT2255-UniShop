package org.udem.unishop.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Review {
    public UUID id;
    public String comment;
    public String author;
    public float rating;

    public Review(
                  @JsonProperty("author") String author,
                  @JsonProperty("comment") String comment,
                  @JsonProperty("rating") int rating) {
        this.id = UUID.randomUUID();
        this.author = author;
        this.comment = comment;
        this.rating = rating;
    }

    public UUID getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getAuthor() {
        return author;
    }

    public float getRating() {
        return rating;
    }
}