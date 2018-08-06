package com.maurofokker.java8.imperativevsdeclarative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RemoveDuplicatesFromList {

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1,2,3,4,4,5,5,6,7,7,8,9,9);

        /**
         * Imperative Style
         */
        List<Integer> uniqueList = new ArrayList<>();
        for(Integer i :integerList)
            if(!uniqueList.contains(i)){
                uniqueList.add(i);
            }
        System.out.println("Imperative result unique List : " + uniqueList);

        /**
         * Declarative Syle
         */

        List<Integer> uniqueList1 = integerList.stream()
                .distinct()
                .collect(toList());
        System.out.println("Declarative result unique list : " + uniqueList1);
    }

}
