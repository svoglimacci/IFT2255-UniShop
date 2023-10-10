package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private boolean isActive;
    private Date dateCreated;

    @JsonCreator
    public User(@JsonProperty("username") String username,
                @JsonProperty("password") String password,
                @JsonProperty("email") String email,
                @JsonProperty("address") String address,
                @JsonProperty("phoneNumber") String phoneNumber,
                @JsonProperty("isActive") boolean isActive,
                @JsonProperty("dateCreated") Date dateCreated){
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
    }

    // Getter methods
    public String getUsername() {
        return this.username;
    }

    // Setter methods
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

}