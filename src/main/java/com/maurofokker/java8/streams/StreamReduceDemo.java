package com.maurofokker.java8.streams;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamReduceDemo {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(23,43,56,97,32);
        System.out.println("---- integer multiplication with reduce identity and acumulator ----");
        System.out.println(performMultiplication(integers));
        System.out.println("---- integer multiplication with reduce withot identity ----");
        Optional<Integer> result = performMultiplicationWithoutIdentity(integers);
        System.out.println(result.isPresent()); // return boolean
        System.out.println(result.get()); // return actual value
        System.out.println("---- max value ----");
        System.out.println(maxValueInList(integers).get());
        System.out.println("---- min value ----");
        System.out.println(minValueInList(integers).get());
        System.out.println(" ---- highest gpa student ----");
        Optional<Student> student = getHighestGpaStudent();
        if (student.isPresent())
            System.out.println(student.get());
    }

    public static final int performMultiplication(List<Integer> integers) {
        return integers.stream()
                // 23
                // 43
                // 56
                // 97
                // 32
                // a=1, b=23 => result 23
                // a=23, b=43 => r = 989
                // a= previous result, b=next in stream
                // output 171911936
                .reduce(1, (a,b) -> a*b)
                // reduce(T identity, BinaryOperator<T> accumulator);`
                ;
    }

    public static final Optional<Integer> performMultiplicationWithoutIdentity(List<Integer> integers) {
        return integers.stream()
                .reduce((a,b) -> a*b)
                // Optional<T> reduce(BinaryOperator<T> accumulator);`
                ;
    }

    public static final Optional<Integer> maxValueInList(List<Integer> integers) {
        return integers.stream()
                // using T reduce(T identity, BinaryOperator<T> accumulator)
                // values in stream take variable 'b' and 'a' the max value in the iteration
                //.reduce(0, (a, b) -> a > b ? a : b)  // for empty list will return 0 that is wrong
                // using Optional<T> reduce(BinaryOperator<T> accumulator)
                .reduce(Integer::max)
                ;
    }

    public static final Optional<Integer> minValueInList(List<Integer> integers) {
        return integers.stream()
                // using Optional<T> reduce(BinaryOperator<T> accumulator)
                //.reduce((a, b) -> a < b ? a : b)
                .reduce(Integer::min)
                ;
    }

    public static Optional<Student> getHighestGpaStudent() {
        return StudentDataBase.getAllStudents()
                .stream()
                .reduce((student, student2) -> student.getGpa() > student2.getGpa() ? student : student2)
                ;
    }

}
