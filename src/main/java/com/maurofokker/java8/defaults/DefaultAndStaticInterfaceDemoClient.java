package com.maurofokker.java8.defaults;

import java.util.Arrays;
import java.util.List;

public class DefaultAndStaticInterfaceDemoClient {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        DefaultAndStaticInterfaceDemo client = new DefaultAndStaticInterfaceDemoImpl();

        System.out.println("Multiply elements in list : " + client.multiplyElementsInList(integers));
        System.out.println("Call method size default in interface or the overriden in impl calss?  : " + client.size(integers));
        System.out.println("Call static method in interface is perform calling the interface not the impl " + DefaultAndStaticInterfaceDemo.isEmpty(integers));

    }
}
