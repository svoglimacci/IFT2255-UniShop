package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.UUID;

/**
 * Represents a learning resource product.
 */
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

  /**
   * Cosntructs a LearningResource object.
   *
   * @param name The name of the learning resource.
   * @param description The description of the learning resource.
   * @param instances The instances of the learning resource.
   * @param price The price of the learning resource.
   * @param bonusPoints The bonus points associated with the learning resource.
   * @param media The media associated with the learning resource.
   * @param isbn The ISBN of the learning resource.
   * @param author The author of the learning resource.
   * @param organization The organization of the learning resource.
   * @param publicationDate The publication date of the learning resource.
   * @param type The type of the learning resource.
   * @param edition The edition of the learning resource.
   */
  @JsonCreator
  public LearningResource(@JsonProperty("name") String name,
      @JsonProperty("description") String description, @JsonProperty("stock") List<UUID> instances,
      @JsonProperty("price") double price, @JsonProperty("bonus_points") int bonusPoints, @JsonProperty("media") String media,
      @JsonProperty("isbn") String isbn, @JsonProperty("author") String author,
      @JsonProperty("organization") String organization,
      @JsonProperty("publication_date") String publicationDate, @JsonProperty("type") String type,
      @JsonProperty("edition") String edition) {
    super(name, description, instances, price, bonusPoints, 0, 0, media);
    this.isbn = isbn;
    this.author = author;
    this.organization = organization;
    this.publicationDate = publicationDate;
    this.type = type;
    this.edition = edition;

  }

  /**
   * Gets the ISBN of the learning resource.
   *
   * @return The ISBN of the learning resource.
   */
  public String getIsbn() {
    return isbn;
  }

  /**
   * Sets the ISBN of the learning resource.
   *
   * @param isbn The ISBN to be set.
   */
  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  /**
   * Gets the author of the learning resource.
   *
   * @return The author of the learning resource.
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Sets the author of the learning resource.
   *
   * @param author of the learning resource.
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * Gets the organization of the learning resource.
   *
   * @return The organization of the learning resource.
   */
  public String getOrganization() {
    return organization;
  }

  /**
   * Sets the ogranization of the learning resource.
   *
   * @param organization The oraganization to be set.
   */
  public void setOrganization(String organization) {
    this.organization = organization;
  }

  /**
   * Gets the publication date of the learning resource.
   *
   * @return The publication date of the learning resource.
   */
  public String getPublicationDate() {
    return publicationDate;
  }

  /**
   * Sets the publication date of the learning resource.
   *
   * @param publicationDate The publication date of the learning resource.
   */
  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  /**
   * Gets the type of the learning resource.
   *
   * @return The type of the learning resource.
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of the learning resource.
   *
   * @param type The type to be set.
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the edition of the learning resource.
   *
   * @return The edition of the learning resource.
   */
  public String getEdition() {
    return edition;
  }

  /**
   * Sets the edition of the learning resource.
   *
   * @param edition The edition to be set.
   */
  public void setEdition(String edition) {
    this.edition = edition;
  }

}