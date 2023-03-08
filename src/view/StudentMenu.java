package view;

import controller.Controller;

import java.util.regex.Matcher;

public class StudentMenu {
    private Controller controller;

    public StudentMenu(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        Matcher matcher;
        String command;

        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.matches("^\\s*back\\s*"))
                return;

            if ((matcher = Menu.getMatcher(command, "^\\s*take\\s+course\\s+(?<name>\\S+)\\s*$")) != null)
                System.out.println(controller.takeCourse(matcher.group("name")));
            else if (command.matches("^\\s*show\\s+my\\s+courses\\s*$"))
                System.out.println(controller.showMyCourses());
            else if (command.matches("^\\s*show\\s+all\\s+courses\\s*$"))
                System.out.println(controller.showAllCourses());
            else
                System.out.println("invalid command!");
        }
    }
}
