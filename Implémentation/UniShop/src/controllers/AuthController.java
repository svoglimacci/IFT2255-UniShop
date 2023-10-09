package controllers;

import services.AuthService;

public class AuthController {

    private final AuthService authService;

    public AuthController() {
        this.authService = new AuthService();
    }

    public boolean login(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {

            return false;
        }
        return authService.login(username, password);
    }

    public boolean register(String firstName, String lastName, String email, String username, String password) {
        return authService.register(firstName, lastName, email, username, password);
    }
}
