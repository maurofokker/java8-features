package com.maurofokker.java8.streams;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFlatMapDemo {

    public static void main(String[] args) {
        System.out.println(studentsActivities());
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


}
