package controllers;

import services.AuthService;

import java.io.IOException;

public class AuthController {

    private final AuthService authService;

    public AuthController() throws IOException {
        this.authService = new AuthService();
    }

    public boolean login(String username, String password, boolean isSeller) {
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }
        return authService.login(username, password, isSeller);
    }

    public boolean register(String firstName, String lastName, String email, String username, String password, String address) {
        return authService.register(firstName, lastName, email, username, password, address);
    }

    public boolean register(String businessName, String email, String username, String password, String address) {
        return authService.register(businessName, email, username, password, address);
    }
}
