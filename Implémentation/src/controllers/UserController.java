package controllers;

import models.*;
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

    public Object login(String username, String password, boolean isSeller) {
        Set<? extends User> userSet = isSeller ? sellerSet : buyerSet;
        User user = userSet.stream()
                .filter(userToFind -> userToFind.getUsername().equals(username) && userToFind.getPassword().equals(password))
                .findFirst()
                .orElse(null);
        if (user == null) {
            return null;
        }

        if (user.getIsActive()) {
            if (new Date().getTime() - user.getDateCreated().getTime() >= 86400000 && isSeller) {
                Seller seller = (Seller) user;
                if (seller.getProducts() == null || seller.getProducts().isEmpty()) {
                    user.setIsActive(false);
                    userService.writeUsers(users);
                    return null;
                }
            }
            return user;
        }

        if (new Date().getTime() - user.getDateCreated().getTime() < 86400000) {
            user.setIsActive(true);
            userService.writeUsers(users);
            return user;
        } else {

            userSet.remove(user);
            userService.writeUsers(users);
            return null;
        }

    }

    public boolean register(String firstName, String lastName, String email, String username, String password, String address, String phoneNumber) {
        Buyer newBuyer = new Buyer(UUID.randomUUID(), username, password, email, address, firstName, lastName, phoneNumber, false, new Date(), new HashSet<>(),  new ArrayList<>(), new ShoppingCart( new HashSet<>()));
        if (users.getBuyers() == null) {
            users.setBuyers(new ArrayList<>());
        }
        buyerSet.add(newBuyer);
        users.getBuyers().add(newBuyer);
        userService.writeUsers(users);
        return true;
    }

    public boolean register(String businessName, String email, String username, String password, String address, String phoneNumber) {
        Seller newSeller = new Seller(UUID.randomUUID(),username, password, email, address, businessName, phoneNumber, false, new Date(), new ArrayList<>(), new HashSet<>());
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

    public void addProductsToSeller(String username, Set<UUID> productId) {
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

    public boolean addLike(Buyer user, Product product) {
            buyerSet.remove(user);
            user.addLike(product.getId());
            buyerSet.add(user);
            users.setBuyers(new ArrayList<>(buyerSet));
            userService.writeUsers(users);
            return true;
        }

        public boolean removeLike(Buyer user, Product product) {
            buyerSet.remove(user);
             user.removeLike(product.getId());
             buyerSet.add(user);
            users.setBuyers(new ArrayList<>(buyerSet));
            userService.writeUsers(users);
            return true;
        }

    public void addProductToCart(Buyer user, Product product, int quantity) {
        buyerSet.remove(user);
        CartItem cartItem = new CartItem(product.getId(), product.getName(), quantity, product.getPrice());
        user.getCart().addProduct(cartItem, quantity);
    }

    public void addProductToPurchases(Buyer user, UUID id) {
        user.addPurchase(id);
    }

    public boolean addReview(Buyer user, Product product, String review, String rating) {
        float ratingToFloat = Float.parseFloat(rating);
        if (product.getReviews() == null) {
            product.setReviews(new ArrayList<>());
        }
        Review newReview = new Review(user.getUsername(), review, ratingToFloat);
        product.getReviews().add(newReview);
        return true;
    }

    public List<Seller> searchSellers(String keyword) {
        List<Seller> sellers = new ArrayList<>();
        for (Seller seller : sellerSet) {
            if (seller.getBusinessName().toLowerCase().contains(keyword.toLowerCase())) {
                sellers.add(seller);
            }
        }
        return sellers;
    }

    //get seller sold categories
    public List<String> getCategories(Seller seller) throws IOException {
        List<String> categories = new ArrayList<>();
        for (UUID id : seller.getProducts()) {
            Product product = new ProductController().getProductById(id);
            if (!categories.contains(product.getCategory())) {
                categories.add(product.getCategory());
            }
        }
        return categories;
    }

    public List<Seller> sortSellers(List<Seller> searchResults, String keyword) {
        if (keyword.equals("name")) {
            searchResults.sort(Comparator.comparing(Seller::getBusinessName));
        } else if (keyword.equals("address")) {
            searchResults.sort(Comparator.comparing(Seller::getAddress));
        }
        return searchResults;
    }

    public List<Seller> filterSellers(List<Seller> searchResults, String category) throws IOException {
        List<Seller> filteredSellers = new ArrayList<>();
        for (Seller seller : searchResults) {
            List<String> categories = getCategories(seller);
            if (categories.contains(category)) {
                filteredSellers.add(seller);
            }
        }
        return filteredSellers;
    }
}