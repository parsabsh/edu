package model;

import java.util.ArrayList;

public class Course {
    private final String name;
    private final int capacity;
    private final Teacher teacher;
    private final ArrayList<Student> students;

    public Course(String name, int capacity, Teacher teacher) {
        this.name = name;
        this.capacity = capacity;
        this.teacher = teacher;
        students = new ArrayList<>();
    }

    public static Course getCourseByName(String name) {
        for (Teacher teacher : Teacher.getTeachers()) {
            for (Course course : teacher.getCourses()) {
                if (course.getName().equals(name))
                    return course;
            }
        }
        return null;
    }

    public boolean isFull() {
        return students.size() == capacity;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return name + ") Instructor: " + teacher.getUsername() + " | Capacity: " + students.size() + "/" + capacity;
    }
}
