package com.maurofokker.java8.streams.numerics;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class RangesDemo {

    public static void main(String[] args) {

        // IntStream

        IntStream intStreamRange = IntStream.range(1, 50);
        System.out.println("IntStream range from 1 to 50, count: " + intStreamRange.count());
        System.out.println("IntStream numbers generated");
        IntStream.range(1, 50).forEach(x -> System.out.print(x + " "));
        System.out.println(" ");
        System.out.println("----------------");
        IntStream intStreamRangeClosed = IntStream.rangeClosed(1, 50);
        System.out.println("IntStream rangeClosed from 1 to 50, count: " + intStreamRangeClosed.count());
        IntStream.rangeClosed(1, 50).forEach(x -> System.out.print(x + " "));

        // LongStream

        System.out.println("LongStream numbers generated");
        System.out.println("LongStream range");
        LongStream.range(1, 50).forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("LongStream rangeClosed");
        LongStream.rangeClosed(1, 50).forEach(x -> System.out.print(x + " "));
        System.out.println();

        // DoubleStream doesn't have range() nor rangeClosed() but there is a way to have Double

        System.out.println("DoubleStream range using LongStream range");
        LongStream.range(1, 50).asDoubleStream().forEach(x -> System.out.print(x + " "));
        System.out.println();
        System.out.println("DoubleStream rangeClosed using LongStream rangeClosed");
        LongStream.rangeClosed(1, 50).asDoubleStream().forEach(x -> System.out.print(x + " "));
    }
}
