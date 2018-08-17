package com.maurofokker.java8.defaults;

import java.util.List;

public class DefaultAndStaticInterfaceDemoImpl implements DefaultAndStaticInterfaceDemo {
    @Override
    public int multiplyElementsInList(List<Integer> integers) {
        return integers.stream().reduce(1, (x, y) -> x * y);
    }

    @Override
    public int size(List<Integer> integers) {
        System.out.println("inside default method overriden in implementation class");
        return integers.size();
    }
}
