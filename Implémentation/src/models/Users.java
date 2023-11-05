package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Users {
    @JsonProperty("buyers")
    public final List<Buyer> buyers;
    @JsonProperty("sellers")
    public final List<Seller> sellers;

    @JsonCreator
    public Users(@JsonProperty("buyers") List<Buyer> buyers,
                 @JsonProperty("sellers") List<Seller> sellers) {
        this.buyers = buyers;
        this.sellers = sellers;
    }

    public List<Buyer> getBuyers() {
        return this.buyers;
    }

    public List<Seller> getSellers() {
        return this.sellers;
    }
}