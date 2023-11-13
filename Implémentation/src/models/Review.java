package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
    public String comment;
    public String author;

    public float rating;

    public Review(
                  @JsonProperty("author") String author,
                  @JsonProperty("comment") String comment,
                  @JsonProperty("rating") float rating) {
        this.author = author;
        this.comment = comment;
        this.rating = rating;
    }

    @Override
    public String toString() {
        if (rating % 1 == 0) {
            return "Avis de " + author + " : " + comment + " (" + (int) rating + "/5)";
        }
        return "Avis de " + author + " : " + comment + " (" + rating + "/5)";
    }

}