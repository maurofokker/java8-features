package com.maurofokker.java8.streams.numerics;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapDemo {

    public static void main(String[] args) {

        System.out.println("mapToObj() result -> " + mapToObject());

        System.out.println("mapToLong() to convert IntStream to LongStream and get the sum -> " + mapToLong());

        System.out.println("mapToDouble() to convert IntStream to DoubleStream and get the sum -> " + mapToDouble());
    }

    public static List<Integer> mapToObject() {
        return IntStream.rangeClosed(1, 10)
                .mapToObj(i -> {return new Integer(i);})
                .collect(Collectors.toList());
    }

    public static long mapToLong() {
        return IntStream.rangeClosed(1, 10) // generate IntStream
                // i is passed from the IntStream
                .mapToLong(i -> i) // convert IntStream to LongStream
                .sum();

    }

    public static double mapToDouble() {
        return IntStream.rangeClosed(1, 10) // generate IntStream
                // i is passed from the IntStream
                .mapToDouble(i -> i) // convert IntStream to DoubleStream
                .sum();

    }
}
