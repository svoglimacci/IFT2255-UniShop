package org.udem.unishop.repositories;

import java.util.UUID;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;
import org.udem.unishop.models.UserList;
import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.JSONHandler;

public class UserRepository {

  private final JSONHandler jsonHandler;
  private final String jsonFilePath;
  private UserList<User> userList = new UserList<>();


  public UserRepository(JSONHandler jsonHandler, String jsonFilePath) {
    this.jsonHandler = jsonHandler;
    this.jsonFilePath = jsonFilePath;
    this.userList = readDataFromJson();

    if (this.userList == null) {
      this.userList = new UserList<>();
    }
  }


  public User findById(UUID userId) {
    return userList.stream().filter(user -> user.getId().equals(userId)).findFirst().orElse(null);
  }

  public User findByUsername(String username, AccountType accountType) {
    return userList.stream()
        .filter(user -> user.getUsername().equals(username) && user.getAccountType() == accountType)
        .findFirst().orElse(null);
  }

public boolean save(User user) {
    boolean added = userList.add(user);
    if (added) {
        writeDataToJson();
    }
    return added;
}

public boolean delete(User user) {
    boolean removed = userList.removeIf(u -> u.equals(user));
    if (removed) {
        writeDataToJson();
    }
    return removed;
}

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


  public UserList getUsers() {
    return userList;
  }

  public UserList getBuyers() {
    UserList<User> buyers = new UserList<>();
    for (User user : userList) {
      if (user.getAccountType() == AccountType.BUYER) {
        buyers.add(user);
      }
    }
    return buyers;
  }

  public UserList getSellers() {
    UserList<User> sellers = new UserList<>();
    for (User user : userList) {
      if (user.getAccountType() == AccountType.SELLER) {
        sellers.add(user);
      }
    }
    return sellers;
  }


  public void saveUsers() {
    jsonHandler.writeJsonToFile(userList, jsonFilePath);
  }


  public Buyer getBuyerForTesting(String username) {
    return (Buyer) userList.stream().filter(user -> user.getUsername().equals(username)).findFirst()
        .orElse(null);
  }

  public Seller getSellerForTesting(String username) {
    return (Seller) userList.stream().filter(user -> user.getUsername().equals(username)).findFirst()
        .orElse(null);
  }
}