package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Buyer extends User {
    private final String firstName;
    private final String lastName;

    @JsonCreator
    public Buyer(@JsonProperty("username") String username,
                 @JsonProperty("password") String password,
                 @JsonProperty("email") String email,
                 @JsonProperty("address") String address,
                 @JsonProperty("firstName") String firstName,
                 @JsonProperty("lastName") String lastName) {
        super(username, password, email, address);
        this.firstName = firstName;
        this.lastName = lastName;
    }

}