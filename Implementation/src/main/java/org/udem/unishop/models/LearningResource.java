package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.UUID;

@JsonTypeName("learning_resource")
public class LearningResource extends Product {

  @JsonProperty
  private String isbn;
  @JsonProperty
  private String author;
  @JsonProperty
  private String organization;
  @JsonProperty
  private String publicationDate;
  @JsonProperty
  private String type;
  @JsonProperty
  private String edition;

  @JsonCreator
  public LearningResource(@JsonProperty("name") String name,
      @JsonProperty("description") String description, @JsonProperty("stock") List<UUID> instances,
      @JsonProperty("price") double price, @JsonProperty("bonus_points") int bonusPoints,
      @JsonProperty("isbn") String isbn, @JsonProperty("author") String author,
      @JsonProperty("organization") String organization,
      @JsonProperty("publication_date") String publicationDate, @JsonProperty("type") String type,
      @JsonProperty("edition") String edition) {
    super(name, description, instances, price, bonusPoints);
    this.isbn = isbn;
    this.author = author;
    this.organization = organization;
    this.publicationDate = publicationDate;
    this.type = type;
    this.edition = edition;
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

  public String getOrganization() {
    return organization;
  }

  public void setOrganization(String organization) {
    this.organization = organization;
  }

  public String getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  public String getType() {
    return type;
  }

  public void setType(String volume) {
    this.type = type;
  }

  public String getEdition() {
    return edition;
  }

  public void setEdition(String edition) {
    this.edition = edition;
  }

}