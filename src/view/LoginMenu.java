package view;

import controller.Controller;

import java.util.regex.Matcher;

public class LoginMenu {
    private Controller controller;

    public LoginMenu(Controller controller) {
        this.controller = controller;
    }

    public String run() {
        Matcher matcher;
        String command, result;

        while (true) {
            command = Menu.getScanner().nextLine();

            if (command.matches("^\\s*exit\\s*$"))
                return "exit";

            if ((matcher = Menu.getMatcher(command, "^\\s*register\\s+(?<role>\\S+)\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$")) != null)
                System.out.println(controller.register(matcher.group("username"), matcher.group("password"), matcher.group("role")));
            else if ((matcher = Menu.getMatcher(command, "^\\s*login\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$")) != null) {
                result = controller.login(matcher.group("username"), matcher.group("password"));
                System.out.println(result);
                if (result.equals("login successful"))
                    return "Logged in";
            } else {
                System.out.println("invalid command!");
            }
        }
    }
}
