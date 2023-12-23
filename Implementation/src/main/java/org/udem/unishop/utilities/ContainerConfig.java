package org.udem.unishop.utilities;

import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.repositories.ProductRepository;
import org.udem.unishop.repositories.UserRepository;
import org.udem.unishop.services.OrderService;
import org.udem.unishop.services.ProductService;
import org.udem.unishop.services.UserService;

/**
 * Configuration class for setting up the IoC container.
 * This class registers various components such as repositories, services and controllers.
 */
public class ContainerConfig {

  /**
   * A JSON file for storing user-related data.
   */
  public static final String USERS_JSON_FILE = "users.json";

  /**
   * A JSON file for storing product-related data.
   */
  public static final String PRODUCTS_JSON_FILE = "products.json";


  /**
   * Sets up and configures the IoC container with the necessary components.
   *
   * @return The configured IoC container.
   */
  public static IoCContainer setupContainer() {
    IoCContainer container = new IoCContainer();

    // user-related components
    UserRepository userRepository = new UserRepository(new JSONHandler(), USERS_JSON_FILE);
    container.registerInstance(UserRepository.class, userRepository);

    UserService userService = new UserService(userRepository);
    container.registerInstance(UserService.class, userService);

    UserController userController = new UserController(userService);
    container.registerInstance(UserController.class, userController);

    // product-related components
    ProductRepository productRepository = new ProductRepository(new JSONHandler(),
        PRODUCTS_JSON_FILE);
    container.registerInstance(ProductRepository.class, productRepository);

    ProductService productService = new ProductService(productRepository, userService);
    container.registerInstance(ProductService.class, productService);

    ProductController productController = new ProductController(productService);
    container.registerInstance(ProductController.class, productController);

    // order-related components
    OrderController orderController = new OrderController(new OrderService(userService, productService));
    container.registerInstance(OrderController.class, orderController);

    OrderService orderService = new OrderService(userService, productService);
    container.registerInstance(OrderService.class, orderService);


    return container;
  }
}