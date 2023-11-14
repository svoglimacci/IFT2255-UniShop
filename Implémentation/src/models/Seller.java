package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Seller extends User {
    private String businessName;
    private List<UUID> products;

    @JsonCreator
    public Seller(@JsonProperty("id") UUID id,
            @JsonProperty("username") String username,
                  @JsonProperty("password") String password,
                  @JsonProperty("email") String email,
                  @JsonProperty("address") String address,
                  @JsonProperty("businessName") String businessName,
                  @JsonProperty("phoneNumber") String phoneNumber,
                  @JsonProperty("isActive") boolean isActive,
                  @JsonProperty("dateCreated") Date dateCreated,
                  @JsonProperty("products") List<UUID> products,
                  @JsonProperty("likes") Set<UUID> likes) {
        super(id, username, password, email, address, phoneNumber, isActive, dateCreated, likes);
        this.businessName = businessName;
        this.products = products;
    }

    // Getter methods
    public String getBusinessName() {
        return this.businessName;
    }

    // Setter methods
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<UUID> getProducts() {
        return this.products;
    }

    public void setProducts(List<UUID> products) {
        this.products = products;
    }


}