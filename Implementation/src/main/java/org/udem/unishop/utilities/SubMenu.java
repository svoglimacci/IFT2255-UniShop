package org.udem.unishop.utilities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a submenu that can contain various menu components.
 */
public class SubMenu implements MenuComponent, MenuContainer {

  private final Scanner scanner = new Scanner(System.in);
  private final String name;
  private final List<MenuComponent> menuComponents;

  /**
   * Constructor of SubMenu.
   *
   * @param name The name of the submenu.
   */
  public SubMenu(String name) {
    this.name = name;
    this.menuComponents = new ArrayList<>();
  }

  /**
   * Adds a menu component to the submenu.
   *
   * @param menuComponent The menu component to be added.
   */
  public void addMenuComponent(MenuComponent menuComponent) {
    menuComponents.add(menuComponent);
  }

  /**
   * Removes a menu component from the submenu.
   *
   * @param menuComponent The menu component to be removed.
   */
  public void removeMenuComponent(MenuComponent menuComponent) {
    menuComponents.remove(menuComponent);
  }

  /**
   * Displays the name of the submenu.
   */
  @Override
  public void display() {
    System.out.println(name);
  }

  /**
   * Gets the list of menu components in the submenu.
   *
   * @return The list of menu components.
   */
  public List<MenuComponent> getMenuComponents() {
    return menuComponents;
  }

  /**
   * Executes the submenu by displaying its options and handling user input.
   */
  @Override
  public void execute() {
    while (true) {
      try {
        for (int i = 0; i < menuComponents.size(); i++) {
          MenuComponent menuComponent = menuComponents.get(i);
          System.out.print(" " + (i + 1) + ". ");
          menuComponent.display();
        }
                  System.out.println(" 0. Retour");

        System.out.print("Choix: ");
        int subOption = scanner.nextInt();

        if (subOption == 0) {

          return;
        }

        if (subOption > 0 && subOption <= menuComponents.size()) {
          menuComponents.get(subOption - 1).execute();
          return;
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