package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.UUID;

/**
 * Represents a stationery product.
 */
@JsonTypeName("stationery")
public class Stationery extends Product {

  @JsonProperty("brand")
  private String brand;
  @JsonProperty("model")
  private String model;
  @JsonProperty("sub_category")
  private String subCategory;

  /**
   * Constructs a Stationery object with specified properties.
   *
   * @param name The name of the stationery product.
   * @param description The description of the stationery product.
   * @param instances The instance of the stationery product.
   * @param price The price of the stationery product.
   * @param bonusPoints The bonus points associated with the stationery product.
   * @param media The media associated with the stationery product.
   * @param brand The brand of the stationery product.
   * @param model The model of the stationery product.
   * @param subCategory The subcategory of the stationery product.
   */
  @JsonCreator
  public Stationery(@JsonProperty("name") String name,
      @JsonProperty("description") String description, @JsonProperty("stock") List<UUID> instances,
      @JsonProperty("price") double price, @JsonProperty("bonus_points") int bonusPoints, @JsonProperty("media") String media,
      @JsonProperty("brand") String brand, @JsonProperty("model") String model,
      @JsonProperty("sub_category") String subCategory) {
    super(name, description, instances, price, bonusPoints, 0, 0, media);
    this.brand = brand;
    this.model = model;
    this.subCategory = subCategory;
  }

  /**
   * Gets the brand of the stationery product.
   *
   * @return the brand of the stationery product.
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Sets the brand of the stationery product.
   *
   * @param brand The brand of the stationery product to be set.
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Gets the model of the stationery product.
   *
   * @return The model of the stationery product.
   */
  public String getModel() {
    return model;
  }

  /**
   * Sets the model of the stationery product.
   * @param model The model to be set.
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Gets the subcategory of the stationery product.
   *
   * @return The subcategory of the stationery product.
   */
  public String getSubCategory() {
    return subCategory;
  }

  /**
   * Sets the subcategory of the stationery product.
   *
   * @param subCategory The subcategory to be set.
   */
  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }

}