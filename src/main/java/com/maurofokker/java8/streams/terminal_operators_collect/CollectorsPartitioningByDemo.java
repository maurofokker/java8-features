package com.maurofokker.java8.streams.terminal_operators_collect;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toMap;

public class CollectorsPartitioningByDemo {

    public static void main(String[] args) {
        System.out.println("Partitioning that return students with gpa >= 3.8");
        //partitioningByStudentsWithGpaGE3_8();
        partitioningByStudentsNamesAndActivities();
    }

    public static void partitioningByStudentsWithGpaGE3_8() {
        Predicate<Student> studentsWithGpaGreaterThan3_8 = student -> student.getGpa() >= 3.8;
        Map<Boolean, List<Student>> booleanListMap = StudentDataBase.getAllStudents()
                .stream()
                .collect(partitioningBy(studentsWithGpaGreaterThan3_8));
        System.out.println(booleanListMap);
    }

    public static void partitioningByStudentsWithGpaGE3_8_toSetCollector() {
        Predicate<Student> studentsWithGpaGreaterThan3_8 = student -> student.getGpa() >= 3.8;
        Map<Boolean, Set<Student>> booleanListMap = StudentDataBase.getAllStudents()
                .stream()
                .collect(partitioningBy(
                        studentsWithGpaGreaterThan3_8   // predicate
                        , Collectors.toSet()            // downstream
                        ));
        System.out.println(booleanListMap);
    }

    public static void partitioningByStudentsNamesAndActivities(){

        Predicate<Student> studentsWithGpaGreaterThan3_8 = (student) -> student.getGpa() >= 3.8;

        Map<Boolean,Map<String, List<String>>> studentsActivitiesMap = StudentDataBase.getAllStudents()
                .stream()
                .collect(partitioningBy(
                        studentsWithGpaGreaterThan3_8
                        ,toMap(Student::getName, Student::getActivities)
                ));

        System.out.println(studentsActivitiesMap);

    }
}
