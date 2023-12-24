package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of users on the platform.
 */
public class UserList<T extends User> extends ArrayList<T> {

  /**
   * Constructs a UserList with the specified list of users.
   *
   * @param users The list of users to be included in the user list.
   */
  @JsonCreator
  public UserList(@JsonProperty("users") List<T> users) {
    super(users);
  }

  /**
   * Constructs an empty UserList.
   */
  public UserList() {
    super();
  }
}