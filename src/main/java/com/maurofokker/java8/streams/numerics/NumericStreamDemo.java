package com.maurofokker.java8.streams.numerics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class NumericStreamDemo {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6);

        System.out.println("Sum of numbers are : " + sumOfNumbers(integerList));

        System.out.println("Sum of numbers using IntStream are : " + sumOfNumbersIntStream());
    }

    public static int sumOfNumbers(List<Integer> integerList){

        return  integerList.stream() // Stream<Integer>
                .reduce(0,(x,y)->x+y); // unboxing to convert Integer to an int in order to do the sum of numbers

    }

    public static int sumOfNumbersIntStream(){
        return IntStream.rangeClosed(1,6)
                .sum(); // saves the unboxing effort.

    }
}
