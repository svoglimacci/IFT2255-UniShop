package org.udem.unishop.user;

import org.udem.unishop.controllers.UserController;
import org.udem.unishop.models.User;
import org.udem.unishop.utilities.Command;
import org.udem.unishop.utilities.MenuItem;
import org.udem.unishop.utilities.SettingType;
import org.udem.unishop.utilities.SubMenu;

public class SettingsMenu {

    private final SubMenu settingsMenu;
    private final UserController userController;
    private final User currentUser;

    public SettingsMenu(UserController userController, User currentUser) {
        this.userController = userController;
        this.currentUser = currentUser;
        this.settingsMenu = createSettingsMenu();
    }

    private SubMenu createSettingsMenu() {
        SubMenu settingsMenu = new SubMenu("Modifier son profil");
        settingsMenu.addMenuComponent(new MenuItem(createChangePasswordCommand()));
        settingsMenu.addMenuComponent(new MenuItem(createChangeEmailCommand()));
        settingsMenu.addMenuComponent(new MenuItem(createChangeAddressCommand()));
        settingsMenu.addMenuComponent(new MenuItem(createChangePhoneNumberCommand()));


        return settingsMenu;
    }

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



    public void run() {
        settingsMenu.execute();
    }
}