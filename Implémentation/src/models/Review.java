package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Review {
    public String comment;
    public String author;

    public float rating;

    public Review(@JsonProperty("comment") String comment,
                  @JsonProperty("author") String author,
                  @JsonProperty("rating") float rating) {
        this.comment = comment;
        this.author = author;
        this.rating = rating;
    }

    @Override
    public String toString() {
        //remove decimal part if it's .0
        if (rating % 1 == 0) {
            return "Avis de " + author + " : " + comment + " (" + (int) rating + "/5)";
        }
        return "Avis de " + author + " : " + comment + " (" + rating + "/5)";
    }

}