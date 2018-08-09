package com.maurofokker.java8.streams;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSortedWithComparatorDemo {

    public static void main(String[] args) {

        System.out.println("----- sorted by name -----");
        sortStudentsByName().forEach(System.out::println);

        System.out.println("----- sorted by gpa -----");
        sortStudentsByGpa().forEach(System.out::println);

        System.out.println("----- sorted by gpa reversed -----");
        sortStudentsByGpaReversed().forEach(System.out::println);
    }

    public static List<Student> sortStudentsByName() {
        return StudentDataBase.getAllStudents()
                .stream()
                // Comparator<Student> comparing(Function<Student, Student> keyExtractor)
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList())
                ;
    }

    public static List<Student> sortStudentsByGpa() {
        return StudentDataBase.getAllStudents()
                .stream()
                // Comparator<Student> comparing(Function<Student, Student> keyExtractor)
                .sorted(Comparator.comparing(Student::getGpa))
                .collect(Collectors.toList())
                ;
    }

    public static List<Student> sortStudentsByGpaReversed() {
        return StudentDataBase.getAllStudents()
                .stream()
                // Comparator<Student> comparing(Function<Student, Student> keyExtractor)
                .sorted(Comparator.comparing(Student::getGpa).reversed())
                .collect(Collectors.toList())
                ;
    }

}
