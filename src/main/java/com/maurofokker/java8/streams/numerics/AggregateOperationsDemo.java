package com.maurofokker.java8.streams.numerics;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class AggregateOperationsDemo {

    public static void main(String[] args) {

        // sum()

        System.out.println("IntStream Sum of 1 to 50 inclusive = " + IntStream.rangeClosed(1, 50).sum());

        System.out.println("LongStream Sum of 1 to 50 inclusive = " + LongStream.rangeClosed(1, 50).sum());

        System.out.println("DoubleStream Sum of 1 to 50 inclusive = " + LongStream.rangeClosed(1, 50).asDoubleStream().sum());

        // min()
        // bc it returns Optional[Int|Long|Double] it is better to evaluate with isPresent() method and then use .getAsXXX()

        System.out.println("IntStream min of 1 to 50 inclusive = " + IntStream.rangeClosed(1, 50).min().getAsInt());
        System.out.println("LongStream min of 1 to 50 inclusive = " + LongStream.rangeClosed(1, 50).min().getAsLong());
        System.out.println("DoubleStream min of 1 to 50 inclusive = " + IntStream.rangeClosed(1, 50).asDoubleStream().min().getAsDouble());

        // max()
        // bc it returns Optional[Int|Long|Double] it is better to evaluate with isPresent() method and then use .getAsXXX()

        System.out.println("IntStream max of 1 to 50 inclusive = " + IntStream.rangeClosed(1, 50).max().getAsInt());
        System.out.println("LongStream max of 1 to 50 inclusive = " + LongStream.rangeClosed(1, 50).max().getAsLong());
        System.out.println("DoubleStream max of 1 to 50 inclusive = " + IntStream.rangeClosed(1, 50).asDoubleStream().max().getAsDouble());

        // average()
        // all three api returns an OptionalDouble it is better to evaluate with isPresent() and then use .getAsDouble()

        System.out.println("IntStream average of 1 to 50 inclusive = " + IntStream.rangeClosed(1, 50).average().getAsDouble());
        System.out.println("LongStream average of 1 to 50 inclusive = " + LongStream.rangeClosed(1, 50).average().getAsDouble());
        System.out.println("DoubleStream average of 1 to 50 inclusive = " + IntStream.rangeClosed(1, 50).asDoubleStream().average().getAsDouble());
    }

}
