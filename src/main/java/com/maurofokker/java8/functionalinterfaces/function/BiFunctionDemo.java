package com.maurofokker.java8.functionalinterfaces.function;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;
import com.maurofokker.java8.functionalinterfaces.predicates.PredicateDemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class BiFunctionDemo {

    // input: List<Student> and Predicate<Student>
    // output: Map<String, Double>
    static BiFunction<List<Student>, Predicate<Student>, Map<String, Double>> studentGpaMapFromStudentsListAndPredicateBiFunction = ((students, studentPredicate) -> {
        Map<String, Double> studentGpaMap = new HashMap<>();
        students.forEach(student -> {
            if (studentPredicate.test(student)) {
                studentGpaMap.put(student.getName(), student.getGpa());
            }
        });
        return studentGpaMap;
    });

    public static void main(String[] args) {
        System.out.println(studentGpaMapFromStudentsListAndPredicateBiFunction.apply(StudentDataBase.getAllStudents(), PredicateDemo.studentPredicateGradeLevelGEThan3));
        System.out.println(studentGpaMapFromStudentsListAndPredicateBiFunction.apply(StudentDataBase.getAllStudents(), PredicateDemo.studentPredicateGPAisGreaterThan3point9));
    }

}
