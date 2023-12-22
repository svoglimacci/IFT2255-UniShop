package org.udem.unishop;

import org.udem.unishop.authentication.MainMenu;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.utilities.ContainerConfig;
import org.udem.unishop.utilities.IoCContainer;


public class Main {

  public static void main(String[] args) {
    IoCContainer container = ContainerConfig.setupContainer();
    UserController userController = container.getInstance(UserController.class);
    ProductController productController = container.getInstance(ProductController.class);
    OrderController orderController = container.getInstance(OrderController.class);

    MainMenu mainMenuInstance = new MainMenu(userController, productController, orderController);
    System.out.println("Bienvenue chez UniShop!");
    mainMenuInstance.run();


  }

}