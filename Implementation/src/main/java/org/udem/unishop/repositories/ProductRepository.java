package org.udem.unishop.repositories;

import java.util.UUID;
import org.udem.unishop.models.ComputerHardware;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.ProductList;
import org.udem.unishop.models.Stationery;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.JSONHandler;
import org.udem.unishop.utilities.ProductType;

/**
 * The ProductRepository class is responsible for managing and persisting products.
 */
public class ProductRepository {

  private final JSONHandler jsonHandler;
  private final String jsonFilePath;
  private ProductList<Product> productList = new ProductList<>();

  // Atomic id generator

    /**
     * Constructor for ProductRepository.
     *
     * @param jsonHandler The JSONHandler instance for reading and writing JSON data.
     * @param jsonFilePath The file path to the JSON file storing product data.
     */
  public ProductRepository(JSONHandler jsonHandler, String jsonFilePath) {
    this.jsonHandler = jsonHandler;
    this.jsonFilePath = jsonFilePath;
    this.productList = readDataFromJson();

    if (this.productList == null) {
      this.productList = new ProductList<>();
    }
  }

  /**
   * Finds a product by its unique identifier (ID).
   *
   * @param productId The ID of the product to find.
   * @return The found product or null if not found.
   */
  public Product findById(UUID productId) {
    return productList.stream().filter(user -> user.getId().equals(productId)).findFirst().orElse(null);
  }

  /**
   * Finds a product by its name and type.
   *
   * @param name The name of the product to find.
   * @param productType The type of the product to find.
   * @return The found product or null if not found.
   */
  public Product findByName(String name, ProductType productType) {
    return productList.stream().filter(
            product -> product.getName().equals(name) && product.getProductType() == productType)
        .findFirst().orElse(null);
  }

    /**
     * Saves a product to the repository and writes the updated data to the JSON file.
     *
     * @param product The product to save.
     * @return true if the product is successfully saved, false otherwise.
     */
    public boolean save(Product product) {
        boolean added = productList.add(product);
        if (added) {
            writeDataToJson();
        }

        return added;
    }

    /**
     * Deletes a product from the repository and writes the updated data to the JSON file.
     *
     * @param product The product to delete.
     * @return true if the product is successfully deleted, false otherwise.
     */
    public boolean delete(Product product) {
        boolean removed = productList.removeIf(u -> u.equals(product));
        if (removed) {
            writeDataToJson();
        }

        return removed;
    }

    /**
     * Updates a product in the repository and writes the updated data to the JSON file.
     *
     * @param product The product to update.
     * @return true if the product is successfully updated, false otherwise.
     */
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

    /**
     * Gets a list of products based on the specified product type.
     *
     * @param productType The type of products.
     * @return A list of products matching the specified type.
     */
    public ProductList getProductsByType(ProductType productType) {
    ProductList<Product> productsByType = new ProductList<>();
    for (Product product : productList) {
      if (product.getProductType() == productType) {
        productsByType.add(product);
      }
    }

    return productsByType;
  }

    /**
     * Reads product data from the JSON file.
     *
     * @return The list of products read from the JSON file.
     */
    private ProductList readDataFromJson() {
    return jsonHandler.readJsonFromFile(jsonFilePath, ProductList.class);
  }

    /**
     * Writes product data to the JSON file.
     *
     * @return true if the data is successfully written to the file, false otherwise.
     */
    private boolean writeDataToJson() {
    try {
      jsonHandler.writeJsonToFile(productList, jsonFilePath);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

    /**
     * Gets the list of all products in the repository.
     *
     * @return The list of all products.
     */
    public ProductList getProducts() {
    return productList;
  }

}