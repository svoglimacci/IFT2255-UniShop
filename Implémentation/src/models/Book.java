package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Book extends Product {

    private final String genre;
    private String isbn;
    private String author;
    private String publisher;
    private int publicationYear;
    private int edition;
    private int volume;

    @JsonCreator
    public Book(@JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("price") double price,
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

        super(name, description, price, likes, reviews, rating, category, isPromoted);
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.edition = edition;
        this.volume = volume;
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Auteur : " + author +
                "\nISBN : " + isbn +
                "\nÉdition : " + edition +
                "\nVolume : " + volume +
                "\nGenre : " + genre +
                "\nMaison d'édition : " + publisher +
                "\nAnnée de publication : " + publicationYear;
    }

    enum genre {
        ACADEMIC("Académique"),
        NOVEL("Roman"),
        DOCUMENTARY("Documentaire"),
        COMIC("Bande dessinée");


        private final String displayName;

        genre(String displayName) {
            this.displayName = displayName;
        }

    }

}