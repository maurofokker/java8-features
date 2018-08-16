package com.maurofokker.java8.streams.terminal_operators_collect;

import com.maurofokker.java8.data.Student;
import com.maurofokker.java8.data.StudentDataBase;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectorsMappingDemo {

    public static void main(String[] args) {

        List<String> mapsStudentsNamesToList = StudentDataBase.getAllStudents()
                                                                .stream()
                                                                .collect(Collectors.mapping(Student::getName, Collectors.toList()))
                                                    ;
        System.out.println("Mapping students names to list of String : " + mapsStudentsNamesToList);


        Set<String> mapsStudentsNamesToSet = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.mapping(Student::getName, Collectors.toSet()))
                ;
        System.out.println("Mapping students names to set of String : " + mapsStudentsNamesToSet);
    }
}
