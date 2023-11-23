package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.List;
import java.util.UUID;

@JsonTypeName("office_equipment")
public class OfficeEquipment extends Product {

  @JsonProperty("brand")
  private String brand;

  @JsonProperty("model")
  private String model;

  @JsonProperty("sub_category")
  private String subCategory;

  @JsonCreator
  public OfficeEquipment(@JsonProperty("name") String name,
      @JsonProperty("description") String description, @JsonProperty("stock") List<UUID> instances,
      @JsonProperty("price") double price, @JsonProperty("bonus_points") int bonusPoints,
      @JsonProperty("brand") String brand, @JsonProperty("model") String model,
      @JsonProperty("sub_category") String subCategory) {
    super(name, description, instances, price, bonusPoints);
    this.brand = brand;
    this.model = model;
    this.subCategory = subCategory;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }
}