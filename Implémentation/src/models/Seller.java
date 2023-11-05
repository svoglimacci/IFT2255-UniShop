package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Seller extends User {
    private String businessName;

    @JsonCreator
    public Seller(@JsonProperty("username") String username,
                  @JsonProperty("password") String password,
                  @JsonProperty("email") String email,
                  @JsonProperty("address") String address,
                  @JsonProperty("businessName") String businessName,
                  @JsonProperty("phoneNumber") String phoneNumber,
                  @JsonProperty("isActive") boolean isActive,
                  @JsonProperty("dateCreated") Date dateCreated) {
        super(username, password, email, address, phoneNumber, isActive, dateCreated);
        this.businessName = businessName;
    }

    // Getter methods
    public String getBusinessName() {
        return this.businessName;
    }

    // Setter methods
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }


}