package org.udem.unishop.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Review {
    public UUID id;
    public String comment;
    public String author;
    public float rating;

    public int likes = 0;

    /**
     * Constructs a Review object with specified properties.
     *
     * @param author The author of the review.
     * @param comment The comment provided for the review.
     * @param rating The rating given in the review.
     */
    public Review(
                  @JsonProperty("author") String author,
                  @JsonProperty("comment") String comment,
                  @JsonProperty("rating") int rating) {
        this.id = UUID.randomUUID();
        this.author = author;
        this.comment = comment;
        this.rating = rating;
    }

    /**
     * Gets the unique identifier of the review.
     *
     * @return the unique identifier of the review.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the comment provided for the review.
     *
     * @return the comment provided for the review.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Gets the author of the review.
     *
     * @return the author of the review.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the rating given in the review.
     *
     * @return the rating given in the review.
     */
    public float getRating() {
        return rating;
    }

    /**
     * Gets the number of likes recevied for the review.
     *
     * @return the number of likes recevied for the review.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Adds a like to the review.
     */
    public void like() {
        likes++;
    }

    /**
     * Removes a like from the review.
     */
    public void dislike() {
        likes--;
    }
}