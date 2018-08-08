package com.maurofokker.java8.functionalinterfaces.function;

import com.maurofokker.java8.data.Student;

import java.util.function.Function;

public class FunctionConstructorReferenceDemo {

    // it needs a constructor with 1 argument or it will complain
    static Function<String, Student> studentFromNameFunction = Student::new;

    public static void main(String[] args) {
        System.out.println(studentFromNameFunction.apply("John"));
    }
}
