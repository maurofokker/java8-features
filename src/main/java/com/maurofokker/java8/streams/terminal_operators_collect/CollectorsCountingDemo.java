package com.maurofokker.java8.streams.terminal_operators_collect;

import com.maurofokker.java8.data.StudentDataBase;

import java.util.stream.Collectors;

public class CollectorsCountingDemo {

    public static void main(String[] args) {

        System.out.println("Total number of students using Collectors.counting() method is " + counting());
    }

    public static long counting() {
        return StudentDataBase.getAllStudents()
                .stream()
                .filter(student -> student.getGender().equalsIgnoreCase("female"))
                .collect(Collectors.counting())
                ;
    }
}
