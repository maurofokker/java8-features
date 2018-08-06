package com.maurofokker.java8.imperativevsdeclarative;

import java.util.stream.IntStream;

public class SumNumbersInRange {

    public static void main(String[] args) {
        // sum of integers for the range from 0 to 100
        /**
         * Imperative Style - how style of programming
         * - shared mutable state
         * - it is sequential and it will go step by step
         * - it will have issues if it run in a multithreaded env
         */
        int sum=0;
        for(int i=0;i<=100;i++){
            sum+=i;
        }
        System.out.println("Imperative sum is : "+sum);


        /**
         * Declarative style - what style of programming.
         * - functional programming uses the same style
         * - let the system do the job for you and get the result
         * - can run in multithreaded env
         */
        int sum1= IntStream.rangeClosed(0,100)
                //.parallel()
                .map(Integer::new)
                .sum();

        System.out.println("Declarative sum is : " + sum1);

    }

}
