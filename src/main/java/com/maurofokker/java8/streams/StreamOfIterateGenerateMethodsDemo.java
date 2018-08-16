package com.maurofokker.java8.streams;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamOfIterateGenerateMethodsDemo {

    public static void main(String[] args) {

        // of()
        Stream<String> streamOfStrings = Stream.of("Hugo", "Paco", "Luis");
        streamOfStrings.forEach(System.out::println);

        // iterate()
        // initial value is 1, and is multiplied by 2
        // x is previous value (obtained in the multiplication)
        Stream.iterate(1, x -> x*2)   // infinite iteration
                .limit(10)                  // limit the iteration
                .forEach(System.out::println);

        // generate()
        // create a Supplier to generate random values
        Supplier<Integer> integerSupplier = new Random()::nextInt;
        Stream.generate(integerSupplier)
                .limit(10)
                .forEach(System.out::println);
    }
}
