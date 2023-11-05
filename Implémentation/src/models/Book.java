package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Book extends Product {

    private final String genre;
    private final String isbn;
    private final String author;
    private final String publisher;
    private final int publicationYear;
    private final int edition;
    private final int volume;

    @JsonCreator
    public Book(@JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("price") double price,
                @JsonProperty("quantity") int quantity,
                @JsonProperty("likes") int likes,
                @JsonProperty("reviews") List<Review> reviews,
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
        super(name, description, price, quantity, likes, reviews, rating, category, isPromoted);
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.edition = edition;
        this.volume = volume;
        this.genre = genre;
    }

    @Override
    public String productDetailsToString() {
        return super.productDetailsToString() +
                "Genre : " + genre + "\n" +
                "ISBN : " + isbn + "\n" +
                "Auteur : " + author + "\n" +
                "Editeur : " + publisher + "\n" +
                "Année de publication : " + publicationYear + "\n" +
                "Edition : " + edition + "\n" +
                "Volume : " + volume + "\n";

    }


    @Override
    public List<String> propertiesToString() {
        //return super and append new properties
        List<String> propertiesNames = super.propertiesToString();
        propertiesNames.addAll(List.of("genre", "isbn", "auteur", "editeur", "annee de publication", "edition", "volume"));
        return propertiesNames;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getEdition() {
        return edition;
    }

    public int getVolume() {
        return volume;
    }

    public String getGenre() {
        return genre;
    }
}