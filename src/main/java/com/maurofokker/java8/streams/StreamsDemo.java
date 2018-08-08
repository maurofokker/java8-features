package com.maurofokker.java8.streams;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamsDemo {

    public static void main(String[] args) {

        Predicate<Student> studentGradeLevelGreaterEqualThan3 = student -> student.getGradeLevel() >= 3;

        // map with student name key and student activities as value
        Map<String, List<String>> studentActivitiesMap = StudentDataBase.getAllStudents().stream()
                                                                .peek(student -> System.out.println(student))
                                                                .filter(studentGradeLevelGreaterEqualThan3)
                                                                .collect(Collectors.toMap(Student::getName, Student::getActivities));

        /* compare above code with next one that made almost the same thing
        Function<List<Student>, Map<String, Double>> studentGpaMapFromStudentsListFunction = (students) -> {
        Map<String, Double> studentGpaMap = new HashMap<>();
        students.forEach(student -> {
            // apply a filter of students that grade level is >= 3 || reuse of predicate in PredicateDemo
            if (PredicateDemo.studentPredicateGradeLevelGEThan3.test(student)) {
                studentGpaMap.put(student.getName(), student.getGpa());
            }
        });
        return studentGpaMap;
    };
         */

        System.out.println(studentActivitiesMap);
    }

}
