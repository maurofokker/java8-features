package com.maurofokker.java8.functionalinterfaces.function;

import java.util.function.Function;

public class FunctionMethodReferenceDemo {

    // using lambda
    static Function<String, String> toUpperCaseLambda = (s) -> s.toUpperCase();
    // using method reference - ClassName::instance-methodName
    static Function<String, String> toUpperCaseMethodReference = String::toUpperCase;

    public static void main(String[] args) {

        System.out.println("Function using lambda expression -> " + toUpperCaseLambda.apply("hello, world"));

        System.out.println("Function using method reference -> " + toUpperCaseMethodReference.apply("hello, world"));

    }

}
