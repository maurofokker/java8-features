package com.maurofokker.java8.streams;

import com.maurofokker.java8.data.StudentDataBase;

import java.util.Arrays;
import java.util.List;

public class StreamMatchDemo {

    public static void main(String[] args) {

        System.out.println("Result of anyMatch : " + anyMatch());
        System.out.println("Result of allMatch : " + allMatch());
        System.out.println("Result of noneMatch : " + noneMatch());

        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 10, 12);
        boolean anyMatch = numbers.stream().anyMatch(x -> x % 2 == 0);
        boolean allMatch = numbers.stream().allMatch(x -> x % 2 == 0);
        boolean noneMatch = numbers.stream().noneMatch(x -> x % 2 == 0);
        System.out.println("Result of anyMatch : " + anyMatch);
        System.out.println("Result of allMatch : " + allMatch);
        System.out.println("Result of noneMatch : " + noneMatch);

    }

    public static boolean anyMatch() {
        return StudentDataBase.getAllStudents()
                .stream()
                .anyMatch(student -> student.getGpa() >= 3.9);
    }

    public static boolean allMatch() {
        return StudentDataBase.getAllStudents().stream()
                .allMatch(student -> student.getGpa()>=3.9);
    }

    public static boolean noneMatch() {
        return StudentDataBase.getAllStudents().stream()
                .noneMatch(student -> student.getGpa()>=3.9);
    }

}
