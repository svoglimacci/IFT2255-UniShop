package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.udem.unishop.utilities.AccountType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "account_type")
@JsonSubTypes({@JsonSubTypes.Type(value = Buyer.class, name = "buyer"),
    @JsonSubTypes.Type(value = Seller.class, name = "seller")})

public class User {

  @JsonIgnore
  private final AccountType accountType = AccountType.valueOf(
      this.getClass().getSimpleName().toUpperCase());

  @JsonProperty("id")
  private UUID id;
  @JsonProperty("username")
  private String username;
  @JsonProperty("password")
  private String password;

  @JsonProperty("email")
  private String email;

  @JsonProperty("phone_number")
  private String phoneNumber;

  @JsonProperty("address")
  private String address;

  @JsonProperty("creation_date")
  private Date creationDate = new Date();

  @JsonProperty("status")
  private boolean status = false;

  @JsonProperty("products_ids")
  private Set<UUID> productsIds = new HashSet<>();

  @JsonCreator
  public User(@JsonProperty("username") String username, @JsonProperty("password") String password,
      @JsonProperty("email") String email, @JsonProperty("phone_number") String phoneNumber,
      @JsonProperty("address") String address) {
    this.id = (id != null) ? id : UUID.randomUUID();
    this.username = username;
    this.password = password;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public AccountType getAccountType() {
    return accountType;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Set<UUID> getProductsIds() {
    return productsIds;
  }

  public void setProductsIds(Set<UUID> productsIds) {
    this.productsIds = productsIds;
  }

  public void addProductId(UUID productId) {
    this.productsIds.add(productId);
  }


}