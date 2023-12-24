package org.udem.unishop.common;

import java.util.List;
import java.util.Scanner;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.User;
import org.udem.unishop.product.ProductCatalog;
import org.udem.unishop.user.UserCatalog;
import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.Menu;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SearchType;
import org.udem.unishop.utilities.SubMenu;

/**
 * The SearchMenu class generates a menu for performing various searches within the application.
 * It provides options for searching products, users, and sellers based on different criteria.
 */
public class SearchMenu {

    private final SubMenu searchMenu;
    private final UserController userController;
    private final ProductController productController;
    private final User currentUser;

    /**
     * Constructs a SearchMenu instance with the necessary controllers and current user.
     *
     * @param userController     The controller for user-related functionalities.
     * @param productController  The controller for product-related functionalities.
     * @param currentUser        The current user accessing the search functionalities.
     */
    public SearchMenu(UserController userController, ProductController productController, User currentUser) {
        this.userController = userController;
        this.productController = productController;
        this.currentUser = currentUser;
        this.searchMenu = createSearchMenu();

    }

    /**
     * Creates the search menu with various search options based on the current user's role.
     *
     * @return The generated search menu with different search commands based on user roles.
     */
    private SubMenu createSearchMenu() {
        SubMenu searchMenu = new SubMenu("Rechercher");
        searchMenu.addMenuComponent(new MenuItem(createProductCatalog()));
        searchMenu.addMenuComponent(new MenuItem(createUserCatalog(AccountType.BUYER)));
        searchMenu.addMenuComponent(new MenuItem(createUserCatalog(AccountType.SELLER)));
        searchMenu.addMenuComponent(new MenuItem(createSearchBuyerByNameCommand()));
        searchMenu.addMenuComponent(new MenuItem(createSearchSellerByNameCommand()));
        searchMenu.addMenuComponent(new MenuItem(createSearchSellerByAddressCommand()));
        searchMenu.addMenuComponent(new MenuItem(createSearchSellerByProductTypeCommand()));
        searchMenu.addMenuComponent(new MenuItem(createSearchProductByNameCommand()));
        searchMenu.addMenuComponent(new MenuItem(createSearchProductByCategoryCommand()));
        searchMenu.addMenuComponent(new MenuItem(createSearchProductByBrandCommand()));
        searchMenu.addMenuComponent(new MenuItem(createSearchProductByModelCommand()));
        searchMenu.addMenuComponent(new MenuItem(createSearchProductByPriceCommand()));
        if (currentUser instanceof Buyer) {
          searchMenu.addMenuComponent(new MenuItem(createSearchBuyerByFollowedCommand()));
        }

       return searchMenu;
    }

  /**
   * Creates a command to search for a buyer among the followed users.
   * This command constructs a UserCatalog with specific search parameters and executes it.
   *
   * @return A Command instance for searching a buyer among the followed users.
   */
  private Command createSearchBuyerByFollowedCommand() {
    return new Command() {
      @Override
      public String getName() {
        return "Rechercher un acheteur parmi les utilisateurs suivis";
      }

      @Override
      public void execute() {
        UserCatalog userCatalog = new UserCatalog(userController, productController, currentUser, AccountType.BUYER, SearchType.FOLLOWED_USERS, null);
        userCatalog.run();
      }
    };
  }

  /**
   * Creates a command to display the product catalog.
   * This command constructs a ProductCatalog instance and executes it to display the catalog of products.
   *
   * @return A Command instance for displaying the product catalog.
   */
  private Command createProductCatalog() {
        return new Command() {
            @Override
            public String getName() {
                return "Catalogue de produits";
            }

            @Override
            public void execute() {
                ProductCatalog productCatalog = new ProductCatalog(userController, productController, currentUser);
                productCatalog.run();
            }
        };
    }

