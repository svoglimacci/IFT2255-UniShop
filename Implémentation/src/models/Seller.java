package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Seller extends User {
    private String businessName;

    @JsonCreator
    public Seller(@JsonProperty("username") String username,
                  @JsonProperty("password") String password,
                  @JsonProperty("email") String email,
                  @JsonProperty("address") String address,
                  @JsonProperty("businessName") String businessName) {
        super(username, password, email, address);
        this.businessName = businessName;
    }


}