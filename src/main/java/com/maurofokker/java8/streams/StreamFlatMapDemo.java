package com.maurofokker.java8.streams;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFlatMapDemo {

    public static void main(String[] args) {
        System.out.println(studentsActivities());

        System.out.println("distinct activities");
        System.out.println(uniqueStudentsActivities());
        System.out.println("count of distinct activities = " + countUniqueStudentsActivities());
        System.out.println("----------- sorted students activities -----------");
        System.out.println(sortedStudentsActivities());
        System.out.println("----------- sorted unique students activities -----------");
        System.out.println(sortedUniqueStudentsActivities());
    }

    public static List<String> studentsActivities() {
        return StudentDataBase.getAllStudents()
                .stream()                       // Stream<Student>
                .map(Student::getActivities)    // Stream<List<String>>
                // Stream<String> flatMap(Function<List<Student>, String> mapper);
                .flatMap(List::stream)          // Stream<String>
                .collect(Collectors.toList())   // List<String>
                ;
    }

    public static List<String> sortedStudentsActivities() {
        return StudentDataBase.getAllStudents()
                .stream()                       // Stream<Student>
                .map(Student::getActivities)    // Stream<List<String>>
                // Stream<String> flatMap(Function<List<Student>, String> mapper);
                .flatMap(List::stream)          // Stream<String>
                .sorted()                       // Stream<String>
                .collect(Collectors.toList())   // List<String>
                ;
    }

    public static List<String> uniqueStudentsActivities() {
        return StudentDataBase.getAllStudents()
                .stream()                       // Stream<Student>
                .map(Student::getActivities)    // Stream<List<String>>
                // Stream<String> flatMap(Function<List<Student>, String> mapper);
                .flatMap(List::stream)          // Stream<String>
                .distinct()                     // Stream<String>
                .collect(Collectors.toList())   // List<String>
                ;
    }

    public static List<String> sortedUniqueStudentsActivities() {
        return StudentDataBase.getAllStudents()
                .stream()                       // Stream<Student>
                .map(Student::getActivities)    // Stream<List<String>>
                // Stream<String> flatMap(Function<List<Student>, String> mapper);
                .flatMap(List::stream)          // Stream<String>
                .distinct()                     // Stream<String>
                .sorted()                       // Stream<String>
                .collect(Collectors.toList())   // List<String>
                ;
    }

    public static long countUniqueStudentsActivities() {
        return StudentDataBase.getAllStudents()
                .stream()                       // Stream<Student>
                .map(Student::getActivities)    // Stream<List<String>>
                // Stream<String> flatMap(Function<List<Student>, String> mapper);
                .flatMap(List::stream)          // Stream<String>
                .distinct()                     // Stream<String>
                .count()                        // returns long
                ;
    }

}
