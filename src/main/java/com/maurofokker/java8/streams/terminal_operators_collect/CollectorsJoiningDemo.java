package com.maurofokker.java8.streams.terminal_operators_collect;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.stream.Collectors;

public class CollectorsJoiningDemo {

    public static void main(String[] args) {
        System.out.println("Using joining terminal operator without parameters : " + joiningStudentsNames());

        System.out.println("Using joining terminal operator with a dash delimiter parameter : " + joiningStudentsNamesWithDelimiter());

        System.out.println("Using joining terminal operator with delimiter, prefix and sufix parameters : " + joiningStudentsNamesWithDelimiterPrefixAndSufix());
    }

    public static String joiningStudentsNames() {
        return StudentDataBase.getAllStudents()
                .stream()  // Stream<Student>
                .map(Student::getName) // Stream<String>
                .collect(Collectors.joining());
    }

    public static String joiningStudentsNamesWithDelimiter() {
        return StudentDataBase.getAllStudents()
                .stream()  // Stream<Student>
                .map(Student::getName) // Stream<String>
                .collect(Collectors.joining("-"));
    }

    public static String joiningStudentsNamesWithDelimiterPrefixAndSufix() {
        return StudentDataBase.getAllStudents()
                .stream()  // Stream<Student>
                .map(Student::getName) // Stream<String>
                .collect(Collectors.joining("-", "(", ")"));
    }
}
