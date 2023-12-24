package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.UUID;

/**
 * Represents a computer hardware product.
 */
@JsonTypeName("computer_hardware")
public class ComputerHardware extends Product {

  @JsonProperty("brand")
  private String brand;

  @JsonProperty("model")
  private String model;

  @JsonProperty("launch_date")
  private String launchDate;

  @JsonProperty("sub_category")
  private String subCategory;

  /**
   * Constructs a ComputerHardware object with the specified properties.
   *
   * @param name The name of the computer hardware.
   * @param description The description of the computer hardware.
   * @param instances The list of instances of the computer hardware.
   * @param price The price of the computer hardware.
   * @param bonusPoints The bonus points associated with the computer hardware.
   * @param media The media associated with the computer hardware.
   * @param brand The brand of the computer hardware.
   * @param model The model of the computer hardware.
   * @param launchDate The launch date of the computer hardware.
   * @param subCategory The sub-category of the computer hardware.
   */
  @JsonCreator
  public ComputerHardware(@JsonProperty("name") String name,
      @JsonProperty("description") String description, @JsonProperty("stock") List<UUID> instances,
      @JsonProperty("price") double price, @JsonProperty("bonus_points") int bonusPoints, @JsonProperty("media") String media,
      @JsonProperty("brand") String brand, @JsonProperty("model") String model,
      @JsonProperty("launch_date") String launchDate,
      @JsonProperty("sub_category") String subCategory) {
    super(name, description, instances, price, bonusPoints, 0, 0, media);
    this.brand = brand;
    this.model = model;
    this.launchDate = launchDate;
    this.subCategory = subCategory;
  }

  /**
   * Gets the brand of the computer hardware.
   *
   * @return The brand of the computer hardware.
   */
  public String getBrand() {
    return brand;
  }

  /**
   * Sets the brand of the computer hardware.
   *
   * @param brand The brand of the computer hardware.
   */
  public void setBrand(String brand) {
    this.brand = brand;
  }

  /**
   * Gets the model of the computer hardware.
   *
   * @return The model of the computer hardware.
   */
  public String getModel() {
    return model;
  }

  /**
   * Sets the model of the computer hardware.
   *
   * @param model The model of the computer hardware.
   */
  public void setModel(String model) {
    this.model = model;
  }

  /**
   * Gets the launch date of the computer hardware.
   *
   * @return The launch date of the computer hardware.
   */
  public String getLaunchDate() {
    return launchDate;
  }

  /**
   * Sets the launch date of the computer hardware.
   *
   * @param launchDate The launch date of the computer hardware.
   */
  public void setLaunchDate(String launchDate) {
    this.launchDate = launchDate;
  }

  /**
   * Gets the sub-category of the computer hardware.
   *
   * @return The sub-category of the computer hardware.
   */
  public String getSubCategory() {
    return subCategory;
  }

  /**
   * Sets the sub-category of the computer hardware.
   *
   * @param subCategory The sub-category of the computer hardware.
   */
  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }
}