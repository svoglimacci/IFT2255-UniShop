package org.udem.unishop.utilities;

import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.repositories.ProductRepository;
import org.udem.unishop.repositories.UserRepository;
import org.udem.unishop.services.OrderService;
import org.udem.unishop.services.ProductService;
import org.udem.unishop.services.UserService;

public class ContainerConfig {

  public static final String USERS_JSON_FILE = "users.json";
  public static final String PRODUCTS_JSON_FILE = "products.json";

  public static IoCContainer setupContainer() {
    IoCContainer container = new IoCContainer();

    UserRepository userRepository = new UserRepository(new JSONHandler(), USERS_JSON_FILE);
    container.registerInstance(UserRepository.class, userRepository);

    UserService userService = new UserService(userRepository);
    container.registerInstance(UserService.class, userService);

    UserController userController = new UserController(userService);
    container.registerInstance(UserController.class, userController);

    ProductRepository productRepository = new ProductRepository(new JSONHandler(),
        PRODUCTS_JSON_FILE);
    container.registerInstance(ProductRepository.class, productRepository);

    ProductService productService = new ProductService(productRepository, userService);
    container.registerInstance(ProductService.class, productService);

    ProductController productController = new ProductController(productService);
    container.registerInstance(ProductController.class, productController);

    OrderController orderController = new OrderController(new OrderService(userService, productService));
    container.registerInstance(OrderController.class, orderController);

    OrderService orderService = new OrderService(userService, productService);
    container.registerInstance(OrderService.class, orderService);


    return container;
  }
}