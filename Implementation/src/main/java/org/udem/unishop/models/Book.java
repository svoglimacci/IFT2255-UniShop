package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.UUID;

/**
 * The Book class represents a book product.
 */
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

  /**
   * Constructor for Book object.
   *
   * @param name The name of the book.
   * @param description The description of the book.
   * @param instances The instances of the book.
   * @param price The price of the book.
   * @param bonusPoints The bonus points associated with the book.
   * @param media The media associated with the book.
   * @param isbn The ISBN of the book.
   * @param author The author of the book.
   * @param publisher The publisher of the book.
   * @param genre The genre of the book.
   * @param publicationDate The publication date of the book.
   * @param edition The edition of the book.
   * @param volume The volume of the book.
   */
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

  /**
   * Gets the ISBN of the book.
   *
   * @return The ISBN of the book.
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * Sets the ISBN of the book.
   *
   * @param isbn The ISBN to set.
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * Gets the author of the book.
   *
   * @return The author of the book.
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Sets the author of the book.
   *
   * @param author The author to set.
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Gets the publisher of the book.
   *
   * @return The publisher of the book.
   */
  public String getPublisher() {
    return publisher;
  }

  /**
   * Sets the publisher of the book.
   *
   * @param publisher The publisher to set.
   */
  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  /**
   * Gets the genre of the book.
   *
   * @return The genre of the book.
   */
  public String getGenre() {
    return genre;
  }

  /**
   * Sets the genre of the book.
   *
   * @param genre The genre to set.
   */
  public void setGenre(String genre) {
    this.genre = genre;
  }

  /**
   * Gets the publication date of the book.
   *
   * @return The publication date of the book.
   */
  public String getPublicationDate() {
    return publicationDate;
  }

  /**
   * Sets the publication date of the book.
   *
   * @param publicationDate The publication date to set.
   */
  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  /**
   * Gets the edition of the book.
   *
   * @return The edition of the book.
   */
  public String getEdition() {
    return edition;
  }

  /**
   * Sets the edition of the book.
   *
   * @param edition The edition to set.
   */
  public void setEdition(String edition) {
    this.edition = edition;
  }

  /**
   * Gets the volume of the book.
   *
   * @return The volume of the book.
   */
  public String getVolume() {
    return volume;
  }

  /**
   * Sets the volume of the book.
   *
   * @param volume The volume to set.
   */
  public void setVolume(String volume) {
    this.volume = volume;
  }


}