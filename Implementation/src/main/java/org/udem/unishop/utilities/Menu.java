package org.udem.unishop.utilities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a menu that contains various menu components.
 */
public class Menu implements MenuContainer {

  private final List<MenuComponent> menuComponents;
  private final Scanner scanner;

  /**
   * Constructs a new Menu with an empty list of menu components.
   */
  public Menu() {

    this.menuComponents = new ArrayList<>();
    this.scanner = new Scanner(System.in);

  }

  /**
   * Adds a menu component to the menu.
   *
   * @param menuComponent The menu component to be added.
   */
  public void addMenuComponent(MenuComponent menuComponent) {
    menuComponents.add(menuComponent);
  }

  /**
   * Removes a menu component from the menu.
   *
   * @param menuComponent The menu component to be removed.
   */
  public void removeMenuComponent(MenuComponent menuComponent) {
    menuComponents.remove(menuComponent);
  }

  /**
   * Gets the list of menu components in the menu.
   *
   * @return The list of menu components.
   */
  @Override
  public List<MenuComponent> getMenuComponents() {
    return menuComponents;
  }

  /**
   * Displays the menu options to the user.
   */
  public void display() {
    for (int i = 0; i < menuComponents.size(); i++) {
      MenuComponent menuComponent = menuComponents.get(i);
      System.out.print(" " + (i + 1) + ". ");
      menuComponent.display();

    }
  }

  /**
   * Executes the menu by repeatedly displaying options and processing user input.
   * Exits when the user chooses '0'.
   */
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