package org.udem.unishop.product;

import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.ProductList;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;

public class ProductCatalog {

  private final SubMenu productCatalog;

  private final User currentUser;


  public ProductCatalog(UserController userController, ProductController productController,
      User currentUser) {
    this.currentUser = currentUser;
    this.productCatalog = new SubMenu("Catalogue de produits");

    createProductList(productController, userController);
  }

  public void run() {
    productCatalog.execute();
  }



  private void createProductList(ProductController productController,
      UserController userController) {
    ProductList productList = productController.getProducts();

    for (Object object : productList) {
      Product product = (Product) object;
      ProductPage productPage = new ProductPage(product, productController, userController,
          currentUser);

      Command showProductCommand = new Command() {
        @Override
        public String getName() {
          return product.getName();
        }

        @Override
        public void execute() {
          productPage.run();

        }
      };

      productCatalog.addMenuComponent(new MenuItem(showProductCommand));

      }

    }

  }