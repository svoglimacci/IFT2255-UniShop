package org.udem.unishop.utilities;

import java.util.List;

public interface MenuContainer {

  void addMenuComponent(MenuComponent menuComponent);

  void removeMenuComponent(MenuComponent menuComponent);

  List<MenuComponent> getMenuComponents();
}