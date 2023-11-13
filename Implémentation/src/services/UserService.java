package services;

import models.Users;
import utilities.JSONHandler;

import java.io.IOException;

public class UserService {

    private final JSONHandler JSONHandler;

    public UserService() {
        JSONHandler = new JSONHandler();
    }

    public Users getUsers() throws IOException {
        return JSONHandler.readJsonFromFile("src/data/users.json", Users.class);
    }


    public void writeUsers(Users users) {
        try {
            JSONHandler.writeJsonToFile(users, "src/data/users.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}