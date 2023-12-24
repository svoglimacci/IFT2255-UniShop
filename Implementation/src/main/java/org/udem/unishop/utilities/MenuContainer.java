package org.udem.unishop.utilities;

import java.util.List;

/**
 * Represents a container for menu components.
 */
public interface MenuContainer {

  /**
   * Adds a menu component to the container.
   *
   * @param menuComponent The menu component to be added.
   */
  void addMenuComponent(MenuComponent menuComponent);

  /**
   * Removes a menu component from the container.
   *
   * @param menuComponent The menu component to be removed.
   */
  void removeMenuComponent(MenuComponent menuComponent);

  /**
   * Gets a list of menu components in the container.
   *
   * @return The list of menu components.
   */
  List<MenuComponent> getMenuComponents();
}