package org.udem.unishop.repositories;

import java.util.UUID;
import org.udem.unishop.models.ComputerHardware;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.ProductList;
import org.udem.unishop.models.Stationery;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.JSONHandler;
import org.udem.unishop.utilities.ProductType;

public class ProductRepository {

  private final JSONHandler jsonHandler;
  private final String jsonFilePath;
  private ProductList<Product> productList = new ProductList<>();

  // Atomic id generator

  public ProductRepository(JSONHandler jsonHandler, String jsonFilePath) {
    this.jsonHandler = jsonHandler;
    this.jsonFilePath = jsonFilePath;
    this.productList = readDataFromJson();

    if (this.productList == null) {
      this.productList = new ProductList<>();
    }
  }

  public Product findById(UUID productId) {
    return productList.stream().filter(user -> user.getId().equals(productId)).findFirst().orElse(null);
  }

  public Product findByName(String name, ProductType productType) {
    return productList.stream().filter(
            product -> product.getName().equals(name) && product.getProductType() == productType)
        .findFirst().orElse(null);
  }

public boolean save(Product product) {
    boolean added = productList.add(product);
    if (added) {
        writeDataToJson();
    }
    return added;
}

public boolean delete(Product product) {
    boolean removed = productList.removeIf(u -> u.equals(product));
    if (removed) {
        writeDataToJson();
    }
    return removed;
}

public boolean update(Product product) {
    boolean userExists = productList.contains(product);
    if (userExists) {
        productList.removeIf(u -> u.equals(product));
        productList.add(product);
        writeDataToJson();
        return true;
    }
    return false;
}

  public ProductList getProductsByType(ProductType productType) {
    ProductList<Product> productsByType = new ProductList<>();
    for (Product product : productList) {
      if (product.getProductType() == productType) {
        productsByType.add(product);
      }
    }
    return productsByType;
  }

  private ProductList readDataFromJson() {
    return jsonHandler.readJsonFromFile(jsonFilePath, ProductList.class);
  }

  private boolean writeDataToJson() {
    try {
      jsonHandler.writeJsonToFile(productList, jsonFilePath);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  public ProductList getProducts() {
    return productList;
  }

}