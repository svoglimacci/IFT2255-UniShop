package org.udem.unishop.common;

import java.util.HashMap;
import java.util.List;
import org.udem.unishop.controllers.OrderController;
import org.udem.unishop.controllers.ProductController;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Order;
import org.udem.unishop.models.ProductList;
import org.udem.unishop.models.User;
import org.udem.unishop.models.Seller;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.OrderState;
import org.udem.unishop.utilities.SubMenu;


public class MetricsPage {

  private final User currentUser;
  private final UserController userController;
  private final ProductController productController;
  private final OrderController orderController;

  public MetricsPage(User currentUser, UserController userController, ProductController productController, OrderController orderController) {
    this.currentUser = currentUser;
    this.userController = userController;
    this.productController = productController;
    this.orderController = orderController;
  }

  public void run() {


    HashMap<String, List<String>> chosenMetrics = currentUser.getSelectedMetrics();
    System.out.println(chosenMetrics.keySet().isEmpty() ? "Vous n'avez pas encore choisi de métriques à afficher."
        : "Vos métriques:");

    for (String metric : chosenMetrics.keySet()) {
          double profit = 0;
          int sales = 0;
          int returns = 0;

          if(currentUser instanceof Seller) {
                List<Order> orders = currentUser.getOrderList();

          for (Order order : orders) {
            if (order.getStatus().equals(OrderState.RETURNED)) {
              returns++;

            }
            if (order.getStatus().equals((OrderState.DELIVERED))) {
              profit += order.getPrice();
              sales = sales + order.getProducts().size();

            }
          }
    }

      switch (metric) {
        case "Purchases":
          System.out.println("Achats:");
          for (String subMetric : currentUser.getSelectedMetrics().get(metric)) {
            switch (subMetric) {
              case "Products":
                System.out.println("- Produits " + currentUser.getProductsIds().size());
                break;
              case "Orders":
                System.out.println("- Commandes " + currentUser.getOrderList().size());
                break;
            }
          }
          break;
        case "Account":
          System.out.println("Compte:");
          for (String subMetric : currentUser.getSelectedMetrics().get(metric)) {
            switch (subMetric) {
              case "Followers":
                System.out.println("- Abonnés: " + ((Buyer) currentUser).getFollowers().size());
                break;
              case "Following":
                System.out.println("- Abonnements: " + ((Buyer) currentUser).getFollowed().size());
                break;
              case "Likes":
                System.out.println("- Mentions j'aime: " + ((Seller) currentUser).getLikes());
                break;
            }
          }
          break;
        case "Activities":
          System.out.println("Activités:");
          for (String subMetric : currentUser.getSelectedMetrics().get(metric)) {
            switch (subMetric) {
              case "Likes":
                System.out.println("- J'aime: " + ((Buyer) currentUser).getLikedProducts().size());
                break;
              case "Reviews":
                System.out.println("- Commentaires: " + ((Buyer) currentUser).getReviews().size());
                break;
            }
          }
          break;
        case "Sales":
          System.out.println("Ventes:");
          for (String subMetric : currentUser.getSelectedMetrics().get(metric)) {
            switch (subMetric) {
              case "Amount":
                System.out.println("- Quantité: " + sales);
                break;
              case "Value":
                System.out.println("- Valeur: " + profit);
                break;
            }
          }
          break;
        case "Products":
          System.out.println("Produits:");
          for (String subMetric : currentUser.getSelectedMetrics().get(metric)) {
            switch (subMetric) {
              case "Amount":
                System.out.println("- En vente: " + currentUser.getProductsIds().size());
                break;
              case "Returned":
                System.out.println("- Retournés: " + returns);
                break;
            }
          }

      }
    }
              MetricsMenu metricsMenu = new MetricsMenu(currentUser, userController);
          metricsMenu.execute();
  }
}