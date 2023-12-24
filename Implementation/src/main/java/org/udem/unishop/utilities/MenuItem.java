package org.udem.unishop.utilities;

/**
 * Represents a menu item that is associated with a command.
 */
public class MenuItem implements MenuComponent {

  private String name;
  private final Command command;

  /**
   * Constructs a new MenuItem with the specified command.
   *
   * @param command the command associated with the menu item.
   */
  public MenuItem(Command command) {
    this.command = command;
    this.name = command.getName();
  }

  /**
   * Displays the name of the menu item.
   */
  @Override
  public void display() {
    System.out.println(name);
  }

  /**
   * Executes the command associated to the menu item and updates the name.
   */
  public void execute() {

    command.execute();
    name = command.getName();
  }
}