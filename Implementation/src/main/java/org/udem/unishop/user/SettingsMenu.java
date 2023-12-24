package org.udem.unishop.user;

import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SettingType;
import org.udem.unishop.utilities.SubMenu;

/**
 * Represents a menu for modifying user profile settings, such as password, email, address, and phone number.
 */
public class SettingsMenu {

    private final SubMenu settingsMenu;
    private final UserController userController;
    private final User currentUser;

    /**
     * Constructor for SettingsMenu instance.
     *
     * @param userController The UserController responsible for managing user-related actions.
     * @param currentUser The current user for whom the settings are being modified.
     */
    public SettingsMenu(UserController userController, User currentUser) {
        this.userController = userController;
        this.currentUser = currentUser;
        this.settingsMenu = createSettingsMenu();
    }

    /**
     * Creates the settings menu with options for changing password, email, address, and phone number.
     *
     * @return The created settings menu.
     */
    private SubMenu createSettingsMenu() {
        SubMenu settingsMenu = new SubMenu("Modifier son profil");
        settingsMenu.addMenuComponent(new MenuItem(createChangePasswordCommand()));
        settingsMenu.addMenuComponent(new MenuItem(createChangeEmailCommand()));
        settingsMenu.addMenuComponent(new MenuItem(createChangeAddressCommand()));
        settingsMenu.addMenuComponent(new MenuItem(createChangePhoneNumberCommand()));


        return settingsMenu;
    }

    /**
     * Creates a command for changing the user's password.
     *
     * @return The command for changing the password.
     */
    private Command createChangePasswordCommand() {
      SettingPrompt settingPrompt = new SettingPrompt();
        return new Command() {
            @Override
            public String getName() {
                return "Modifier son mot de passe";
            }

            @Override
            public void execute() {
                String input = settingPrompt.createSettingPrompt(SettingType.PASSWORD).getValuesFromUser().get(0);
                userController.setPassword(currentUser.getId(), input);
            }
        };
    }

    /**
     * Creates a command for changing the user's email.
     *
     * @return The command for changing the email.
     */
    private Command createChangeEmailCommand() {
      SettingPrompt settingPrompt = new SettingPrompt();
        return new Command() {
            @Override
            public String getName() {
                return "Modifier son courriel";
            }

            @Override
            public void execute() {
                String input = settingPrompt.createSettingPrompt(SettingType.EMAIL).getValuesFromUser().get(0);
                userController.setEmail(currentUser.getId(), input);
            }
        };
    }

    /**
     * Creates a command for changing the user's address.
     *
     * @return The command for changing the address.
     */
    private Command createChangeAddressCommand() {
      SettingPrompt settingPrompt = new SettingPrompt();
        return new Command() {
            @Override
            public String getName() {
                return "Modifier son adresse";
            }

            @Override
            public void execute() {
                String input = settingPrompt.createSettingPrompt(SettingType.ADDRESS).getValuesFromUser().get(0);
                userController.setAddress(currentUser.getId(), input);
            }
        };
    }

    /**
     * Creates a command for changing the user's phone number.
     *
     * @return The command for changing the phone number.
     */
    private Command createChangePhoneNumberCommand() {
      SettingPrompt settingPrompt = new SettingPrompt();
        return new Command() {

            @Override
            public String getName() {
                return "Modifier son numéro de téléphone";
            }

            @Override
            public void execute() {
                String input = settingPrompt.createSettingPrompt(SettingType.PHONE_NUMBER).getValuesFromUser().get(0);
                userController.setPhoneNumber(currentUser.getId(), input);
            }
        };
    }


    /**
     * Executes the settings menu, allowing the user to choose and perform specific settings modifications.
     */
    public void run() {
        settingsMenu.execute();
    }
}