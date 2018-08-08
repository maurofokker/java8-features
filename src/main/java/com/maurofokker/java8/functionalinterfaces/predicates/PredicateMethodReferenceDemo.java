package com.maurofokker.java8.functionalinterfaces.predicates;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateMethodReferenceDemo {

    static Predicate<Student> predicateUsingLambda = (s) -> s.getGradeLevel()>=3;
    static BiPredicate<Student, Integer> biPredicateUsingLambda = ((student, integer) -> student.getGradeLevel() >= integer);

    static Predicate<Student> predicateUsingMethodReference = PredicateMethodReferenceDemo::gradeLeveGreaterThan3;

    // with method reference ... extracted method from biPredicateUsingLambda with intellij
    static BiPredicate<Student, Integer> biPredicateUsingMethodReference = (PredicateMethodReferenceDemo::gradeLevelGreaterThan);

    static public boolean gradeLeveGreaterThan3(Student student){
        return student.getGradeLevel() >3;
    }

    private static boolean gradeLevelGreaterThan(Student student, Integer integer) {
        return student.getGradeLevel() >= integer;
    }

    public static void main(String[] args) {

        System.out.println("Lambda expression - Student pass grade level >= 3? " + predicateUsingLambda.test(StudentDataBase.studentSupplier.get()));

        System.out.println("Method reference - Student pass grade lvel >=3? " + predicateUsingMethodReference.test(StudentDataBase.studentSupplier.get()));

        System.out.println("Lambda expression biPredicate - Student pass grade level? " + biPredicateUsingLambda.test(StudentDataBase.studentSupplier.get(), 3));
        System.out.println("Method reference biPredicate - Student pass grade level? " + biPredicateUsingMethodReference.test(StudentDataBase.studentSupplier.get(), 1));

    }


}
