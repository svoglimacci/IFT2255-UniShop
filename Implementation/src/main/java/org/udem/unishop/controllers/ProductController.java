package org.udem.unishop.controllers;

import java.util.List;
import java.util.UUID;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.ProductList;
import org.udem.unishop.models.User;
import org.udem.unishop.services.ProductService;
import org.udem.unishop.utilities.ProductType;

public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  public ProductList getBooks() {
    return productService.getProductsByType(ProductType.BOOK);
  }

  public ProductList getComputerHardware() {
    return productService.getProductsByType(ProductType.COMPUTER_HARDWARE);
  }

  public ProductList getLearningResources() {
    return productService.getProductsByType(ProductType.LEARNING_RESOURCE);
  }

  public ProductList getOfficeEquipment() {
    return productService.getProductsByType(ProductType.OFFICE_EQUIPMENT);
  }

  public ProductList getStationery() {
    return productService.getProductsByType(ProductType.STATIONERY);
  }

  public boolean addBook(List<String> inputs, UUID userId) {
    return productService.createBook(inputs, userId);
  }

  public boolean addComputerHardware(List<String> inputs, UUID userId) {
    return productService.createComputerHardware(inputs, userId);
  }

  public boolean addLearningResource(List<String> inputs, UUID userId) {
    return productService.createLearningResource(inputs, userId);
  }

  public boolean addOfficeEquipment(List<String> inputs, UUID userId) {
    return productService.createOfficeEquipment(inputs, userId);
  }

  public boolean addStationery(List<String> inputs, UUID userId) {
    return productService.createStationery(inputs, userId);
  }


  public boolean addProductLike(UUID userId, UUID productId) {
    return productService.addLike(userId, productId);
  }

  public boolean removeProductLike(UUID userId, UUID productId) {
    return productService.removeProductLike(userId, productId);
  }

  public ProductList getProducts() {
    return productService.getProducts();
  }


  public Product getProductById(UUID productId) {
    return productService.getProductById(productId);
  }


  public boolean addReview(List<String> inputs, Buyer buyer, Product product) {
    return productService.addReview(inputs, buyer, product);
  }

  public boolean changePoints(Product product, String input) {
    return productService.changePoints(product, input);
  }

  public boolean addPromotion(Product product, List<String> inputs) {
    return productService.addPromotion(product, inputs);
  }

  public boolean addReviewLike(UUID user, UUID product, UUID review) {
    return productService.addReviewLike(user, product, review);

  }

  public boolean removeReviewLike(UUID user, UUID product, UUID review) {
    return productService.removeReviewLike(user, product, review);
  }

}