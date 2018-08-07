package com.maurofokker.java8.functionalinterfaces;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class BiConsumerDemo {

    public static void main(String[] args) {

        BiConsumer<String, String> biConsumer = (s1, s2) -> System.out.println("s1: " + s1.toUpperCase() + " - s2: " + s2.toUpperCase());
        biConsumer.accept("john", "doe");

        Map<String, String> map = new HashMap<>();
        BiConsumer<String, String> biConsumerMethodReference = map::put;
        biConsumerMethodReference.accept("john", "doe");
        System.out.println("BiConsumer with method reference result for map -> " + map);


        printStudentsNamesAndActivities();

    }

    private static void printStudentsNamesAndActivities() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Printing students names and activities with BiConsumer");
        System.out.println(" ");

        BiConsumer<String, List<String>> namesAndActivitiesOfStudentsBiConsumer = (name, activities) -> {
            System.out.println(name + " - " + activities);
        };
        List<Student> students = StudentDataBase.getAllStudents();
        students.forEach(student -> namesAndActivitiesOfStudentsBiConsumer.accept(student.getName(), student.getActivities()));

    }

}
