package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * The Buyer class represents a buyer user.
 */
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

  @JsonProperty("followed")
  private List<UUID> followed = new ArrayList<>();

  @JsonProperty("followers")
  private List<UUID> followers = new ArrayList<>();

  @JsonProperty("reviews")
  private List<UUID> reviews = new ArrayList<>();

  @JsonProperty("shopping_cart")
  private Cart shoppingCart = new Cart();

  @JsonProperty("fidelity_points")
  private int fidelityPoints = 0;

  /**
   * Constructor for Buyer object.
   *
   * @param username The unique username for the buyer.
   * @param password The password associated with the buyer's account.
   * @param email The email address of the buyer.
   * @param phoneNumber The phone number of the buyer.
   * @param address The physical address of the buyer.
   * @param firstName The first name of the buyer.
   * @param lastName The last name of the buyer.
   */
  @JsonCreator
  public Buyer(@JsonProperty("username") String username, @JsonProperty("password") String password,
      @JsonProperty("email") String email, @JsonProperty("phone_number") String phoneNumber,
      @JsonProperty("address") String address, @JsonProperty("first_name") String firstName,
      @JsonProperty("last_name") String lastName) {
    super(username, password, email, phoneNumber, address);
    this.firstName = firstName;
    this.lastName = lastName;

  }

  /**
   * Gets the first name of the buyer.
   *
   * @return the first name of the buyer.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the buyer.
   *
   * @param firstName The first name to set.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of the buyer.
   *
   * @return the last name of the buyer.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the buyer.
   *
   * @param lastName The last name to set.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the UUID list of liked products of the buyer.
   *
   * @return the UUID list of liked products.
   */
  public List<UUID> getLikedProducts() {
    return likedProducts;
  }

  /**
   * Sets the UUID list of liked products of the buyer.
   *
   * @param likedProducts The UUID list of liked products to set.
   */
  public void setLikedProducts(List<UUID> likedProducts) {
    this.likedProducts = likedProducts;
  }

  /**
   * Gets the UUID list of liked sellers of the buyer.
   *
   * @return The UUID list of liked sellers.
   */
  public List<UUID> getLikedSeller() {
    return likedSellers;
  }

  /**
   * Sets the UUID list of liked sellers of the buyer.
   *
   * @param likedProducts The UUID list of liked sellers to set.
   */
  public void setLikedSellers(List<UUID> likedSellers) {
    this.likedSellers = likedSellers;
  }

  /**
   * Gets the UUID list of reviews written by the buyer.
   *
   * @return The UUID list of reviews.
   */
  public List<UUID> getReviews() {
    return reviews;
  }

  /**
   * Sets the UUID list of reviews written by the buyer.
   *
   * @param likedProducts The UUID list of reviews to set.
   */
  public void setReviews(List<UUID> reviews) {
    this.reviews = reviews;
  }

  /**
   * Gets the shopping cart of the buyer.
   *
   * @return the shopping cart of the buyer.
   */
  @JsonProperty("shopping_cart")
  public Cart getCart() {
    return shoppingCart;
  }

  /**
   * Gets the UUID list of followed users of the buyer.
   *
   * @return The UUID list of followed users.
   */
  public List<UUID> getFollowed() {
    return followed;
  }

  /**
   * Sets the UUID list of followed users of the buyer.
   *
   * @param isb++ The UUID list of followed users of the buyer.
   */
  public void setFollowed(List<UUID> followed) {
    this.followed = followed;
  }

  /**
   * Gets the UUID list of buyer's followers.
   *
   * @return The UUID list of buyer's followers.
   */
  public List<UUID> getFollowers() {
    return followers;
  }

  /**
   * Sets the ISBN of the book.
   *
   * @param isb The ISBN to set.
   */
  public void setFollowers(List<UUID> followers) {
    this.followers = followers;
  }

  /**
   * Gets the fidelity points of the buyer.
   *
   * @return the fidelity points of the buyer.
   */
  public int getFidelityPoints() {
    return fidelityPoints;
  }

  /**
   * Adds fidelity points to buyer's profile.
   *
   * @param fidelityPoints The fidelity points to be added.
   */
  public void addFidelityPoints(int fidelityPoints) {
    this.fidelityPoints += fidelityPoints;
  }

  /**
   * Removes fidelity points from buyer's profile.
   *
   * @param fidelityPoints The fidelity points to be deducted
   */
  public void removeFidelityPoints(int fidelityPoints) {
    this.fidelityPoints -= fidelityPoints;
  }

  /**
   * Sets the fidelity points of the buyer.
   *
   * @param fidelityPoints The fidelity points to be set.
   */
  public void setFidelityPoints(int fidelityPoints) {
    this.fidelityPoints = fidelityPoints;
  }

}