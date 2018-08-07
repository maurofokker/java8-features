package com.maurofokker.java8.lambdas;

import java.util.Comparator;

public class ComparatorLamdaImpl {

    public static void main(String[] args) {

        /**
         * Comparator implementation before java 8
         * - anonymous class
         * - comparator returns
         *      0 -> if both are equal
         *      1 -> if o1 > o2
         *      -1 -> if o1<o2
         */
        Comparator<Integer> comparator  = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        System.out.println(comparator.compare(1,2));

        /**
         * Comparator with java 8 lambda syntax
         */
        Comparator<Integer> comparatorLambda = (Integer  a,Integer b) -> a.compareTo(b);
        System.out.println("Comparator lambda single statement: " + comparatorLambda.compare(1,2));

        Comparator<Integer> comparatorLambdaNaturalOrder = Comparator.comparing(Integer::intValue);
        System.out.println("Comparator lambda using natural order: " + comparatorLambdaNaturalOrder.compare(1,2));

        /**
         * Comparator chaining happens only when the first comparators result is zero.
         * For example, to sort a collection of {@code String} based on the length
         * and then case-insensitive natural ordering, the comparator can be
         * composed using following code,
         *   Comparator<String> cmp = Comparator.comparingInt(String::length)
         *                                  .thenComparing(String.CASE_INSENSITIVE_ORDER);
         */
        Comparator<Integer> comparatorLambda1 = (a, b) -> a.compareTo(b);
        System.out.println("Result of chaining comparator is with equal inputs : " + comparatorLambda1.thenComparing(comparatorLambda1).compare(2,2));

        System.out.println("Result of chaining comparator is with not equal inputs: " + comparatorLambda1.thenComparing(comparatorLambda1).compare(3,2));


        System.out.println("The greatest integer is : " + compareTwoIntegers(comparatorLambda,1,2));

    }

    public static int compareTwoIntegers(Comparator<Integer> comparator, int a, int b){
        return comparator.compare(a,b) <= 0 ? b : a;
    }

}
