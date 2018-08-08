package com.maurofokker.java8.functionalinterfaces.operators;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorDemo {

    static BinaryOperator<Integer> multiplyBinaryOperator = ((a, b) -> a * b);

    // to test minBay and maxBy static methods of BinaryOperator a Comparator must be declare bc is the argument they accept
    static Comparator<Integer> compareIntegers = (a, b) -> a.compareTo(b);

    public static void main(String[] args) {

        System.out.println("Result of multiplying with binary operator: " + multiplyBinaryOperator.apply(4,8));

        // minBy
        BinaryOperator<Integer> min = BinaryOperator.minBy(compareIntegers);
        System.out.println("Min between 3 and 4 is -> " + min.apply(3, 4) );

        // maxBy
        BinaryOperator<Integer> max = BinaryOperator.maxBy(compareIntegers);
        System.out.println("Max between 3 and 4 is -> " + max.apply(3, 4) );

    }

}