    /**
     * Creates a command to display the user catalog based on the given account type.
     * This command constructs a UserCatalog instance and executes it to display the catalog of users based on the specified account type.
     *
     * @param accountType The type of account for which the catalog is generated (BUYER or SELLER).
     * @return A Command instance for displaying the user catalog based on the account type.
     */
    private Command createUserCatalog(AccountType accountType) {
        return new Command() {
            @Override
            public String getName() {
  return accountType == AccountType.BUYER ? "Catalogue d'acheteurs" : "Catalogue de revendeurs";
            }

            @Override
            public void execute() {
                UserCatalog userCatalog = new UserCatalog(userController, productController, currentUser, accountType);
                userCatalog.run();
            }
        };
    }

        /**
        * Creates a command to search for a buyer by username.
        * This command utilizes a SearchPrompt to gather user input for the username, constructs a UserCatalog instance based on the provided username, and executes it to search for the buyer.
        *
        * @return A Command instance for searching a buyer by username.
        */
        private Command createSearchBuyerByNameCommand() {
        SearchPrompt searchPrompt = new SearchPrompt();
        return new Command() {
            @Override
            public String getName() {
                return "Rechercher un acheteur par pseudo";
            }

            @Override
            public void execute() {
              List<String> input = searchPrompt.createSearchPrompt(SearchType.USERNAME).getValuesFromUser();
              UserCatalog userCatalog = new UserCatalog(userController, productController, currentUser, AccountType.BUYER, SearchType.USERNAME, input.get(0));
                userCatalog.run();



            }
        };
    }

    /**
     * Creates a command to search for a seller by business address.
     * This command utilizes a SearchPrompt to gather user input for the business address, constructs a UserCatalog instance based on the provided address, and executes it to search for the seller.
     *
     * @return A Command instance for searching a seller by business address.
     */
    private Command createSearchSellerByAddressCommand() {
      SearchPrompt searchPrompt = new SearchPrompt();
      return new Command() {
        @Override
        public String getName() {
          return "Rechercher un revendeur par adresse";
        }

        @Override
        public void execute() {
          List<String> input = searchPrompt.createSearchPrompt(SearchType.BUSINESS_ADDRESS).getValuesFromUser();
          UserCatalog userCatalog = new UserCatalog(userController, productController, currentUser,
              AccountType.SELLER, SearchType.BUSINESS_ADDRESS, input.get(0));
          userCatalog.run();
        }
      };
    }

    /**
     * Creates a command to search for a seller by product type.
     * This command uses a SearchPrompt to collect user input for the product type, constructs a UserCatalog instance based on the provided product type, and executes it to search for the seller.
     *
     * @return A Command instance for searching a seller by product type.
     */
    private Command createSearchSellerByProductTypeCommand() {
      SearchPrompt searchPrompt = new SearchPrompt();
      return new Command() {
        @Override
        public String getName() {
          return "Rechercher un revendeur par type de produit";
        }

        @Override
        public void execute() {
          List<String> input = searchPrompt.createSearchPrompt(SearchType.PRODUCT_TYPE).getValuesFromUser();
          UserCatalog userCatalog = new UserCatalog(userController, productController, currentUser,
              AccountType.SELLER, SearchType.PRODUCT_TYPE, input.get(0));
          userCatalog.run();
        }
      };
    }

        private Command createSearchSellerByNameCommand() {
        SearchPrompt searchPrompt = new SearchPrompt();
        return new Command() {
            @Override
            public String getName() {
                return "Rechercher un revendeur par nom";
            }

            @Override
            public void execute() {
              List<String> input = searchPrompt.createSearchPrompt(SearchType.BUSINESS_NAME).getValuesFromUser();
              UserCatalog userCatalog = new UserCatalog(userController, productController, currentUser, AccountType.SELLER, SearchType.BUSINESS_NAME, input.get(0));
                userCatalog.run();



            }
        };
    }

