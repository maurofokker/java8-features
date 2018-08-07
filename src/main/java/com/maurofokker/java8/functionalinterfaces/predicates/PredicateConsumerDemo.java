package com.maurofokker.java8.functionalinterfaces.predicates;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateConsumerDemo {

    static Predicate<Student> studentWithGradeLevelGreatEqThan3 = (student) -> student.getGradeLevel() >= 3;

    static Predicate<Student> studentWithGPAGreaterEqThan3point9 = (student) -> student.getGpa() >= 3.9;

    BiConsumer<String, List<String>> studentNameAndActivitiesBiConsumer = (name, activities) -> System.out.println(name + " : " + activities);

    Consumer<Student> studentWithGradeLevelGEthan3AndGpaGEthan3point9Consumer = (student) -> {
        if (studentWithGradeLevelGreatEqThan3.and(studentWithGPAGreaterEqThan3point9).test(student)) {
            studentNameAndActivitiesBiConsumer.accept(student.getName(), student.getActivities());
        }
    };

    public void printStudentsNameAndActivities(List<Student> students) {
        students.forEach(studentWithGradeLevelGEthan3AndGpaGEthan3point9Consumer);
    }

    public static void main(String[] args) {

        List<Student> students = StudentDataBase.getAllStudents();
        new PredicateConsumerDemo().printStudentsNameAndActivities(students);

    }

}
