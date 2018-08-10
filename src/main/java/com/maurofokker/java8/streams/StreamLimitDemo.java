package com.maurofokker.java8.streams;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamLimitDemo {

    public static void main(String[] args) {
        List<Student> students = StudentDataBase.getAllStudents()
                .stream()
                .limit(2)
                .collect(Collectors.toList());
        students.forEach(System.out::println);

        Optional<Integer> sumOf2FirstNumberInList = Arrays.asList(23,43,56,97,32)
                                .stream()
                                        .limit(2)
                                        .reduce((a,b)-> a+b);
        System.out.println("sumOf2FirstNumberInList " + (sumOf2FirstNumberInList.isPresent() ? sumOf2FirstNumberInList.get() : ""));
    }

}
