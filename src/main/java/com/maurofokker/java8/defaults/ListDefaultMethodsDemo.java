package com.maurofokker.java8.defaults;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class ListDefaultMethodsDemo {

    static Consumer<Student> printStudentConsumer = student -> System.out.println(student);

    static Comparator<Student> studentNameComparator = Comparator.comparing(Student::getName);
    static Comparator<Student> studentGpaComparator = Comparator.comparing(Student::getGpa);
    static Comparator<Student> studentGradeLevelComparator = Comparator.comparing(Student::getGradeLevel);

    public static void main(String[] args) {

        List<String> names = Arrays.asList("John", "Jane", "Juan", "Lisette", "Mauricio", "Darío");
        listInterfaceDefaultMethods(names);


        List<Student> students = StudentDataBase.getAllStudents();
        students.forEach(printStudentConsumer);
        System.out.println("Sort student list by name: ");
        sortStudentsName(students);
        System.out.println("Sort student list by gpa: ");
        sortStudentsByGpa(students);

        System.out.println("Comparator chaining . sort by grade and then by name : ");
        sortComparatorChainingByGradeLevelAndByName();

        System.out.println("Sort With null first");
        sortWhenListHasNullValue();
    }

    private static void sortStudentsName(List<Student> students) {

        students.sort(studentNameComparator);
        students.forEach(printStudentConsumer);
    }

    private static void sortStudentsByGpa(List<Student> students) {

        students.sort(studentGpaComparator);
        students.forEach(printStudentConsumer);
    }

    private static void sortComparatorChainingByGradeLevelAndByName() {
        List<Student> students = StudentDataBase.getAllStudents();
        students.sort(studentGradeLevelComparator.thenComparing(studentNameComparator));
        students.forEach(printStudentConsumer);
    }

    private static void sortWhenListHasNullValue() {
        List<Student> studentsWithNull = StudentDataBase.getAllStudentsWithNull();
        // can use Comparator.nullsFirst or Comparator.nullsLast
        Comparator<Student> studentNameComparatorWithNullFirst = Comparator.nullsFirst(studentNameComparator);
        studentsWithNull.sort(studentNameComparatorWithNullFirst);
        studentsWithNull.forEach(printStudentConsumer);
    }

    private static void listInterfaceDefaultMethods(List<String> names) {
        /**
         * Sort a List before java 8
         */
        //Collections.sort(names);
        //System.out.println("Sort list before java 8: " + names);

        /**
         * Sort using default method in List interface
         */
        names.sort(Comparator.naturalOrder());
        System.out.println("Sort list in natural order using default method sort : " + names);
        names.sort(Comparator.reverseOrder());
        System.out.println("Revert list : " + names);

        names.replaceAll(s -> s.toUpperCase());
        System.out.println("Replace all default method to names list: " + names);

    }
}
