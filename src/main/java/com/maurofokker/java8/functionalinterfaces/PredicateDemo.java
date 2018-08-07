package com.maurofokker.java8.functionalinterfaces;

import java.util.function.Predicate;

public class PredicateDemo {

    // even number
    static Predicate<Integer> predicate = (i) -> i%2 == 0;
    // divisible by 5
    static Predicate<Integer> predicate2 = (integer -> integer%5 == 0);

    public static void main(String[] args) {

        // check even number
        System.out.println(predicate.test(8));
        // output: true
        System.out.println(predicate.test(3));
        // output: false

        // using default methods in predicate
        predicateAnd();
        predicateOr();
        predicateNegate();

    }

    private static void predicateAnd() {
        System.out.println("Test predicate _and_ default method");
        System.out.println("");
        System.out.println("check if 15 is even number _and_ divisible by 5: " + predicate.and(predicate2).test(15));
        System.out.println("check if 40 is even number _and_ divisible by 5: " + predicate.and(predicate2).test(40));
    }

    private static void predicateOr() {
        System.out.println("Test predicate _or_ default method");
        System.out.println("");
        System.out.println("check if 15 is even number _or_ divisible by 5: " + predicate.or(predicate2).test(15));
        System.out.println("check if 40 is even number _or_ divisible by 5: " + predicate.or(predicate2).test(40));
        // if first is true then will not evaluate second predicate
    }

    private static void predicateNegate() {
        System.out.println("Test predicate _negate_ default method");
        System.out.println("");
        System.out.println("check if 15 is even number _and_ divisible by 5: " + predicate.and(predicate2).negate().test(15));
        // negate will reverse the result
    }

}
