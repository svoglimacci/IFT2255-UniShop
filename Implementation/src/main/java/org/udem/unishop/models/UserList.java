package org.udem.unishop.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class UserList<T extends User> extends ArrayList<T> {

  @JsonCreator
  public UserList(@JsonProperty("users") List<T> users) {
    super(users);
  }

  public UserList() {
    super();
  }
}