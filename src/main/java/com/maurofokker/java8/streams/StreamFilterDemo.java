package com.maurofokker.java8.streams;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilterDemo {

    public static void main(String[] args) {
        System.out.println("---- filter students by gender");
        filterStudentsByGender("female").forEach(System.out::println);
    }

    public static List<Student> filterStudentsByGender(String gender) {
        return StudentDataBase.getAllStudents()
                .stream()
                .filter(student -> student.getGender().equalsIgnoreCase(gender)) // if gender is find then go to the next step in pipeline
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }
}
