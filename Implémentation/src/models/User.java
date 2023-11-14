package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class User {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phoneNumber;
    private boolean isActive;
    private Date dateCreated;

    private final Set<UUID> likes;

    @JsonCreator
    public User(@JsonProperty("id") UUID id,
            @JsonProperty("username") String username,
                @JsonProperty("password") String password,
                @JsonProperty("email") String email,
                @JsonProperty("address") String address,
                @JsonProperty("phoneNumber") String phoneNumber,
                @JsonProperty("isActive") boolean isActive,
                @JsonProperty("dateCreated") Date dateCreated,
                @JsonProperty("likes") Set<UUID> likes) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.dateCreated = dateCreated;
        this.likes = likes;
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

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Set<UUID> getLikes() {
        return this.likes;
    }

    public void addLike(UUID id) {
        this.likes.add(id);
    }

    public void removeLike(UUID id) {
        this.likes.remove(id);
    }

    public UUID getId() {
        return id;

    }
}