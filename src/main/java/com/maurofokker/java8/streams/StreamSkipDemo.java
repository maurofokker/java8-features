package com.maurofokker.java8.streams;

import java.util.Arrays;
import java.util.Optional;

public class StreamSkipDemo {

    public static void main(String[] args) {

        Optional<Integer> sumLastThirdNumbersInList = Arrays.asList(23,43,56,97,32)
                .stream()
                .skip(2)
                .reduce((a,b)-> a+b);
        System.out.println("sumOf2FirstNumberInList " + (sumLastThirdNumbersInList.isPresent() ? sumLastThirdNumbersInList.get() : ""));

        Optional<Integer> sumThirdAndFourthNumbersInList = Arrays.asList(23,43,56,97,32)
                .stream()
                .skip(2)
                .limit(2)
                .reduce((a,b)-> a+b);
        System.out.println("sumOf2FirstNumberInList " + (sumThirdAndFourthNumbersInList.isPresent() ? sumThirdAndFourthNumbersInList.get() : ""));

    }

}
