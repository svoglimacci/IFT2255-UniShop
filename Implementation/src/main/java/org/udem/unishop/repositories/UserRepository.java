package org.udem.unishop.repositories;

import java.util.UUID;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;
import org.udem.unishop.models.UserList;
import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.JSONHandler;

/**
 * The UserRepository class is responsible for managing and persisting user data.
 */
public class UserRepository {

  private final JSONHandler jsonHandler;
  private final String jsonFilePath;
  private UserList<User> userList = new UserList<>();


    /**
     * Constructor for UserRepository.
     *
     * @param jsonHandler The JSONHandler instance for reading and writing JSON data.
     * @param jsonFilePath The file path to the JSON file storing user data.
     */
    public UserRepository(JSONHandler jsonHandler, String jsonFilePath) {
    this.jsonHandler = jsonHandler;
    this.jsonFilePath = jsonFilePath;
    this.userList = readDataFromJson();

    if (this.userList == null) {
      this.userList = new UserList<>();
    }
  }

    /**
     * Finds a user by their unique identifier (ID).
     *
     * @param userId The ID of the user to find.
     * @return The found user or null if not found.
     */
    public User findById(UUID userId) {
    return userList.stream().filter(user -> user.getId().equals(userId)).findFirst().orElse(null);
  }

    /**
     * Finds a user by their username and account type.
     *
     * @param username The username of the user to find.
     * @param accountType The account type of the user to find.
     * @return The found user or null if not found.
     */
    public User findByUsername(String username, AccountType accountType) {
    return userList.stream()
        .filter(user -> user.getUsername().equals(username) && user.getAccountType() == accountType)
        .findFirst().orElse(null);
  }

    /**
     * Saves a user to the repository and writes the updated data to the JSON file.
     *
     * @param user The user to save.
     * @return true if the user is successfully saved, false otherwise.
     */
    public boolean save(User user) {
        boolean added = userList.add(user);
        if (added) {
            writeDataToJson();
        }
        return added;
    }

    /**
     * Deletes a user from the repository and writes the updated data to the JSON file.
     *
     * @param user The user to delete.
     * @return true if the user is successfully deleted, false otherwise.
     */
    public boolean delete(User user) {
        boolean removed = userList.removeIf(u -> u.equals(user));
        if (removed) {
            writeDataToJson();
        }
        return removed;
    }

    /**
     * Updates a user in the repository and writes the updated data to the JSON file.
     *
     * @param user The user to update.
     * @return true if the user is successfully updated, false otherwise.
     */
    public boolean update(User user) {
    boolean userExists = userList.contains(user);
        if (userExists) {
            userList.removeIf(u -> u.equals(user));
            userList.add(user);
            writeDataToJson();
            return true;
        }
        return false;
    }

    private UserList<User> readDataFromJson() {
    return jsonHandler.readJsonFromFile(jsonFilePath, UserList.class);
  }

    private void writeDataToJson() {
    jsonHandler.writeJsonToFile(userList, jsonFilePath);
  }


    /**
     * Gets a list of all users in the repository.
     *
     * @return The list of all users.
     */
    public UserList getUsers() {
    return userList;
  }

    /**
     * Gets a list of all buyers in the repository.
     *
     * @return The list of all buyers.
     */
    public UserList getBuyers() {
    UserList<User> buyers = new UserList<>();
    for (User user : userList) {
      if (user.getAccountType() == AccountType.BUYER) {
        buyers.add(user);
      }
    }
    return buyers;
  }

    /**
     * Gets a list of all sellers in the repository.
     *
     * @return The list of all sellers.
     */
    public UserList getSellers() {
    UserList<User> sellers = new UserList<>();
    for (User user : userList) {
      if (user.getAccountType() == AccountType.SELLER) {
        sellers.add(user);
      }
    }
    return sellers;
  }


    /**
     * Saves the current user data to the JSON file.
     */
    public void saveUsers() {
    jsonHandler.writeJsonToFile(userList, jsonFilePath);
  }


    /**
     * Gets a buyer for testing purposes based on the provided username.
     *
     * @param username The username of the buyer to retrieve for testing.
     * @return The buyer with the specified username or null if not found.
     */
    public Buyer getBuyerForTesting(String username) {
    return (Buyer) userList.stream().filter(user -> user.getUsername().equals(username)).findFirst()
        .orElse(null);
  }

    /**
     * Gets a seller for testing purposes based on the provided username.
     *
     * @param username The username of the seller to retrieve for testing.
     * @return The seller with the specified username or null if not found.
     */
  public Seller getSellerForTesting(String username) {
    return (Seller) userList.stream().filter(user -> user.getUsername().equals(username)).findFirst()
        .orElse(null);
  }
}