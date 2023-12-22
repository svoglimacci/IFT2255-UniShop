package org.udem.unishop.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.Buyer;
import org.udem.unishop.models.Seller;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.SubMenu;


public class MetricsOptionsMenu extends SubMenu {


  private final User currentUser;

  private final UserController userController;

  public MetricsOptionsMenu(User currentUser, UserController userController) {
    super("Métriques");
    this.userController = userController;
    this.currentUser = (currentUser instanceof Buyer) ? (Buyer) currentUser : (Seller) currentUser;
    createMetricsList(currentUser);

  }

  public void run() {
  }

  private void createMetricsList(
      User currentUser) {
    Scanner scanner = new Scanner(System.in);
    int input;
    int size = 0;
    HashMap<String, List<String>> chosenMetrics = currentUser.getSelectedMetrics();
    for(String metric : chosenMetrics.keySet()){
      for(String subMetric : chosenMetrics.get(metric)){
        size++;
      }
    }

    HashMap<String, List<String>> metrics = new HashMap<>();

    if(currentUser instanceof Buyer){
          metrics.put("Purchases", Arrays.asList("Products", "Orders"));
      metrics.put("Account", Arrays.asList("Followers", "Following"));
    metrics.put("Activities", Arrays.asList("Likes", "Reviews"));
    }

    if(currentUser instanceof Seller) {
      metrics.put("Sales", Arrays.asList("Amount", "Value"));
      metrics.put("Products", Arrays.asList("Amount", "Returned"));
      metrics.put("Account", Arrays.asList("Likes"));
    }

    do {
      System.out.println("Veuillez sélectionner une catégorie de métrique à afficher:");

      int i = 1;
      for (String metric : metrics.keySet()) {
        System.out.println(i + ". " + metric + (chosenMetrics.containsKey(metric) ? " (" + chosenMetrics.get(metric).size() + ")" : ""));
        i++;
      }

      System.out.println("0. Retour");

      input = scanner.nextInt();
      if (input == 0) {
        currentUser.setSelectedMetrics(chosenMetrics);
        userController.updateUser(currentUser);
        return;
      }

      if (input < 1 || input > metrics.size()) {
        System.out.println("Choix invalide, veuillez entrer un nombre entre 0 et " + i);
        continue;
      }

      String selectedMetric = (String) metrics.keySet().toArray()[input - 1];
      System.out.println(selectedMetric);

      System.out.println("Veuillez sélectionner les sous-facteurs à afficher:");
      List<String> subFactors = metrics.get(selectedMetric);
      for (int j = 0; j < subFactors.size(); j++) {
        System.out.println(j + 1 + ". " + subFactors.get(j) + (chosenMetrics.containsKey(selectedMetric) && chosenMetrics.get(selectedMetric).contains(subFactors.get(j)) ? " (*)" : ""));
      }
      System.out.println("0. " + "Retour");
      input = scanner.nextInt();
      if (input == 0) {
        continue;
      }

if (input < 1 || input > subFactors.size()) {
        System.out.println("Choix invalide, veuillez entrer un nombre entre 0 et " + i);
        continue;
      }

      String selectedSubFactor = subFactors.get(input - 1);

if(size >= 10){
        System.out.println("Impossible d'afficher plus de 10 métriques à la fois.");
        continue;
      }
      System.out.println(selectedSubFactor);

      if (chosenMetrics.containsKey(selectedMetric)) {
    if (!chosenMetrics.get(selectedMetric).contains(selectedSubFactor)) {

        chosenMetrics.get(selectedMetric).add(selectedSubFactor);
        size++;

    } else {
        chosenMetrics.get(selectedMetric).remove(selectedSubFactor);
        size--;
    }
} else {
    chosenMetrics.put(selectedMetric, new ArrayList<>(Collections.singletonList(selectedSubFactor)));
}

    } while (true);
  }
}