package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;

@JsonTypeName("Seller")
public class Seller extends User {

  @JsonProperty("order_list")
  private final List<Order> orderList = new ArrayList<>();

  @JsonProperty("company_name")
  private String companyName;

  @JsonProperty("likes")
  private int likes;

  @JsonCreator
  public Seller(@JsonProperty("username") String username,
      @JsonProperty("password") String password, @JsonProperty("email") String email,
      @JsonProperty("phone_number") String phoneNumber, @JsonProperty("address") String address,
      @JsonProperty("company_name") String companyName) {
    super(username, password, email, phoneNumber, address);
    this.companyName = companyName;
    this.likes = 0;}

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public int getLikes() {
    return likes;

  }

  public void setLikes(int likes) {
    this.likes = likes;
  }



}