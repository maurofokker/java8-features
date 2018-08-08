package com.maurofokker.java8.functionalinterfaces.operators;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class UnaryOperatorDemo {

    static UnaryOperator<String> toUpperCaseUnaryOperator = (s -> s.toUpperCase());

    public static void main(String[] args) {

        List<String> names = Arrays.asList("John", "Jane", "Freddy");
        names.forEach(name -> System.out.println(toUpperCaseUnaryOperator.apply(name)));

    }

}
