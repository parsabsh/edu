package controller;

import model.Course;
import model.Student;
import model.Teacher;
import view.LoginMenu;
import view.MainMenu;
import view.StudentMenu;
import view.TeacherMenu;

public class Controller {
    private Student loggedInStudent;
    private Teacher loggedInTeacher;
    private final LoginMenu loginMenu;
    private final MainMenu mainMenu;
    private final TeacherMenu teacherMenu;
    private final StudentMenu studentMenu;

    public Controller() {
        loginMenu = new LoginMenu(this);
        mainMenu = new MainMenu(this);
        teacherMenu = new TeacherMenu(this);
        studentMenu = new StudentMenu(this);
    }

    public boolean isLoggedInUserStudent() {
        return loggedInStudent != null;
    }

    public void run() {
        if (loginMenu.run().equals("exit")) return;
        while (true) {
            switch (mainMenu.run()) {
                case "teacher menu":
                    teacherMenu.run();
                    break;
                case "student menu":
                    studentMenu.run();
                    break;
                case "logout":
                    if (loginMenu.run().equals("exit")) return;
                    break;
            }
        }
    }

    public String register(String username, String password, String role) {
        if (Teacher.getTeacherByUsername(username) != null || Student.getStudentByUsername(username) != null)
            return "register failed: username already exists";

        if (password.length() < 5 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*[0-9].*"))
            return "register failed: password is weak";

        if (role.equals("teacher"))
            Teacher.addTeacher(username, password);
        else if (role.equals("student"))
            Student.addStudent(username, password);
        else
            return "register failed: invalid role!";
        return "register successful";
    }

    public String login(String username, String password) {
        if ((loggedInTeacher = Teacher.getTeacherByUsername(username)) != null) {
            if (!loggedInTeacher.isPasswordCorrect(password))
                return "login failed: incorrect password!";
            return "login successful";
        } else if ((loggedInStudent = Student.getStudentByUsername(username)) != null) {
            if (!loggedInStudent.isPasswordCorrect(password))
                return "login failed: incorrect password!";
            return "login successful";
        } else
            return "login failed: user not found!";
    }

    public String logout() {
        loggedInStudent = null;
        loggedInTeacher = null;
        return "logout successful";
    }

    public String addCourse(String name, int capacity) {
        loggedInTeacher.addCourse(name, capacity);
        return "course add successful";
    }

    public String showMyCourses() {
        StringBuilder res = new StringBuilder();
        for (Course course : this.loggedInStudent.getCourses()) {
            res.append(course.toString()).append('\n');
        }
        return res.toString();
    }

    public String showAllCourses() {
        StringBuilder res = new StringBuilder();
        for (Teacher teacher : Teacher.getTeachers()) {
            for (Course course : teacher.getCourses()) {
                res.append(course.toString()).append('\n');
            }
        }
        return res.toString();
    }

    public String takeCourse(String name) {
        Course course = Course.getCourseByName(name);
        if (course == null)
            return "take course failed: course not found!";

        if (course.isFull())
            return "take course failed: full!";

        if (loggedInStudent.getCourses().contains(course))
            return "take course failed: you've already taken " + course.getName();

        loggedInStudent.takeCourse(course);
        return "take course successful";
    }
}
