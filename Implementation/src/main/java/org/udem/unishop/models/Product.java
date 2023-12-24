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

/**
 * Represents a purchasable product.
 */
public class Product {



  @JsonIgnore
  private final ProductType productType = (this instanceof Book) ? ProductType.BOOK
      : (this instanceof Stationery) ? ProductType.STATIONERY
          : (this instanceof LearningResource) ? ProductType.LEARNING_RESOURCE
              : (this instanceof ComputerHardware) ? ProductType.COMPUTER_HARDWARE
                  : (this instanceof OfficeEquipment) ? ProductType.OFFICE_EQUIPMENT
                      : null;
  @JsonProperty("id")
  private UUID id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("description")
  private String description;
  @JsonProperty("instances")
  private List<UUID> instances;
  @JsonProperty("price")
  private double price = 0;
  @JsonProperty("bonus_points")
  private int bonusPoints = 0;

  @JsonProperty("promotion_price")
  private int promotionPrice = 0;

  @JsonProperty("promotion_points")
  private int promotionPoints = 0;

  @JsonProperty("seller_id")
  private UUID sellerId;

  @JsonProperty("likes")
  private int likes = 0;

  @JsonProperty("reviews")
  private List<Review> reviews;

  @JsonProperty("media")
  private String media;

  /**
   * Constructs a Product object with specified properties.
   *
   * @param name The name of the product.
   * @param description The description of the product.
   * @param instances The instance of the product.
   * @param price The unit price of the product.
   * @param bonusPoints The bonus points associated with the product.
   * @param promotionPrice The promoted price of the product.
   * @param promotionPoints The promotion points associated with the product.
   * @param media The media associated with the product.
   */
  @JsonCreator
  public Product(@JsonProperty("name") String name, @JsonProperty("description") String description,
      @JsonProperty("instances") List<UUID> instances, @JsonProperty("price") double price,
      @JsonProperty("bonus_points") int bonusPoints, @JsonProperty("promotion_price") double promotionPrice, @JsonProperty("promotion_points") int promotionPoints, @JsonProperty("media") String media) {
    this.id = (id != null) ? id : UUID.randomUUID();
    this.name = name;
    this.description = description;
    this.instances = instances;
    this.price = price - promotionPrice;
    this.bonusPoints = bonusPoints + promotionPoints;
    this.media = media;
    this.reviews = new ArrayList<>();
  }

  /**
   * Gets the unique identifier of the product.
   *
   * @return the unique identifier of the product.
   */
  public UUID getId() {
    return id;
  }

  /**
   * Sets the unique identifier of the product.
   *
   * @param id the unique identifier to be set.
   */
  public void setId(UUID id) {
    this.id = id;
  }

  /**
   * Gets the name of the product.
   *
   * @return the name of the product.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the product.
   *
   * @param name The name to be set.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the description of the product.
   *
   * @return the description of the product.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the product.
   *
   * @param description The description of the product.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the list of instances of the product.
   * @return the list of instances.
   */
  public List<UUID> getInstances() {
    return instances;
  }

  /**
   * Sets a list of instances of the product.
   *
   * @param instances The list of instances to be set.
   */
  public void setInstances(List<UUID> instances) {
    this.instances = instances;
  }

  /**
   * Gets the price of the product.
   *
   * @return The price of the product.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the price of the product.
   *
   * @param price The price of the product.
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Gets the bonus points associated with the product.
   *
   * @return the bonus points associated with the product.
   */
  public int getBonusPoints() {
    return bonusPoints;
  }

  /**
   * Sets the bonus points associated with the product.
   *
   * @param bonusPoints The bonus points associated with the product.
   */
  public void setBonusPoints(int bonusPoints) {
    this.bonusPoints = bonusPoints;
  }

  /**
   * Gets the type of the product.
   *
   * @return The type of the product.
   */
  public ProductType getProductType() {
    return productType;
  }

  /**
   * Gets the unique identifier of the product's seller.
   *
   * @return the unique identifier of the product's seller.
   */
  public UUID getSellerId() {
    return sellerId;
  }

  /**
   * Sets the unique identifier of the product's seller.
   *
   * @param sellerId The unique identifier of the product's seller.
   */
  public void setSellerId(UUID sellerId) {
    this.sellerId = sellerId;
  }

  /**
   * Gets the number of likes associated with the product.
   *
   * @return the number of likes associated with the product.
   */
  public int getLikes() {
    return likes;
  }

  /**
   * Sets the number of likes associated with the product.
   * @param likes The number of likes associated with the product.
   */
  public void setLikes(int likes) {
    this.likes = likes;
  }

  /**
   * Gets the available quantity of the product.
   *
   * @return The available quantity
   */
  @JsonIgnore
  public int getQuantity() {
    return instances.size();
  }

  /**
   * Gets the list of reviews associated with the product.
   *
   * @return The list of reviews associated with the product.
   */
  public List<Review> getReviews() {
    return reviews;
  }

  /**
   * Sets the list of reviews associated with the product.
   * @param reviews The list of reviews associated with the product.
   */
  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  /**
   * Adds a review to the product.
   *
   * @param review The review to be added.
   */
  public void addReview(Review review) {
    this.reviews.add(review);

  }

  /**
   * Adds an instance to the product.
   *
   * @param instance The instance to be added.
   */
  public void addInstance(UUID instance) {
    this.instances.add(instance);
  }

  /**
   * Removes an isntance from the product.
   *
   * @param uuid The instance to be removed.
   */
  public void removeInstance(UUID uuid) {
    this.instances.remove(uuid);
  }

  /**
   * Gets the promotion points associated with the product.
   *
   * @return the promotion points associated with the product.
   */
  public int getPromotionPoints() {
    return promotionPoints;
  }

  /**
   * Sets the promotion points associated with the product.
   *
   * @param promotionPoints The promotion points to be added.
   */
  public void setPromotionPoints(int promotionPoints) {
    this.promotionPoints = promotionPoints;
  }

  /**
   * Gets the promotion price associated with the product.
   *
   * @return the promotion price associated with the product.
   */
  public double getPromotionPrice() {
    return promotionPrice;
  }

  /**
   * Sets the promotion price associated with the product.
   *
   * @param promotionPrice The promotion price to be set.
   */
  public void setPromotionPrice(int promotionPrice) {
    this.promotionPrice = promotionPrice;
  }

  /**
   * Gets the media associated with the product.
   *
   * @return the media associated with the product.
   */
  public String getMedia() {
    return media;
  }

  public Review getReviewById(UUID review) {
    for (Review r : reviews) {
      if (r.getId().equals(review)) {
        return r;
      }
    }
    return null;
  }
}