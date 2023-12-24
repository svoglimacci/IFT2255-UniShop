package org.udem.unishop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.udem.unishop.models.Book;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.ComputerHardware;
import org.udem.unishop.models.LearningResource;
import org.udem.unishop.models.OfficeEquipment;
import org.udem.unishop.models.Product;
import org.udem.unishop.models.ProductList;
import org.udem.unishop.models.Review;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.Stationery;
import org.udem.unishop.repositories.ProductRepository;
import org.udem.unishop.utilities.AccountType;
import org.udem.unishop.utilities.ProductType;
import org.udem.unishop.models.User;

/**
 * Service class for handling product-related operations.
 */
public class ProductService {

  private final ProductRepository productRepository;

  private final UserService userService;

  /**
   * Constructs a new ProductService with the provided ProductRepository and UserService.
   *
   * @param productRepository The repository for managing products.
   * @param userService The service for managing user-related operations.
   */
  public ProductService(ProductRepository productRepository, UserService userService) {
    this.productRepository = productRepository;
    this.userService = userService;
  }

  /**
   * Gets the product with the specified unique identifier.
   *
   * @param productId The unique identifier of the product.
   * @return The product associated with the given identifier.
   */
  public Product getProductById(UUID productId) {
    return productRepository.findById(productId);
  }

  /**
   * Gets a list of products of a specific type.
   *
   * @param productType The type of product.
   * @return A list of products matching the specified type.
   */
  public ProductList getProductsByType(ProductType productType) {
    return productRepository.getProductsByType(productType);
  }

