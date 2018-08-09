package com.maurofokker.java8.streams;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamMapDemo {

    public static void main(String[] args) {
        System.out.println(studentsNames());
        System.out.println("---------------------");
        System.out.println(studentsNamesSet());
    }

    static List<String> studentsNames() {

        List<String> studentsName = StudentDataBase.getAllStudents()
                                .stream()                       // Stream<Student>
                                // Function input Student -> Function output String (getName())
                                // Stream<String> map(Function<Student, String> mapper);
                                .map(Student::getName)          // Stream<String>
                                .collect(Collectors.toList())   // List<String>
        ;

        return studentsName;
    }

    static Set<String> studentsNamesSet() {

        Set<String> studentsName = StudentDataBase.getAllStudents()
                .stream()                       // Stream<Student>
                // Function input Student -> Function output String (getName())
                // Stream<String> map(Function<Student, String> mapper);
                .map(Student::getName)          // Stream<String>
                .map(String::toUpperCase)       // Stream<String> -> uppercase operation on each input
                .collect(Collectors.toSet())   // Set<String>
                ;

        return studentsName;
    }

}
