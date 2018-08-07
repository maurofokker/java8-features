package com.maurofokker.java8.lambdas;

public class RunnableLambdaImpl {

    public static void main(String[] args) {

        /**
         * runnable implementation before java 8
         * - with an anonymous class
         * - pass the runnable to a thread
         */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("In runnable before java 8");
            }
        };

        new Thread(runnable).start();

        /**
         * runnable with java 8 lambda syntax
         * 1. in single line for one single statement
         * 2. multi lines -> multi statements
         * 3. pass the lambda expression directly as a parameter in the constructor
         */
        Runnable runnable1 = () -> System.out.println("In runnable with java 8 lambda in single statement");
        new Thread(runnable1).start();

        Runnable runnable2 = () -> {
            System.out.println("In runnable with java 8 lambda multi statement a");
            System.out.println("In runnable with java 8 lambda multi statement b");
        };
        new Thread(runnable2).start();

        new Thread(() -> System.out.println("In runnable with lamda expression as parameter")).start();
    }

}
