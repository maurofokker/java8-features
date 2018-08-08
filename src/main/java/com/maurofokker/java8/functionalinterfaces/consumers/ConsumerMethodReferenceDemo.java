package com.maurofokker.java8.functionalinterfaces.consumers;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.function.Consumer;

public class ConsumerMethodReferenceDemo {

    // lambda expression
    // Consumer<String> printStudent = (s -> System.out.println(s));
    // method reference ClassName::instance-method
    static Consumer<Student> printStudent = System.out::println;

    // lambda expression
    // static Consumer<Student> printListActivitiesConsumenr = student -> student.printActivities();
    // instance::instance-method
    static Consumer<Student> printListActivitiesConsumenr = Student::printActivities;

    public static void main(String[] args) {

        StudentDataBase.getAllStudents().forEach(printStudent);

        StudentDataBase.getAllStudents().forEach(printListActivitiesConsumenr);

    }

}
