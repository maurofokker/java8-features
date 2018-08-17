package com.maurofokker.java8.defaults;

import java.util.List;

public interface DefaultAndStaticInterfaceDemo {

    // concrete class must implement this method
    int multiplyElementsInList(List<Integer> integers);

    // this method can be overriden and call within the interface impl
    default int size(List<Integer> integers) {
        System.out.println("inside default method in interface");
        return integers.size();
    }

    // static method will be call using the interface not the implementation
    static boolean isEmpty(List<Integer> integers) {
        return integers != null && integers.size() == 0;
    }

}
