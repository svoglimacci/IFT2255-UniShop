package services;

import models.User;
import utilities.JSONHandler;

import java.util.Set;

public class AuthService {


    private final Set<User> userSet;

    public AuthService() {
        userSet = new JSONHandler<User>("src/data/users.json").read(User.class);

    }

    public boolean login(String username, String password) {
        for (User user : userSet) {
            return user.getUsername().equals(username) && user.getPassword().equals(password);
        }


        return false;
    }

    public boolean register(String firstName, String lastName, String email, String username, String password) {
        try {
            User newUser = new User(username, password, firstName, lastName, email);
            userSet.add(newUser);
            new JSONHandler<User>("src/data/users.json").write(userSet);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
