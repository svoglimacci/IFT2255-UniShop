package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a seller user.
 */
@JsonTypeName("Seller")
public class Seller extends User {

  @JsonProperty("order_list")
  private final List<Order> orderList = new ArrayList<>();

  @JsonProperty("company_name")
  private String companyName;

  @JsonProperty("likes")
  private int likes;

  /**
   * Constructs a Seller object.
   *
   * @param username The username of the seller.
   * @param password The password associated with the seller's account.
   * @param email The email of the seller.
   * @param phoneNumber The phone number of the seller.
   * @param address The physical address of the seller.
   * @param companyName The company name of the seller.
   */
  @JsonCreator
  public Seller(@JsonProperty("username") String username,
      @JsonProperty("password") String password, @JsonProperty("email") String email,
      @JsonProperty("phone_number") String phoneNumber, @JsonProperty("address") String address,
      @JsonProperty("company_name") String companyName) {
    super(username, password, email, phoneNumber, address);
    this.companyName = companyName;
    this.likes = 0;}

  /**
   * Gets the company name of the seller.
   *
   * @return The company name of the seller.
   */
  public String getCompanyName() {
    return companyName;
  }

  /**
   * Sets the company name of the seller.
   *
   * @param companyName The company name to be set.
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  /**
   * Gets the number of likes received by the seller.
   *
   * @return The number of likes received by the seller.
   */
  public int getLikes() {
    return likes;

  }

  /**
   * Sets the number of likes received by the seller.
   *
   * @param likes The number of likes received by the seller.
   */
  public void setLikes(int likes) {
    this.likes = likes;
  }



}