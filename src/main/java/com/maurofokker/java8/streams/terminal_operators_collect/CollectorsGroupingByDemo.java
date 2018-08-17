package com.maurofokker.java8.streams.terminal_operators_collect;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.*;
import java.util.stream.Collectors;

public class CollectorsGroupingByDemo {

    public static void main(String[] args) {

        // groupingBy(classifier)
        System.out.println("Grouping by gender : ");
        groupingStudentsByGender();
        System.out.println("Custom grouping by gpa : ");
        customizedGroupingByGpa();

        // groupingBy(classifier, downstream) or two level grouping
        System.out.println("Grouping by grade level and gpa :");
        groupingByGradeLevelAndByGpa();
        System.out.println("Grouping students total notebooks by grade level: ");
        groupingStudentsTotalNotebooksByGradeLevel();

        // groupingBy(classifier, supplier mapFactory, downstream) or three level grouping
        System.out.println("Grouping by students name : ");
        groupingStudentsByNameInLinkedHashMap();

        // using maxBy as Collector
        System.out.println("Max Student by grade level : ");
        studentWithMaxGpaByGrade();

        // using minBy as Collector
        System.out.println("Min Student by grade level : ");
        studentWithMinGpaByGrade();
    }

    /**
     * groupingBy(Function classifier)
     */
    public static void groupingStudentsByGender() {
        Map<String, List<Student>> studentsByGender = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(Student::getGender))
                ;
        System.out.println(studentsByGender);
    }

    public static void customizedGroupingByGpa() {
        Map<String, List<Student>> custom = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(student -> student.getGpa() >= 3.8 ? "EXCELLENT" : "AVERAGE"))
                ;
        System.out.println(custom);
    }

    /**
     * groupingBy(Function classifier, Collector downstream)
     */

    public static void groupingByGradeLevelAndByGpa() {
        Map<Integer, Map<String, List<Student>>> students =  StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(
                        Student::getGradeLevel      // classifier
                        , Collectors.groupingBy(student -> student.getGpa() >= 3.8 ? "EXCELLENT" : "AVERAGE") // downstream
                ))
        ;
        System.out.println(students);
    }

    public static void groupingStudentsTotalNotebooksByGradeLevel() {
        Map<Integer, Integer> students =  StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(
                        Student::getGradeLevel      // classifier
                        , Collectors.summingInt(Student::getNoteBooks) // downstream
                ))
                ;
        System.out.println(students);
    }

    /**
     * groupingBy(Function classifier, Supplier mapFactory, Collector downstream)
     *
     * passing a Supplier to override Map::new
     */
    public static void groupingStudentsByNameInLinkedHashMap() {
        LinkedHashMap<String, Set<Student>> groupingByName = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(
                        Student::getName        // classifier  --> it is going to be the key (String)
                        , LinkedHashMap::new    // supplier mapFactory override into LinkedHashMap --> what king of collection is gonna return
                        , Collectors.toSet()    // downstream  --> what is the value for the output that is going to generate
                ));
        System.out.println(groupingByName);
    }

    // using maxBy and minBy as Collector in groupingBy

    public static void studentWithMaxGpaByGrade() {
        Map<Integer, Optional<Student>> studentWithMaxGpaByGrade = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(
                        Student::getGradeLevel
                        , Collectors.maxBy(Comparator.comparing(Student::getGpa))
                ));
        System.out.println(studentWithMaxGpaByGrade);

        Map<Integer, Student> studentWithMaxGpaByGradeWithoutOptional = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(
                        Student::getGradeLevel
                        , Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Student::getGpa))
                                , Optional::get
                        )
                ));
        System.out.println(studentWithMaxGpaByGradeWithoutOptional);
    }

    public static void studentWithMinGpaByGrade() {
        Map<Integer, Student> studentWithMinGpaByGradeWithoutOptional = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(
                        Student::getGradeLevel
                        , Collectors.collectingAndThen(Collectors.minBy(Comparator.comparing(Student::getGpa))
                                , Optional::get
                        )
                ));
        System.out.println(studentWithMinGpaByGradeWithoutOptional);
    }
}
