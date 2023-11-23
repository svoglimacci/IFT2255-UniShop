package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonTypeName("Buyer")
public class Buyer extends User {

  @JsonProperty("first_name")
  private String firstName;
  @JsonProperty("last_name")
  private String lastName;

  @JsonProperty("liked_products")
  private List<UUID> likedProducts = new ArrayList<>();

  @JsonProperty("liked_sellers")
  private List<UUID> likedSellers = new ArrayList<>();

  @JsonProperty("reviews")
  private List<UUID> reviews = new ArrayList<>();


  @JsonProperty("shopping_cart")
  private Cart shoppingCart = new Cart();

  @JsonCreator
  public Buyer(@JsonProperty("username") String username, @JsonProperty("password") String password,
      @JsonProperty("email") String email, @JsonProperty("phone_number") String phoneNumber,
      @JsonProperty("address") String address, @JsonProperty("first_name") String firstName,
      @JsonProperty("last_name") String lastName) {
    super(username, password, email, phoneNumber, address);
    this.firstName = firstName;
    this.lastName = lastName;

  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<UUID> getLikedProducts() {
    return likedProducts;
  }

  public void setLikedProducts(List<UUID> likedProducts) {
    this.likedProducts = likedProducts;
  }

  public List<UUID> getLikedUsers() {
    return likedSellers;
  }

  public void setLikedUsers(List<UUID> likedUsers) {
    this.likedSellers = likedUsers;
  }

  public List<UUID> getReviews() {
    return reviews;
  }

  public void setReviews(List<UUID> reviews) {
    this.reviews = reviews;
  }


@JsonProperty("shopping_cart")
  public Cart getCart() {
    return shoppingCart;
  }
}