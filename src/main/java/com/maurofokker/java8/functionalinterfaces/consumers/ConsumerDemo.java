package com.maurofokker.java8.functionalinterfaces.consumers;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {

    public static void main(String[] args) {

        Consumer<String>  consumer = s -> System.out.println(s.toUpperCase());
        consumer.accept("hello world");
        // output: HELLO WORLD

        List<String> names = Arrays.asList("John", "Doe");
        //names.forEach(s -> System.out.println(s.toUpperCase()));
        names.forEach(consumer);
        // output: JOHN \n DOE

        printStudentsName();
        printStudentsNameAndActivities();
        printNameAndActivitiesUsingCondition();

    }

    private static void printStudentsName() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Printing students names");

        Consumer<Student> consumer = student -> System.out.println(student);
        List<Student> students = StudentDataBase.getAllStudents();
        students.forEach(consumer);

    }

    private static void printStudentsNameAndActivities() {
        System.out.println("---------------------------------------------------------");
        System.out.println("Printing students names and activities");

        Consumer<Student> studentsNameConsumer = student -> System.out.print(student.getName());
        Consumer<Student> studentsActivitiesConsumer = student -> System.out.println(student.getActivities());

        List<Student> students = StudentDataBase.getAllStudents();
        // apply consumer chaining
        students.forEach(studentsNameConsumer.andThen(studentsActivitiesConsumer));

    }

    public static void printNameAndActivitiesUsingCondition(){
        System.out.println("---------------------------------------------------------");
        System.out.println("Printing students names and activities using conditions");

        Consumer<Student> studentsNameConsumer = student -> System.out.print(student.getName());
        Consumer<Student> studentsActivitiesConsumer = student -> System.out.println(student.getActivities());

        List<Student> personList = StudentDataBase.getAllStudents();
        personList.forEach((student) -> {
            if( student.getGradeLevel() >= 3 && student.getGpa() > 3.9){
                studentsNameConsumer.andThen(studentsActivitiesConsumer).accept(student);
            }
        });
    }

}
