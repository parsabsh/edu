package model;

import java.util.ArrayList;

public class Teacher {
    private static final ArrayList<Teacher> teachers = new ArrayList<>();
    private final String username;
    private final String password;
    private final ArrayList<Course> courses;

    public Teacher(String username, String password) {
        this.username = username;
        this.password = password;
        this.courses = new ArrayList<>();
    }

    public static Teacher getTeacherByUsername(String username) {
        for (Teacher teacher : teachers) {
            if (teacher.getUsername().equals(username))
                return teacher;
        }
        return null;
    }

    public static void addTeacher(String username, String password) {
        teachers.add(new Teacher(username, password));
    }

    public void addCourse(String name, int capacity) {
        this.courses.add(new Course(name, capacity, this));
    }

    public static ArrayList<Teacher> getTeachers() {
        return teachers;
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
