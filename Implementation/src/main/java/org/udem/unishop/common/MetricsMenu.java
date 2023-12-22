package org.udem.unishop.common;

import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SubMenu;

public class MetricsMenu extends SubMenu {

  public final User currentUser;

  public final UserController userController;

  public MetricsMenu(User currentUser, UserController userController) {
    super("Mes métriques");
    this.currentUser = currentUser;
    this.userController = userController;

    initializeMenu();
  }

  private void initializeMenu() {
    Command createChooseMetricsCommand = createChooseMetricsCommand();
    addMenuComponent(new MenuItem(createChooseMetricsCommand));
  }

  private Command createChooseMetricsCommand() {

    return new Command() {
      @Override
      public String getName() {
        return "Modifier mes métriques";
      }

      @Override
      public void execute() {
        MetricsOptionsMenu metricsOptionsMenu = new MetricsOptionsMenu(currentUser, userController);
        metricsOptionsMenu.run();
      }
    };
  }

}