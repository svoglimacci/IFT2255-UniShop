package org.udem.unishop.utilities;


public class MenuItem implements MenuComponent {

  private String name;
  private final Command command;


  public MenuItem(Command command) {
    this.command = command;
    this.name = command.getName();
  }

  @Override
  public void display() {
    System.out.println(name);
  }

  public void execute() {

    command.execute();
    name = command.getName();
  }
}