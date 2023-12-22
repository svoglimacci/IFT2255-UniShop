package org.udem.unishop.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.udem.unishop.buyer.CheckoutPrompt;
import org.udem.unishop.models.Seller;
import org.udem.unishop.product.QuantityPrompt;
import org.udem.unishop.utilities.Prompt;
import org.udem.unishop.utilities.SubMenu;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Order;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.models.Product;
import java.util.UUID;


public class ReturnMenu {

  private final SubMenu returnMenu;
  private final Buyer currentUser;

  private final boolean isExchange;

  private final Order order;

  public ReturnMenu(Buyer currentUser, Order order, OrderController orderController, UserController userController, ProductController productController, boolean isExchange) {
    this.currentUser = currentUser;
    this.order = order;
    this.isExchange = isExchange;
    this.returnMenu = isExchange ? new SubMenu("Échange") : new SubMenu("Retour");
    createReturnItemList(orderController, userController, productController, order);

  }


  public void run() {

  }

  private void createReturnItemList(OrderController orderController, UserController userController,
      ProductController productController, Order order) {
    Scanner scanner = new Scanner(System.in);
int input;
    HashMap<UUID, List<UUID>> returnItems = new HashMap<>();
do {
  System.out.println("Veuillez sélectionner les articles à" + (isExchange ? " échanger" : " retourner"));

  System.out.println("Articles sélectionnés:");
  for (UUID product : returnItems.keySet()) {
    System.out.println(productController.getProductById(product).getName() + " - "
        + returnItems.get(product).size() + " - " + productController.getProductById(product).getPrice() + "$");
  }
  int i = 1;
  List<Integer> quantityList = new ArrayList<>();
  List<UUID> productList = new ArrayList<>();

  Set<UUID> products = order.getProducts();

  for (UUID id : products) {
    Product product = productController.getProductById(id);
    System.out.println(i + ". " + product.getName() + " - " + order.getProductQuantity(id) + " - " + product.getPrice() + "$");
    quantityList.add(order.getProductQuantity(id));
    productList.add(product.getId());
    i++;
  }
  if (!returnItems.isEmpty() ) {
    System.out.println(i + ". " + (isExchange ? "Procéder a l'échange" : "Procéder au retour"));
  }
  System.out.println(0 + ". " + "Retour");

  input = scanner.nextInt();
    if (input == 0) {
      return;
  }

  if (input < i && input > 0) {
    QuantityPrompt quantityPrompt = new QuantityPrompt();
    int quantity = Integer.parseInt(quantityPrompt.createQuantityPrompt().getValuesFromUser().get(0));
    while (quantity > quantityList.get(input - 1)) {
      System.out.println("La quantité entrée est supérieure à la quantité commandée");
      quantity = Integer.parseInt(quantityPrompt.createQuantityPrompt().getValuesFromUser().get(0));
    }
    if (quantity == 0) {
      UUID id = productController.getProductById(productList.get(input - 1)).getId();
      returnItems.remove(id);
    } else {
      List<UUID> instances = new ArrayList<>();
      for (int j = 0; j < quantity; j++) {
        instances.add(productController.getProductById(productList.get(input - 1)).getInstances().get(j));
      }
      returnItems.put(productList.get(input - 1), instances);
    }
  } else {
    if (input == i) {
      if (isExchange) {
        createExchangeItemList(orderController, userController, productController, order, returnItems);
        return;
      }
      orderController.returnOrder(order, returnItems);
      System.out.println("Retour" + " effectué");
      System.out.println("Veuillez vous présenter à la poste pour retourner le(s) produit(s) à l'entrepôt.");
      return;
    }
    return;
  }
} while (true);

  }

  private void createExchangeItemList(OrderController orderController, UserController userController, ProductController productController, Order order, HashMap<UUID, List<UUID>> returnItems) {
        Scanner scanner = new Scanner(System.in);
int input;
    HashMap<UUID, List<UUID>> exchangeItems = new HashMap<>();
do {

  System.out.println("Veillez sélectionner les articles voulu en échange");

  System.out.println("Articles sélectionnés:");
  for (UUID product : exchangeItems.keySet()) {
    System.out.println(productController.getProductById(product).getName() + " - "
        + exchangeItems.get(product).size() + " - " + productController.getProductById(product).getPrice() + "$");
  }
  double exchangePrice = 0;
  for (UUID product : exchangeItems.keySet()) {
    exchangePrice += productController.getProductById(product).getPrice() * exchangeItems.get(product).size();
  }

  double returnPrice = 0;
  for (UUID product : returnItems.keySet()) {
    returnPrice += productController.getProductById(product).getPrice() * returnItems.get(product).size();
  }

  exchangePrice -= returnPrice;

  System.out.println("Prix de l'échange: " + exchangePrice + "$");

  int i = 1;
  List<Integer> quantityList = new ArrayList<>();
  List<UUID> productList = new ArrayList<>();

  Seller seller = (Seller) userController.getUserById(order.getSellerId());
  Set<UUID> products = seller.getProductsIds();
  for (UUID id : products) {
    Product product = productController.getProductById(id);
    System.out.println(i + ". " + product.getName() + " - " + product.getQuantity() + " - " + product.getPrice() + "$");
    quantityList.add(product.getQuantity());
    productList.add(product.getId());
    i++;
  }
  if (!exchangeItems.isEmpty()) {
    System.out.println(i + ". " + "Procéder a l'échange");
  }
  System.out.println(0 + ". " + "Retour");

  input = scanner.nextInt();

    if (input == 0) {
    return;
  }

  if (input < i && input > 0) {
    QuantityPrompt quantityPrompt = new QuantityPrompt();
    int quantity = Integer.parseInt(quantityPrompt.createQuantityPrompt().getValuesFromUser().get(0));
    while (quantity > quantityList.get(input - 1)) {
      System.out.println("La quantité entrée est supérieure à la quantité commandée");
      quantity = Integer.parseInt(quantityPrompt.createQuantityPrompt().getValuesFromUser().get(0));
    }
    if (quantity == 0) {
      UUID id = productController.getProductById(productList.get(input - 1)).getId();
      exchangeItems.remove(id);
    } else {
      List<UUID> instances = new ArrayList<>();
      for (int j = 0; j < quantity; j++) {
        instances.add(productController.getProductById(productList.get(input - 1)).getInstances().get(j));
      }
      exchangeItems.put(productList.get(input - 1), instances);
    }
  } else {
    if (input == i) {
      CheckoutPrompt checkoutPrompt = new CheckoutPrompt();
      List<String> inputs = checkoutPrompt.createCheckoutPrompt().getValuesFromUser();


      orderController.exchangeOrder(order, exchangeItems, returnItems);
      System.out.println("Échange" + " effectué");
      System.out.println("Veuillez vous présenter à la poste pour retourner le(s) produit(s) à l'entrepôt.");
      return;
    }
  }
} while (true);
  }
}