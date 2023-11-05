package controllers;

import services.AuthService;

import java.io.IOException;

public class AuthController {

    private final AuthService authService;

    public AuthController() throws IOException {
        this.authService = new AuthService();
    }

    public boolean login(String username, String password, boolean isSeller) throws IOException {
        return authService.login(username, password, isSeller);
    }

    public boolean register(String firstName, String lastName, String email, String username, String password, String address, String phoneNumber) {
        return authService.register(firstName, lastName, email, username, password, address, phoneNumber);
    }

    public boolean register(String businessName, String email, String username, String password, String address, String phoneNumber) {
        return authService.register(businessName, email, username, password, address, phoneNumber);
    }

    public boolean validateUsername(String username, boolean isSeller) {

        if (!username.matches("^[a-zA-Z]*[0-9]*$")) {
            return false;
        }

        return !this.authService.userExists(username, isSeller);
    }

    public boolean validatePassword(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public boolean validateAddress(String address) {
        return !(address.matches(""));
    }

    public boolean validateName(String businessName) {
        return !(businessName.matches(""));
    }

    public boolean validateName(String firstName, String lastName) {
        return !(firstName.matches("") && lastName.matches(""));
    }

    public boolean validateEmail(String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^[0-9]{10}$");
    }
}
