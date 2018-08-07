package com.maurofokker.java8.functionalinterfaces.predicates;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class BiPredicateDemo {

    public static void main(String[] args) {

        // example of BiPredicate<T, U>
        BiPredicate<Integer, String> ageGreaterThan21AndNameStartsWithJ = (age, name) -> age > 21 && name.startsWith("J");
        System.out.println(ageGreaterThan21AndNameStartsWithJ.test(23, "John")); // true
        System.out.println(ageGreaterThan21AndNameStartsWithJ.test(21, "John")); // false

        // Same example for students in PredicateConsumerDemo.java
        BiPredicate<Integer, Double> studentWithGradeLevelGEthan3AndGpaGEthan3point9Predicate = (gradeLevel, gpa) -> gradeLevel >= 3 && gpa >= 3.9;

        BiConsumer<String, List<String>> studentNameAndActivitiesBiConsumer = (name, activities) -> System.out.println(name + " : " + activities);

        Consumer<Student> studentWithGradeLevelGEthan3AndGpaGEthan3point9Consumer = (student) -> {
            if (studentWithGradeLevelGEthan3AndGpaGEthan3point9Predicate.test(student.getGradeLevel(), student.getGpa())) {
                studentNameAndActivitiesBiConsumer.accept(student.getName(), student.getActivities());
            }
        };

        List<Student> students = StudentDataBase.getAllStudents();
        students.forEach(studentWithGradeLevelGEthan3AndGpaGEthan3point9Consumer);
    }

}
