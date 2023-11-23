package org.udem.unishop.utilities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu implements MenuContainer {

  private final List<MenuComponent> menuComponents;
  private final Scanner scanner;

  public Menu() {

    this.menuComponents = new ArrayList<>();
    this.scanner = new Scanner(System.in);

  }


  public void addMenuComponent(MenuComponent menuComponent) {
    menuComponents.add(menuComponent);
  }

  public void removeMenuComponent(MenuComponent menuComponent) {
    menuComponents.remove(menuComponent);
  }

  @Override
  public List<MenuComponent> getMenuComponents() {
    return menuComponents;
  }

  public void display() {
    for (int i = 0; i < menuComponents.size(); i++) {
      MenuComponent menuComponent = menuComponents.get(i);
      System.out.print(" " + (i + 1) + ". ");
      menuComponent.display();

    }
  }

  public void execute() {
    while (true) {
      try {
        display();

        System.out.println(" 0. Quitter");

        // Get user input
        System.out.print("Choix: ");
        int option = scanner.nextInt();

        // Exit if the user chooses '0'
        if (option == 0) {
          return;
        }

        // Execute the selected option
        if (option > 0 && option <= menuComponents.size()) {
          menuComponents.get(option - 1).execute();

        } else {
          System.out.println("Option invalide. Veuillez réessayer.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Entrée invalide. Veuillez saisir un nombre non-négatif.");
                scanner.nextLine();

      }
    }

  }
}