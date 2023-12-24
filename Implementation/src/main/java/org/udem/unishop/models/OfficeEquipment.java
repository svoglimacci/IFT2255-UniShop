package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.UUID;

/**
 * Represents an office equipment product.
 */
@JsonTypeName("office_equipment")
public class OfficeEquipment extends Product {

  @JsonProperty("brand")
  private String brand;

  @JsonProperty("model")
  private String model;

  @JsonProperty("sub_category")
  private String subCategory;

  /**
   * Constructs an OfficeEquipment object with the specified properties.
   *
   * @param name        The name of the office equipment.
   * @param description The description of the office equipment.
   * @param instances   The list of instances of the office equipment.
   * @param price       The price of the office equipment.
   * @param bonusPoints The bonus points associated with the office equipment.
   * @param media       The media (image or video link) associated with the office equipment.
   * @param brand       The brand of the office equipment.
   * @param model       The model of the office equipment.
   * @param subCategory The sub-category of the office equipment.
   */
  @JsonCreator
  public OfficeEquipment(@JsonProperty("name") String name,
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
   * Gets the brand of the office equipment.
   *
   * @return The brand of the office equipment.
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Sets the brand of the office equipment.
   *
   * @param brand The brand of the office equipment.
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Gets the model of the office equipment.
   *
   * @return The model of the office equipment.
   */
  public String getModel() {
    return model;
  }

  /**
   * Sets the model of the office equipment.
   *
   * @param model The model of the office equipment.
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Gets the sub-category of the office equipment.
   *
   * @return The sub-category of the office equipment.
   */
  public String getSubCategory() {
    return subCategory;
  }

  /**
   * Sets the sub-category of the office equipment.
   *
   * @param subCategory The sub-category of the office equipment.
   */
  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }
}