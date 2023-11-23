package org.udem.unishop.common;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.User;
import org.udem.unishop.product.ProductCatalog;
import org.udem.unishop.user.UserCatalog;
import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.Menu;
import org.udem.unishop.utilities.MenuItem;
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

        return searchMenu;
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
  return accountType == AccountType.BUYER ? "Catalogue d'acheteurs" : "Catalogue de vendeurs";
            }

            @Override
            public void execute() {
                UserCatalog userCatalog = new UserCatalog(userController, productController, currentUser, accountType);
                userCatalog.run();
            }
        };
    }


    public SubMenu getSearchMenu() {
        return searchMenu;
    }


}