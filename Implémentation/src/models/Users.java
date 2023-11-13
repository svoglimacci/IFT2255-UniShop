package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Users {

    public List<Buyer> buyers;
    public List<Seller> sellers;

    @JsonCreator
    public Users(@JsonProperty("buyers") List<Buyer> buyers,
                 @JsonProperty("sellers") List<Seller> sellers) {
        this.buyers = buyers;
        this.sellers = sellers;
    }

    public List<Buyer> getBuyers() {
        return this.buyers;
    }

    public void setBuyers(List<Buyer> buyers) {
        this.buyers = buyers;
    }

    public List<Seller> getSellers() {
        return this.sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
    }
}