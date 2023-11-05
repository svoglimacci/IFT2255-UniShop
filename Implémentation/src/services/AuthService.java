package services;

import models.Buyer;
import models.Seller;
import models.Users;
import utilities.JSONHandler;

import java.io.IOException;
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

    public boolean login(String username, String password, boolean isSeller) {
        if (isSeller) {
            return sellerSet.stream()
                    .anyMatch(seller -> seller.getUsername().equals(username) && seller.getPassword().equals(password));
        } else {
            return buyerSet.stream()
                    .anyMatch(buyer -> buyer.getUsername().equals(username) && buyer.getPassword().equals(password));
        }
    }

    public boolean register(String firstName, String lastName, String email, String username, String password, String address) {
        try {
            Buyer newBuyer = new Buyer(username, password, email, address, firstName, lastName);
            buyerSet.add(newBuyer);
            users.getBuyers().add(newBuyer);
            JSONHandler.writeJsonToFile(users, "src/data/users.json");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean register(String businessName, String email, String username, String password, String address) {
        try {
            Seller newSeller = new Seller(username, password, email, address, businessName);
            sellerSet.add(newSeller);
            users.getSellers().add(newSeller);
            JSONHandler.writeJsonToFile(users, "src/data/users.json");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}