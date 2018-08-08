package com.maurofokker.java8.functionalinterfaces.suppliers;

import com.maurofokker.java8.data.Student;

import java.util.function.Supplier;

public class SupplierConstructorReferenceDemo {

    static Supplier<Student> studentSupplier = Student::new;

    public static void main(String[] args) {
        System.out.println(studentSupplier.get());
    }
}
