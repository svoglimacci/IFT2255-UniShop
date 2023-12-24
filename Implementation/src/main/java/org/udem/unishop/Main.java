package org.udem.unishop;

/**
 * UniShop is a system design to facilitate students from university in purchasing various products for their
 * academic needs. It offers products such as textbooks, stationeries, office equipments and electronics. To
 * help the search, students can filter by various settings, follow relevant sellers, etc. Users can also register
 * as a seller to sell products on this market.
 *
 * @author Simon Vgolimacci (20002825)
 * @author Julie Yang (20239909)
 * @author Celina Zhang (20207461)
 * @author Victor Leblond (20244841)
 */

import org.udem.unishop.authentication.MainMenu;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.repositories.ProductRepository;
import org.udem.unishop.repositories.UserRepository;
import org.udem.unishop.utilities.ContainerConfig;
import org.udem.unishop.utilities.IoCContainer;

public class Main {

  /**
   * The main method of the program
   * @param args
   */
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