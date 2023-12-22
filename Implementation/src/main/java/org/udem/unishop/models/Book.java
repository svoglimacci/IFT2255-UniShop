package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.UUID;

@JsonTypeName("book")
public class Book extends Product {

  @JsonProperty("isbn")
  private String isbn;
  @JsonProperty("author")
  private String author;
  @JsonProperty("publisher")
  private String publisher;
  @JsonProperty("genre")
  private String genre;
  @JsonProperty("publication_date")
  private String publicationDate;
  @JsonProperty("edition")
  private String edition;
  @JsonProperty("volume")
  private String volume;

  @JsonCreator
  public Book(@JsonProperty("name") String name, @JsonProperty("description") String description,
      @JsonProperty("stock") List<UUID> instances, @JsonProperty("price") double price,
      @JsonProperty("bonus_points") int bonusPoints, @JsonProperty("media") String media, @JsonProperty("isbn") String isbn,
      @JsonProperty("author") String author, @JsonProperty("publisher") String publisher,
      @JsonProperty("genre") String genre, @JsonProperty("publication_date") String publicationDate,
      @JsonProperty("edition") String edition, @JsonProperty("volume") String volume) {
    super(name, description, instances, price, bonusPoints, 0, 0, media);
    this.isbn = isbn;
    this.author = author;
    this.publisher = publisher;
    this.genre = genre;
    this.publicationDate = publicationDate;
    this.edition = edition;
    this.volume = volume;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  public String getEdition() {
    return edition;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

  public String getVolume() {
    return volume;
  }

  public void setVolume(String volume) {
    this.volume = volume;
  }


}