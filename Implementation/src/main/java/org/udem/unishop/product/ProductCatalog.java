package org.udem.unishop.product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.ComputerHardware;
import org.udem.unishop.models.OfficeEquipment;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.ProductList;
import org.udem.unishop.models.Stationery;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.ProductType;
import org.udem.unishop.utilities.SearchType;
import org.udem.unishop.utilities.SubMenu;

/**
 * The ProductCatalog class represents a catalog of products, providing functionality to display and filter products
 * based on different search criteria.
 */
public class ProductCatalog {

  private final SubMenu productCatalog;

  private final User currentUser;

  private final Set<ProductPage> productPages = new HashSet<>();

  /**
   * Constructs a ProductCatalog with the specified controllers and current user, initializing
   * the product catalog SubMenu and creating the product list.
   *
   * @param userController The UserController instance for managing users.
   * @param productController The ProductController instance for managing products.
   * @param currentUser The current user accessing the catalog.
   */
  public ProductCatalog(UserController userController, ProductController productController,
      User currentUser) {
    this.currentUser = currentUser;
    this.productCatalog = new SubMenu("Catalogue de produits");

    createProductList(productController, userController);
  }

  /**
   * Constructs a ProductCatalog with the specified controllers, current user, and search criteria,
   * initializing the product catalog SubMenu and creating a filtered product list based on the
   * provided search parameters.
   *
   * @param userController The UserController instance for managing users.
   * @param productController The ProductController instance for managing products.
   * @param currentUser The current user accessing the catalog.
   * @param searchType The type of search criteria to apply.
   * @param searchValue The list of search values corresponding to the searchType.
   */
  public ProductCatalog(UserController userController, ProductController productController,
      User currentUser, SearchType searchType, List<String> searchValue) {
    this.currentUser = currentUser;
    this.productCatalog = new SubMenu("Catalogue de produits");

    createFilteredProductList(productController, userController, searchType, searchValue);
  }

  /**
   * Creates a filtered list of products based on the specified search criteria and values.
   *
   * @param productController The ProductController instance for managing products.
   * @param userController The UserController instance for managing users.
   * @param searchType The type of search criteria to apply.
   * @param searchValue The list of search values corresponding to the searchType.
   */
  private void createFilteredProductList(ProductController productController,
      UserController userController, SearchType searchType, List<String> searchValue) {

    for (int i = 0; i < searchValue.size(); i++) {
      searchValue.set(i, searchValue.get(i).toLowerCase());
    }

    ProductList productList = productController.getProducts();

    for (Object product : productList) {
      Product p = (Product) product;

      switch (searchType) {
        case PRODUCT_NAME:
          if (p.getName().toLowerCase().contains(searchValue.get(0))) {
            addProductPage(p, productController, userController);
          }
          break;

        case PRODUCT_MODEL:
          String model = "";
          if (p.getProductType() == ProductType.COMPUTER_HARDWARE) {
            model = ((ComputerHardware) p).getModel();
          } else if (p.getProductType() == ProductType.OFFICE_EQUIPMENT) {
            model = ((OfficeEquipment) p).getModel();
          } else if (p.getProductType() == ProductType.STATIONERY) {
            model = ((Stationery) p).getModel();
          }

          if (model.toLowerCase().contains(searchValue.get(0))) {
            addProductPage(p, productController, userController);
          }
          break;

        case PRODUCT_CATEGORY:
          if (p.getProductType().toString().toLowerCase().contains(searchValue.get(0))) {
            addProductPage(p, productController, userController);
          }
          break;

        case PRODUCT_PRICE:
          double minPrice = Double.parseDouble(searchValue.get(0));
          double maxPrice = Double.parseDouble(searchValue.get(1));
          if (p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
            addProductPage(p, productController, userController);
          }
          break;

        case PRODUCT_BRAND:
          String brand = "";
          if (p.getProductType() == ProductType.COMPUTER_HARDWARE) {
            brand = ((ComputerHardware) p).getBrand();
          } else if (p.getProductType() == ProductType.OFFICE_EQUIPMENT) {
            brand = ((OfficeEquipment) p).getBrand();
          } else if (p.getProductType() == ProductType.STATIONERY) {
            brand = ((Stationery) p).getBrand();
          }

          if (brand.toLowerCase().contains(searchValue.get(0))) {
            addProductPage(p, productController, userController);
          }
          break;
      }
    }
  }

  /**
   * Runs the product catalog, allowing users to view and interact with the displayed products.
   */
  public void run() {
    productCatalog.execute();
  }

  /**
   * Creates the initial list of products for the catalog by retrieving all available products
   * from the ProductController and adding corresponding ProductPages to the catalog.
   *
   * @param productController The ProductController instance for managing products.
   * @param userController The UserController instance for managing users.
   */
  private void createProductList(ProductController productController,
      UserController userController) {
    ProductList productList = productController.getProducts();

    for (Object product : productList) {
      Product p = (Product) product;
      addProductPage(p, productController, userController);
    }
  }

  /**
   * Adds a ProductPage to the catalog for the specified product, ensuring no duplicate pages
   * are added.
   *
   * @param product The product for which to create a ProductPage.
   * @param productController The ProductController instance for managing products.
   * @param userController The UserController instance for managing users.
   */
  private void addProductPage(Product product, ProductController productController,
      UserController userController) {
    ProductPage productPage = new ProductPage(product, productController, userController,
        currentUser);

    if (!productPages.contains(productPage)) {
      productPages.add(productPage);

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