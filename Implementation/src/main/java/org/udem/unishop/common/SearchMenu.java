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

public class SearchMenu {

    private final SubMenu searchMenu;
    private final UserController userController;
    private final ProductController productController;
    private final User currentUser;





    public SearchMenu(UserController userController, ProductController productController, User currentUser) {
        this.userController = userController;
        this.productController = productController;
        this.currentUser = currentUser;
        this.searchMenu = createSearchMenu();

    }

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


    public SubMenu getSearchMenu() {
        return searchMenu;
    }


}