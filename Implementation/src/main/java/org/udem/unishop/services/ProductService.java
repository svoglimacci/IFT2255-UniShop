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

public class ProductService {

  private final ProductRepository productRepository;

  private final UserService userService;

  public ProductService(ProductRepository productRepository, UserService userService) {
    this.productRepository = productRepository;
    this.userService = userService;
  }

  public Product getProductById(UUID productId) {
    return productRepository.findById(productId);
  }

  public ProductList getProductsByType(ProductType productType) {
    return productRepository.getProductsByType(productType);
  }

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

  public ProductList getProducts() {
    return productRepository.getProducts();
  }



  public boolean addReview(List<String> inputs, Buyer buyer, Product product) {
    int rating = Integer.parseInt(inputs.get(1));
    Review newReview = new Review(buyer.getUsername(), inputs.get(0), rating);
    product.addReview(newReview);
    userService.addReviewToBuyer(buyer.getId(), newReview.id);
    Seller seller = (Seller) userService.getUserById(product.getSellerId());
    seller.addNotification("Un nouveau commentaire a été ajouté à votre produit: " + product.getName());
    return productRepository.update(product);
  }

  public void removeInstance(Product productById, UUID instance) {
    productById.getInstances().remove(instance);
    productRepository.update(productById);

  }

  public void updateProduct(Product product) {
    productRepository.update(product);
  }

  public boolean changePoints(Product product, String input) {
    int points = Integer.parseInt(input);
    product.setBonusPoints(points);
    return productRepository.update(product);
  }

  public boolean addPromotion(Product product, List<String> inputs) {
    int points = Integer.parseInt(inputs.get(0));
    int price = Integer.parseInt(inputs.get(1));

    product.setPromotionPoints(points);
    product.setPromotionPrice(price);


    return productRepository.update(product);
  }

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