package models;


import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Book extends Product {

    private final String genre;
    private final String isbn;
    private final String author;
    private final String publisher;
    private final String publicationYear;
    private final String edition;
    private final String volume;


    public Book(
       UUID id,
 Set<UUID> instances,
 String name,
String description,
 double price,
 int likes,
List<Review> reviews,
 float rating,
 String category,
    boolean isPromoted,
String isbn,
    String author,
    String publisher,
   String publicationYear,
       String edition,
         String volume,
     String genre) {
        super(id, instances, name, description, price, likes, reviews, rating, category, isPromoted);

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

    public String getPublicationYear() {
        return publicationYear;
    }

    public String getEdition() {
        return edition;
    }

    public String getVolume() {
        return volume;
    }

    public String getGenre() {
        return genre;
    }

}