package view;

import controller.Controller;

import java.util.regex.Matcher;

public class TeacherMenu {
    private Controller controller;

    public TeacherMenu(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        Matcher matcher;
        String command;

        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.matches("^\\s*back\\s*"))
                return;

            if ((matcher = Menu.getMatcher(command, "^\\s*add\\s+course\\s+(?<name>\\S+)\\s+(?<capacity>\\d+)\\s*$")) != null)
                System.out.println(controller.addCourse(matcher.group("name"), Integer.parseInt(matcher.group("capacity"))));
            else if (command.matches("^\\s*show\\s+all\\s+courses\\s*$"))
                System.out.println(controller.showAllCourses());
            else
                System.out.println("invalid command!");
        }
    }
}
