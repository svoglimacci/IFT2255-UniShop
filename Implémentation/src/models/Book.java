package models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Book extends Product {

    private String isbn;
    private String author;
    private String publisher;
    private int publicationYear;
    private int edition;
    private int volume;
    private final String genre;


    public Book(@JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("price") double price,
                @JsonProperty("likes") int likes,
                @JsonProperty("comments") List<Review> reviews,
                @JsonProperty("rating") float rating,
                @JsonProperty("category") String category,
                @JsonProperty("isPromoted") boolean isPromoted,
                @JsonProperty("isbn") String isbn,
                @JsonProperty("author") String author,
                @JsonProperty("publisher") String publisher,
                @JsonProperty("publicationYear") int publicationYear,
                @JsonProperty("edition") int edition,
                @JsonProperty("volume") int volume,
                @JsonProperty("genre") String genre) {

        super(name, description, price, likes, reviews, rating, category, isPromoted);
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.edition = edition;
        this.volume = volume;
        this.genre = genre;
    }

    //getGenre
    public String getGenre() {
        return this.genre;
    }


}