package model;

import java.util.ArrayList;

public class Student {
    private static final ArrayList<Student> students = new ArrayList<>();
    private final String username;
    private final String password;
    private final ArrayList<Course> courses;

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
        this.courses = new ArrayList<>();
    }

    public static Student getStudentByUsername(String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username))
                return student;
        }
        return null;
    }

    public static void addStudent(String username, String password) {
        students.add(new Student(username, password));
    }

    public void takeCourse(Course course) {
        this.getCourses().add(course);
        course.getStudents().add(this);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
