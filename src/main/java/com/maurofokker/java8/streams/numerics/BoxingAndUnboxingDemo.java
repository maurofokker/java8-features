package com.maurofokker.java8.streams.numerics;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoxingAndUnboxingDemo {

    public static void main(String[] args) {
        System.out.println("Converting primitive stream to List : "+ boxingInt());

        List<Integer> integers = boxingInt();

        System.out.println("Sum using unboxing : " + unboxingIntegers(integers));
    }

    public static List<Integer> boxingInt() {

        return IntStream.rangeClosed(1,25)
                // int values
                .boxed() // to Stream<Integer>
                // to List of Integer
                .collect(Collectors.toList());

    }

    public static int unboxingIntegers(List<Integer> integerList) {

        int sum =  integerList.stream()
                .mapToInt(Integer::intValue).sum();
        return sum;

    }
}