  /**
   * Creates a command to search for a product by price range.
   * This command utilizes a SearchPrompt to collect user input for the price range, initializes a ProductCatalog instance based on the provided range, and executes it to search for the product.
   *
   * @return A Command instance for searching a product by price range.
   */
  private Command createSearchProductByPriceCommand() {
      SearchPrompt searchPrompt = new SearchPrompt();

    return new Command() {
      @Override
      public String getName() {
        return "Rechercher un produit par tranche de prix";
      }

      @Override
      public void execute() {
        List<String> inputs = searchPrompt.createSearchPrompt(SearchType.PRODUCT_PRICE).getValuesFromUser();
        ProductCatalog productCatalog = new ProductCatalog(userController, productController, currentUser, SearchType.PRODUCT_PRICE, inputs);
        productCatalog.run();
      }
    };
    }

  /**
   * Creates a command to search for a product by model.
   * This command utilizes a SearchPrompt to gather user input for the product model, initializes a ProductCatalog instance based on the provided model, and executes it to search for the product.
   *
   * @return A Command instance for searching a product by its model.
   */
  private Command createSearchProductByModelCommand() {
      SearchPrompt searchPrompt = new SearchPrompt();

    return new Command() {
      @Override
      public String getName() {
        return "Rechercher un produit par modèle";
      }

      @Override
      public void execute() {
        List<String> input = searchPrompt.createSearchPrompt(SearchType.PRODUCT_MODEL).getValuesFromUser();
        ProductCatalog productCatalog = new ProductCatalog(userController, productController, currentUser, SearchType.PRODUCT_MODEL, input);
        productCatalog.run();
      }
    };
    }

  /**
   * Creates a command to search for a product by model.
   * This command utilizes a SearchPrompt to gather user input for the product model, initializes a ProductCatalog instance based on the provided model, and executes it to search for the product.
   *
   * @return A Command instance for searching a product by its model.
   */
  private Command createSearchProductByBrandCommand() {
      SearchPrompt searchPrompt = new SearchPrompt();

    return new Command() {
      @Override
      public String getName() {
        return "Rechercher un produit par marque";
      }

      @Override
      public void execute() {
        List<String> input = searchPrompt.createSearchPrompt(SearchType.PRODUCT_BRAND).getValuesFromUser();
        ProductCatalog productCatalog = new ProductCatalog(userController, productController, currentUser, SearchType.PRODUCT_BRAND, input);
        productCatalog.run();
      }
    };
  }

  /**
   * Creates a command to search for a product by category.
   * This method initializes a SearchPrompt to gather user input for the product category, utilizes a ProductCatalog instance with the provided category information, and executes it to search for the product.
   *
   * @return A Command instance for searching a product by its category.
   */
  private Command createSearchProductByCategoryCommand() {
      SearchPrompt searchPrompt = new SearchPrompt();

    return new Command() {
      @Override
      public String getName() {
        return "Rechercher un produit par catégorie";
      }

      @Override
      public void execute() {
        List<String> input = searchPrompt.createSearchPrompt(SearchType.PRODUCT_CATEGORY).getValuesFromUser();
        ProductCatalog productCatalog = new ProductCatalog(userController, productController, currentUser, SearchType.PRODUCT_CATEGORY, input);
        productCatalog.run();
      }
    };
  }

  /**
   * Creates a command to search for a product by name.
   * This method initializes a SearchPrompt to gather user input for the product name, utilizes a ProductCatalog instance with the provided name information, and executes it to search for the product.
   *
   * @return A Command instance for searching a product by its name.
   */
  private Command createSearchProductByNameCommand() {
      SearchPrompt searchPrompt = new SearchPrompt();

    return new Command() {
      @Override
      public String getName() {
        return "Rechercher un produit par nom";
      }

      @Override
      public void execute() {
        List<String> input = searchPrompt.createSearchPrompt(SearchType.PRODUCT_NAME).getValuesFromUser();
        ProductCatalog productCatalog = new ProductCatalog(userController, productController, currentUser, SearchType.PRODUCT_NAME, input);
        productCatalog.run();
      }
    };
  }

    /**
     * Retrieves the generated search menu.
     *
     * @return The SubMenu instance representing the generated search menu with various search options.
     */
    public SubMenu getSearchMenu() {
        return searchMenu;
    }


}