package services;

import models.Buyer;
import models.Seller;
import models.User;
import models.Users;
import utilities.JSONHandler;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AuthService {

    private final Set<Buyer> buyerSet;
    private final Set<Seller> sellerSet;
    private final Users users;
    private final JSONHandler JSONHandler;

    public AuthService() throws IOException {
        JSONHandler = new JSONHandler();
        users = JSONHandler.readJsonFromFile("src/data/users.json", Users.class);
        this.buyerSet = new HashSet<>(users.getBuyers());
        this.sellerSet = new HashSet<>(users.getSellers());
    }

    public boolean login(String username, String password, boolean isSeller) throws IOException {
        System.out.println("username: " + username + " password: " + password + " isSeller: " + isSeller);
        Set<? extends User> userSet = isSeller ? sellerSet : buyerSet;
        User user = userSet.stream()
                .filter(userToFind -> userToFind.getUsername().equals(username) && userToFind.getPassword().equals(password))
                .findFirst()
                .orElse(null);
            if (user == null) {
                return false;
            }

            if( user.getIsActive()) {
                return true;
            }

            if (new Date().getTime() - user.getDateCreated().getTime() < 86400000) {
                System.out.println ("Account is now active");
                user.setIsActive(true);
                JSONHandler.writeJsonToFile(users, "src/data/users.json");
                return true;
            } else {
                userSet.remove(user);
                JSONHandler.writeJsonToFile(users, "src/data/users.json");
                return false;
            }
    }

    public boolean register(String firstName, String lastName, String email, String username, String password, String address, String phoneNumber) {
        try {
            Buyer newBuyer = new Buyer(username, password, email, address, firstName, lastName, phoneNumber, false, new Date());
            buyerSet.add(newBuyer);
            users.getBuyers().add(newBuyer);
            JSONHandler.writeJsonToFile(users, "src/data/users.json");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean register(String businessName, String email, String username, String password, String address, String phoneNumber) {
        try {
            Seller newSeller = new Seller(username, password, email, address, businessName, phoneNumber, false, new Date());
            sellerSet.add(newSeller);
            users.getSellers().add(newSeller);
            JSONHandler.writeJsonToFile(users, "src/data/users.json");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

public boolean userExists(String username, boolean isSeller) {
    Set<? extends User> userSet = isSeller ? sellerSet : buyerSet;
    User user = userSet.stream()
            .filter(userToFind -> userToFind.getUsername() != null && userToFind.getUsername().equals(username))
            .findFirst()
            .orElse(null);
    return user != null;
}

}