  /**
   * Creates a new book product based on the provided inputs and associates it with a seller.
   *
   * @param inputs The list of input values for creating the book.
   * @param userId The ID of the seller who is creating the book.
   * @return true if the book creation is successful, false otherwise.
   */
  public boolean createBook(List<String> inputs, UUID userId) {
    List<UUID> instances = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(inputs.get(2)); i++) {
      instances.add(UUID.randomUUID());
    }
    double price = Double.parseDouble(inputs.get(3));
    int points = Integer.parseInt(inputs.get(4));
    Book newBook = new Book(inputs.get(0), inputs.get(1), instances, price, points, inputs.get(5),
        inputs.get(6),
        inputs.get(7), inputs.get(8), inputs.get(9), inputs.get(10), inputs.get(11), inputs.get(12));
    newBook.setSellerId(userId);
    userService.addProductToSeller(userService.getUserById(userId), newBook.getId());
    return productRepository.save(newBook);

  }

  /**
   * Creates a new computer hardware product based on the provided inputs and associates it with a seller.
   *
   * @param inputs The list of input values for creating the computer hardware.
   * @param userId The ID of the seller who is creating the computer hardware.
   * @return true if the computer hardware creation is successful, false otherwise.
   */
  public boolean createComputerHardware(List<String> inputs, UUID userId) {
    List<UUID> instances = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(inputs.get(2)); i++) {
      instances.add(UUID.randomUUID());
    }
    double price = Double.parseDouble(inputs.get(3));
    int points = Integer.parseInt(inputs.get(4));
    ComputerHardware newComputerHardware = new ComputerHardware(inputs.get(0), inputs.get(1), instances,
        price, points, inputs.get(5), inputs.get(6), inputs.get(7), inputs.get(8), inputs.get(9));
    newComputerHardware.setSellerId(userId);
    userService.addProductToSeller(userService.getUserById(userId), newComputerHardware.getId());
    return productRepository.save(newComputerHardware);
  }

  /**
   * Creates a new learning resource product based on the provided inputs and associates it with a seller.
   *
   * @param inputs The list of input values for creating the learning resource.
   * @param userId The ID of the seller who is creating the learning resource.
   * @return true if the learning resource creation is successful, false otherwise.
   */
  public boolean createLearningResource(List<String> inputs, UUID userId) {
    List<UUID> instances = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(inputs.get(2)); i++) {
      instances.add(UUID.randomUUID());
    }
    double price = Double.parseDouble(inputs.get(3));
    int points = Integer.parseInt(inputs.get(4));
    LearningResource newLearningResource = new LearningResource(inputs.get(0), inputs.get(1), instances,
        price, points, inputs.get(5), inputs.get(6), inputs.get(7), inputs.get(8), inputs.get(9), inputs.get(10),
        inputs.get(10));
    newLearningResource.setSellerId(userId);
    userService.addProductToSeller(userService.getUserById(userId), newLearningResource.getId());
    return productRepository.save(newLearningResource);
  }

  /**
   * Creates a new office equipement product based on the provided inputs and associates it with a seller.
   *
   * @param inputs The list of input values for creating the office equipement.
   * @param userId The ID of the seller who is creating the office equipement.
   * @return true if the office equipement creation is successful, false otherwise.
   */
  public boolean createOfficeEquipment(List<String> inputs, UUID userId) {
    List<UUID> instances = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(inputs.get(2)); i++) {
      instances.add(UUID.randomUUID());
    }
    double price = Double.parseDouble(inputs.get(3));
    int points = Integer.parseInt(inputs.get(4));
    OfficeEquipment newOfficeEquipment = new OfficeEquipment(inputs.get(0), inputs.get(1), instances, price,
        points, inputs.get(5), inputs.get(6), inputs.get(7), inputs.get(9));
    newOfficeEquipment.setSellerId(userId);
    userService.addProductToSeller(userService.getUserById(userId), newOfficeEquipment.getId());
    return productRepository.save(newOfficeEquipment);
  }
  /**
   * Creates a new stationery product based on the provided inputs and associates it with a seller.
   *
   * @param inputs The list of input values for creating the stationery.
   * @param userId The ID of the seller who is creating the stationery.
   * @return true if the stationery creation is successful, false otherwise.
   */
  public boolean createStationery(List<String> inputs, UUID userId) {
    List<UUID> instances = new ArrayList<>();
    for (int i = 0; i < Integer.parseInt(inputs.get(2)); i++) {
      instances.add(UUID.randomUUID());
    }
    double price = Double.parseDouble(inputs.get(3));
    int points = Integer.parseInt(inputs.get(4));
    Stationery newStationery = new Stationery(inputs.get(0), inputs.get(1), instances, price, points,
        inputs.get(5), inputs.get(6), inputs.get(7), inputs.get(8));
    newStationery.setSellerId(userId);
    userService.addProductToSeller(userService.getUserById(userId), newStationery.getId());
    return productRepository.save(newStationery);
  }

  /**
   * Adds a like to a product by a specific user.
   *
   * @param userId The ID of the user who is adding the like.
   * @param productId The ID of the product to which the like is being added.
   * @return true if the like is successfully added, false otherwise.
   */
  public boolean addLike(UUID userId, UUID productId) {
    Buyer user = (Buyer) userService.getUserById(userId);
    Product product = this.getProductById(productId);

    if (user == null || product == null) {
      return false;
    }

    if (user.getLikedProducts().contains(productId)) {
      return false;
    }

    product.setLikes(product.getLikes() + 1);
    userService.addLikedProduct(userId, productId);
    return productRepository.update(product);
  }

  /**
   * Removes a like from a product by a specific user.
   *
   * @param userId The ID of the user who is removing the like.
   * @param productId The ID of the product to which the like is being removed.
   * @return true if the like is successfully removed, false otherwise.
   */
  public boolean removeProductLike(UUID userId, UUID productId) {
    Buyer user = (Buyer) userService.getUserById(userId);
    Product product = this.getProductById(productId);

    if (user == null || product == null) {
      return false;
    }
    if (!user.getLikedProducts().contains(productId)) {
      return false;
    }
    product.setLikes(product.getLikes() - 1);
    userService.removeLikedProduct(userId, productId);
    productRepository.update(product);
    return true;
  }

  /**
   * Gets a list of all available products in the product repository.
   *
   * @return A lsit of all available products.
   */
  public ProductList getProducts() {
    return productRepository.getProducts();
  }


  /**
   * Adds a review to a product and associates it with the buyer.
   *
   * @param inputs The list of inputs containing the review details.
   * @param buyer The buyer who is adding the review.
   * @param product The product to which the review is being added.
   * @return true if the review was successfully added, false otherwise.
   */
  public boolean addReview(List<String> inputs, Buyer buyer, Product product) {
    int rating = Integer.parseInt(inputs.get(1));
    Review newReview = new Review(buyer.getUsername(), inputs.get(0), rating);
    product.addReview(newReview);
    userService.addReviewToBuyer(buyer.getId(), newReview.id);
    Seller seller = (Seller) userService.getUserById(product.getSellerId());
    seller.addNotification("Un nouveau commentaire a été ajouté à votre produit: " + product.getName());
    return productRepository.update(product);
  }

  /**
   * Removes an instance from the specified product and updates the product in the repository.
   *
   * @param productById The product from which the instance should be removed.
   * @param instance The UUID of the instance to be removed.
   */
  public void removeInstance(Product productById, UUID instance) {
    productById.getInstances().remove(instance);
    productRepository.update(productById);

  }

  /**
   * Updates the specified product in the repository.
   *
   * @param product The product to be updated.
   */
  public void updateProduct(Product product) {
    productRepository.update(product);
  }

  /**
   * Changes the bonus points for the specified product.
   *
   * @param product The product for which to change the bonus points.
   * @param input The new bonus points value as a string.
   * @return true if the update is successful, false otherwise.
   */
  public boolean changePoints(Product product, String input) {
    int points = Integer.parseInt(input);
    product.setBonusPoints(points);
    return productRepository.update(product);
  }

  /**
   * Adds a promotion to the specified product, setting promotion points and price.
   *
   * @param product The product to which the promotion will be added.
   * @param inputs A list of strings containing promotion information.
   * @return true if the update is successful, false otherwise.
   */
  public boolean addPromotion(Product product, List<String> inputs) {
    int points = Integer.parseInt(inputs.get(0));
    int price = Integer.parseInt(inputs.get(1));

    product.setPromotionPoints(points);
    product.setPromotionPrice(price);


    return productRepository.update(product);
  }

  /**
   * Adds a like to the specified review of a product. If it's the first like for the review, awards bonus points
   * to the author.
   *
   * @param user The ID of the user giving the like.
   * @param product The ID of the product to which the review belongs.
   * @param review The ID of the review to be liked.
   * @return true if the update is successful, false otherwise.
   */
  public boolean addReviewLike(UUID user, UUID product, UUID review) {
    Product productById = getProductById(product);
    Review reviewById = productById.getReviewById(review);

        if(reviewById.getLikes() == 0) {
      Buyer buyer = (Buyer) userService.getUserByUsername(reviewById.getAuthor(), AccountType.BUYER);
      buyer.addFidelityPoints(10);
      userService.updateUser(buyer);
    }

    reviewById.like();
    User userById = userService.getUserById(user);
    userById.addLikedReview(review);


    return productRepository.update(productById);

  }

  /**
   * Removes a like from the specified review of a product. If there is no more likes for the review, deducts bonus
   * points from the author.
   *
   * @param user The ID of the user removing the like.
   * @param product The ID of the product to which the review belongs.
   * @param review The ID of the review to be unliked.
   * @return true if the update is successful, false otherwise.
   */
  public boolean removeReviewLike(UUID user, UUID product, UUID review) {
    Product productById = getProductById(product);
    Review reviewById = productById.getReviewById(review);

    if(reviewById.getLikes() == 0) {
      Buyer buyer = (Buyer) userService.getUserByUsername(reviewById.getAuthor(), AccountType.BUYER);
      buyer.addFidelityPoints(10);
      userService.updateUser(buyer);
    }
    reviewById.dislike();
    User userById = userService.getUserById(user);
    userById.removeLikedReview(review);
    return productRepository.update(productById);
  }

}