package com.maurofokker.java8.functionalinterfaces.suppliers;

import com.google.common.util.concurrent.Uninterruptibles;
import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class SupplierDemo {

    // supplier of student
    public static  Supplier<Student> studentSupplier = () -> {
        return  new Student("Adam",2,4.0,"male", Arrays.asList("swimming", "basketball","volleyball"));
    };

    // supplier of list of students
    public static Supplier<List<Student>> studentsSupplier = () -> StudentDataBase.getAllStudents();

    public static void main(String[] args) {

        Student student = studentSupplier.get();
        System.out.println("Student is : " + student);

        System.out.println("Students are : " + studentsSupplier.get());


        // supplier implementation where the generation of valuo takes a considerable amount of time
        // to simulate a heavy process for generation it uses sleepUninterruptibly method from GUAVA
        Supplier<Double> lazyValue = () -> {
            Uninterruptibles.sleepUninterruptibly(1000, TimeUnit.MILLISECONDS);
            return 9d;
        };

        Double valueSquared = squareLazy(lazyValue);
        System.out.println("Square is "+ valueSquared);
    }

    // define a function that squares a double value
    // It will receive not a value itself, but a Supplier of this value
    public static double squareLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 2);
    }
}
