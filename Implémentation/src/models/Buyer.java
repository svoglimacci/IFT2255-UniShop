package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Buyer extends User {
    private String firstName;
    private String lastName;


    @JsonCreator
    public Buyer(@JsonProperty("username") String username,
                 @JsonProperty("password") String password,
                 @JsonProperty("email") String email,
                 @JsonProperty("address") String address,
                 @JsonProperty("firstName") String firstName,
                 @JsonProperty("lastName") String lastName,
                 @JsonProperty("phoneNumber") String phoneNumber,
                 @JsonProperty("isActive") boolean isActive,
                 @JsonProperty("dateCreated") Date dateCreated,
                 @JsonProperty("likes") Set<UUID> likes) {
        super(username, password, email, address, phoneNumber, isActive, dateCreated, likes);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getter methods
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Setter methods

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}