package controllers;

import models.Buyer;
import models.Seller;
import models.User;
import models.Users;
import services.UserService;

import java.io.IOException;
import java.util.*;

public class UserController {

    private final UserService userService;

    private final Users users;
    private final Set<Buyer> buyerSet;
    private final Set<Seller> sellerSet;


    public UserController() throws IOException {
        this.userService = new UserService();
        users = userService.getUsers();

        if (users.getBuyers() != null) {
            this.buyerSet = new HashSet<>(users.getBuyers());
        } else {
            this.buyerSet = new HashSet<>();
        }
        if (users.getSellers() != null) {
            this.sellerSet = new HashSet<>(users.getSellers());
        } else {
            this.sellerSet = new HashSet<>();
        }
    }

    public boolean login(String username, String password, boolean isSeller) {
        Set<? extends User> userSet = isSeller ? sellerSet : buyerSet;
        User user = userSet.stream()
                .filter(userToFind -> userToFind.getUsername().equals(username) && userToFind.getPassword().equals(password))
                .findFirst()
                .orElse(null);
        if (user == null) {
            return false;
        }

        if (user.getIsActive()) {
            if (new Date().getTime() - user.getDateCreated().getTime() >= 86400000 && isSeller) {
                Seller seller = (Seller) user;
                if (seller.getProducts() == null || seller.getProducts().isEmpty()) {
                    user.setIsActive(false);
                    userService.writeUsers(users);
                    return false;
                }
            }
            return true;
        }

        if (new Date().getTime() - user.getDateCreated().getTime() < 86400000) {
            user.setIsActive(true);
            userService.writeUsers(users);
            return true;
        } else {

            userSet.remove(user);
            userService.writeUsers(users);
            return false;
        }

    }

    public boolean register(String firstName, String lastName, String email, String username, String password, String address, String phoneNumber) {
        Buyer newBuyer = new Buyer(username, password, email, address, firstName, lastName, phoneNumber, false, new Date());
        if (users.getBuyers() == null) {
            users.setBuyers(new ArrayList<>());
        }
        buyerSet.add(newBuyer);
        users.getBuyers().add(newBuyer);
        userService.writeUsers(users);
        return true;
    }

    public boolean register(String businessName, String email, String username, String password, String address, String phoneNumber) {
        Seller newSeller = new Seller(username, password, email, address, businessName, phoneNumber, false, new Date(), new ArrayList<>());
        if (users.getSellers() == null) {
            users.setSellers(new ArrayList<>());
        }
        sellerSet.add(newSeller);
        users.getSellers().add(newSeller);
        userService.writeUsers(users);
        return true;
    }

    public boolean userExists(String username, boolean isSeller) {
        Set<? extends User> userSet = isSeller ? sellerSet : buyerSet;
        User user = userSet.stream()
                .filter(userToFind -> userToFind.getUsername() != null && userToFind.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        return user != null;
    }

    public boolean validateUsername(String username, boolean isSeller) {

        if (!username.matches("^[a-zA-Z]*[0-9]*$")) {
            return false;
        }

        return !this.userExists(username, isSeller);
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

    public void addProductsToSeller(String username, List<UUID> productId) {
        Seller seller = sellerSet.stream()
                .filter(sellerToFind -> sellerToFind.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (seller == null) {
            return;
        }
        if (seller.getProducts() == null) {
            seller.setProducts(new ArrayList<>());
        }
        seller.getProducts().addAll(productId);
        users.setSellers(new ArrayList<>(sellerSet));
        userService.writeUsers(users);
    }

    public boolean changeProperty(String attribute, String value, String username, boolean isSeller) {
        Set<? extends User> userSet = isSeller ? sellerSet : buyerSet;
        User user = userSet.stream()
                .filter(userToFind -> userToFind.getUsername().equals(username))
                .findFirst()
                .orElse(null);
        if (user == null) {
            return false;
        }
        switch (attribute) {
            case "username":
                user.setUsername(value);
                break;
            case "password":
                user.setPassword(value);
                break;
            case "email":
                user.setEmail(value);
                break;
            case "address":
                user.setAddress(value);
                break;
            case "phoneNumber":
                user.setPhoneNumber(value);
                break;
            case "businessName":
                ((Seller) user).setBusinessName(value);
                break;
            case "firstName":
                ((Buyer) user).setFirstName(value);
                break;
            case "lastName":
                ((Buyer) user).setLastName(value);
                break;
            default:
                return false;
        }
        if (isSeller) {
            sellerSet.add((Seller) user);
            users.setSellers(new ArrayList<>(sellerSet));
        } else {
            buyerSet.add((Buyer) user);
            users.setBuyers(new ArrayList<>(buyerSet));
        }
        userService.writeUsers(users);
        return true;
    }
}