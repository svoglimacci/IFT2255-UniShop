package org.udem.unishop.seller;

import java.util.List;
import org.udem.unishop.common.SearchMenu;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Seller;
import org.udem.unishop.product.ProductPrompt;
import org.udem.unishop.utilities.Menu;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.ProductType;
import org.udem.unishop.utilities.SubMenu;
import org.udem.unishop.utilities.Command;

public class SellerMenu {

    private final Menu sellerMenu;
    private final UserController userController;
    private final ProductController productController;
    private final Seller currentUser;


    public SellerMenu(UserController userController, ProductController productController, Seller currentUser) {
        this.userController = userController;
        this.productController = productController;
        this.currentUser = currentUser;

        SearchMenu searchMenu = new SearchMenu(userController, productController, currentUser);
        this.sellerMenu = new Menu();
        this.sellerMenu.addMenuComponent(searchMenu.getSearchMenu());
         this.sellerMenu.addMenuComponent(createAddProductMenu());
    }

    public void run() {
        sellerMenu.execute();
    }

    private SubMenu createAddProductMenu() {
        SubMenu addProductMenu = new SubMenu("Ajouter un produit");

        addProductMenu.addMenuComponent(new MenuItem(createAddProductCommand(ProductType.BOOK)));
        addProductMenu.addMenuComponent(new MenuItem(createAddProductCommand(ProductType.COMPUTER_HARDWARE)));
        addProductMenu.addMenuComponent(new MenuItem(createAddProductCommand(ProductType.LEARNING_RESOURCE)));
        addProductMenu.addMenuComponent(new MenuItem(createAddProductCommand(ProductType.OFFICE_EQUIPMENT)));
        addProductMenu.addMenuComponent(new MenuItem(createAddProductCommand(ProductType.STATIONERY)));

        return addProductMenu;
    }

    private Command createAddProductCommand(ProductType productType) {
        ProductPrompt productPrompt = new ProductPrompt();
        return new Command() {
            @Override
            public String getName() {
                return productType.displayName();
            }

            @Override
            public void execute() {
                List<String> inputs = productPrompt.createProductPrompt(productType).getValuesFromUser();
                boolean productCreated = switch (productType) {
                  case BOOK -> productController.addBook(inputs, currentUser.getId());
                  case LEARNING_RESOURCE ->
                      productController.addLearningResource(inputs, currentUser.getId());
                  case STATIONERY -> productController.addStationery(inputs, currentUser.getId());
                  case OFFICE_EQUIPMENT ->
                      productController.addOfficeEquipment(inputs, currentUser.getId());
                  case COMPUTER_HARDWARE ->
                      productController.addComputerHardware(inputs, currentUser.getId());
                };

              if (productCreated) {
                    System.out.println(inputs.get(0) + " créé avec succès.");
                } else {
                    System.out.println("Échec de la création du produit. Veuillez réessayer.");
                }
            }
        };
    }


}