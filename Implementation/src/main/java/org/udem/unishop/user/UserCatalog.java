package org.udem.unishop.user;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.ProductList;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;
import org.udem.unishop.models.UserList;
import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SearchType;
import org.udem.unishop.utilities.SubMenu;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The UserCatalog class represents a catalog of users with various filtering options.
 * It provides functionality to display a list of users and their associated information through a menu interface.
 */
public class UserCatalog {

    private final SubMenu userCatalog;
    private final User currentUser;
    private final AccountType accountType;
    private final Set<UserPage> userPages = new HashSet<>();

    /**
     * Constructs a UserCatalog with the specified controllers, current user, and account type.
     *
     * @param userController The UserController instance.
     * @param productController The ProductController instance.
     * @param currentUser The currently logged-in user.
     * @param accountType The account type of the users to be displayed.
     */
    public UserCatalog(UserController userController, ProductController productController,
                       User currentUser, AccountType accountType) {
        this.currentUser = currentUser;
        this.accountType = accountType;
        this.userCatalog = new SubMenu("Catalogue d'utilisateurs");
        createUserList(productController, userController);
    }

    /**
     * Constructor for UserCatalog class with the specified controllers, current user, account type, search type
     * and search value.
     *
     * @param userController The UserController instance.
     * @param productController The ProductController instance.
     * @param currentUser The currently logged-in user.
     * @param accountType The account type of the users to be displayed.
     * @param searchType The type of search to perform in the user catalog.
     * @param searchValue The value used for filtering users based on the search type.
     */
    public UserCatalog(UserController userController, ProductController productController, User currentUser, AccountType
            accountType, SearchType searchType, String searchValue) {
        this.currentUser = currentUser;
        this.accountType = accountType;
        this.userCatalog = new SubMenu("Catalogue d'utilisateurs");
        createFilteredUserList(productController, userController, searchType, searchValue);
    }

    /**
     * Creates a filtered list of users based on the specified search criteria and adds user pages to the catalog.
     *
     * @param productController The ProductController instance.
     * @param userController The UserController instance.
     * @param searchType The type of search to perform in the user catalog.
     * @param searchValue The value used for filtering users based on the search type.
     */
    private void createFilteredUserList(ProductController productController, UserController userController,
                                        SearchType searchType, String searchValue) {
        if(searchValue != null) {
            searchValue = searchValue.toLowerCase();
        }

        UserList userList = accountType == AccountType.BUYER ? userController.getBuyers() : userController.getSellers();

        for (Object user : userList) {
          User u = (User) user;
            switch (searchType) {
              case FOLLOWED_USERS:
                Buyer buyer = (Buyer) currentUser;
                for (UUID followedUserId : buyer.getFollowed()) {
                  User followedUser = userController.getUserById(followedUserId);
                  if (followedUser != null && followedUser.getId() == u.getId()) {
                    addUserPage(u, productController, userController);
                  }
                }
                break;

                case USERNAME:
                    if (u.getUsername().toLowerCase().contains(searchValue)) {
                        addUserPage(u, productController, userController);
                    }
                    break;

                case BUSINESS_NAME:
                    if (user instanceof Seller && ((Seller) user).getCompanyName().contains(searchValue)) {
                        addUserPage(u, productController, userController);
                    }
                    break;

                case BUSINESS_ADDRESS:
                    if (u.getAddress().toLowerCase().contains(searchValue)) {
                        addUserPage(u, productController, userController);
                    }
                    break;

                case PRODUCT_TYPE:
                    ProductList productList = productController.getProducts();

                    for (Object product : productList) {
                        Product p = (Product) product;
                        String productType = String.valueOf(p.getProductType()).toLowerCase();
                        if (productType.contains(searchValue)) {
                            UUID sellerId = p.getSellerId();
                            User seller = userController.getUserById(sellerId);
                            addUserPage(seller, productController, userController);
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Executes the user catalog, displaying the menu and allowing user interaction.
     */
    public void run() {
        userCatalog.execute();
    }

    /**
     * Creates a list of users based on the account type and adds user pages to the catalog.
     *
     * @param productController The ProductController instance.
     * @param userController    The UserController instance.
     */
    private void createUserList(ProductController productController, UserController userController) {
        UserList userList = accountType == AccountType.BUYER ? userController.getBuyers() : userController.getSellers();

        for (Object user : userList) {
            addUserPage((User) user, productController, userController);
        }
    }

    /**
     * Adds a user page to the catalog if it does not already exist and the user is not the current user.
     *
     * @param user The User instance for which to create a user page.
     * @param productController The ProductController instance.
     * @param userController The UserController instance.
     */
    private void addUserPage(User user, ProductController productController, UserController userController) {
        UserPage userPage = new UserPage(user, productController, userController, currentUser);

        if (!userPages.contains(userPage) && user.getId() != currentUser.getId()) {
            userPages.add(userPage);

            Command showUserCommand = new Command() {
                @Override
                public String getName() {
                  return (user instanceof Seller ? ((Seller) user).getCompanyName()
                      : user.getUsername());
                }

                @Override
                public void execute() {
                    userPage.run();
                }
            };

            userCatalog.addMenuComponent(new MenuItem(showUserCommand));
        }
    }
}