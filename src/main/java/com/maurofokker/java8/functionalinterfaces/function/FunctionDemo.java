package com.maurofokker.java8.functionalinterfaces.function;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;
import com.maurofokker.java8.functionalinterfaces.predicates.PredicateDemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class FunctionDemo {

    // T t: input type String, R r: output type String but uppercase
    static Function<String, String> takesNameAndReturnNameInUpperCase = (name) -> name.toUpperCase();
    static Function<String, String> prefixGreeting = (name) -> "Hello, ".concat(name.toLowerCase());

    // using students db
    // T t: input type List<Student>; R r: output map of key String (name of student) and value Double (gpa of student)
    static Function<List<Student>, Map<String, Double>> studentGpaMapFromStudentsListFunction = (students) -> {
        Map<String, Double> studentGpaMap = new HashMap<>();
        students.forEach(student -> {
            // apply a filter of students that grade level is >= 3 || reuse of predicate in PredicateDemo
            if (PredicateDemo.studentPredicateGradeLevelGEThan3.test(student)) {
                studentGpaMap.put(student.getName(), student.getGpa());
            }
        });
        return studentGpaMap;
    };

    public static void main(String[] args) {

        System.out.println("Result of apply to function : " + takesNameAndReturnNameInUpperCase.apply("john"));

        System.out.println("Result of _andThen_ default method : " + takesNameAndReturnNameInUpperCase.andThen(prefixGreeting).apply("John"));

        System.out.println("Result of _compose_ default method : " + takesNameAndReturnNameInUpperCase.compose(prefixGreeting).apply("John"));

        // students
        System.out.println("Students GPA: " + studentGpaMapFromStudentsListFunction.apply(StudentDataBase.getAllStudents()));
    }

}
