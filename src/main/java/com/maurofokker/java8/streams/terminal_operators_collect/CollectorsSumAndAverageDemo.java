package com.maurofokker.java8.streams.terminal_operators_collect;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.stream.Collectors;

public class CollectorsSumAndAverageDemo {

    public static void main(String[] args) {
        System.out.println("SummingInt total of notebooks " + sumOfNotebooks());

        System.out.println("averagingInt of notebooks " + averageOfNotebooks());
    }

    public static int sumOfNotebooks() {
        return StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.summingInt(Student::getNoteBooks))
                ;
    }

    public static double averageOfNotebooks() {
        return StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.averagingInt(Student::getNoteBooks))
                ;
    }
}
