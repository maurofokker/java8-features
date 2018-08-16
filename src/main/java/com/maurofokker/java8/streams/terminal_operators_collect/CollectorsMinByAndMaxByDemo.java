package com.maurofokker.java8.streams.terminal_operators_collect;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectorsMinByAndMaxByDemo {

    public static void main(String[] args) {
        Optional<Student> studentWithMinGpa = minByGpa();
        if (studentWithMinGpa.isPresent())
            System.out.println("Student with min gpa is " + minByGpa().get());

        Optional<Student> studentWithMaxGpa = maxByGpa();
        if (studentWithMaxGpa.isPresent())
            System.out.println("Student with min gpa is " + studentWithMaxGpa.get());
    }

    public static Optional<Student> minByGpa() {
        return StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.minBy(Comparator.comparing(Student::getGpa)))
                ;
    }

    public static Optional<Student> maxByGpa() {
        return StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.maxBy(Comparator.comparing(Student::getGpa)))
                ;
    }
}
