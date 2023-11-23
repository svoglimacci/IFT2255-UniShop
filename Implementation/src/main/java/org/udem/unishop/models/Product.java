package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.udem.unishop.utilities.ProductType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "product_type")
@JsonSubTypes({@JsonSubTypes.Type(value = Book.class, name = "book"),
    @JsonSubTypes.Type(value = Stationery.class, name = "stationery"),
    @JsonSubTypes.Type(value = LearningResource.class, name = "learning_resource"),
    @JsonSubTypes.Type(value = ComputerHardware.class, name = "computer_hardware"),
    @JsonSubTypes.Type(value = OfficeEquipment.class, name = "office_equipement")})

public class Product {

  @JsonIgnore
  private final ProductType productType = ProductType.valueOf(
      this.getClass().getSimpleName().toUpperCase());
  @JsonProperty("id")
  private UUID id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("description")
  private String description;
  @JsonProperty("instances")
  private List<UUID> instances;
  @JsonProperty("price")
  private double price;
  @JsonProperty("bonus_points")
  private int bonusPoints;
  @JsonProperty("seller_id")
  private UUID sellerId;

  @JsonProperty("likes")
  private int likes = 0;

  @JsonProperty("reviews")
  private List<Review> reviews;

  @JsonCreator
  public Product(@JsonProperty("name") String name, @JsonProperty("description") String description,
      @JsonProperty("instances") List<UUID> instances, @JsonProperty("price") double price,
      @JsonProperty("bonus_points") int bonusPoints) {
    this.id = (id != null) ? id : UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.instances = instances;
    this.price = price;
    this.bonusPoints = (bonusPoints > 0) ? bonusPoints : 0;
    this.reviews = new ArrayList<>();
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<UUID> getInstances() {
    return instances;
  }

  public void setInstances(List<UUID> instances) {
    this.instances = instances;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getBonusPoints() {
    return bonusPoints;
  }

  public void setBonusPoints(int bonusPoints) {
    this.bonusPoints = bonusPoints;
  }


  public ProductType getProductType() {
    return productType;
  }

  public UUID getSellerId() {
    return sellerId;
  }

  public void setSellerId(UUID sellerId) {
    this.sellerId = sellerId;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  @JsonIgnore
  public int getQuantity() {
    return instances.size();
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public void addReview(Review review) {
    this.reviews.add(review);

  }
}