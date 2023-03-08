package view;

import controller.Controller;

public class MainMenu {
    private Controller controller;

    public MainMenu(Controller controller) {
        this.controller = controller;
    }

    public String run() {
        String command;

        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.matches("^\\s*teacher\\s+menu\\s*$")) {
                if (controller.isLoggedInUserStudent())
                    System.out.println("access denied!");
                else
                    return "teacher menu";
            } else if (command.matches("^\\s*student\\s+menu\\s*$")) {
                if (!controller.isLoggedInUserStudent())
                    System.out.println("access denied!");
                else
                    return "student menu";
            } else if (command.matches("^\\s*logout\\s*$")) {
                System.out.println(controller.logout());
                return "logout";
            } else
                System.out.println("invalid command!");
        }
    }
}
