# Java 8 features

## Imperative vs Declarative programmig

* Imperative Style - how style of programming
  * shared mutable state
  * it is sequential and it will go step by step
  * it will have issues if it run in a multithreaded env

* Declarative style - what style of programming.
  * functional programming uses the same style
  * let the system do the job for you and get the result
  * can run in multithreaded environment

## Lambda expression

* Lambda => method without a name => anonymous function => arrow function
* Not tied to any class 
* Can be assigned to a variable and passed around
* Syntax
  ```java
    (   )   ->  {   }
  ```
  * `( )` input parameters passed to the lambda
    * type is optional
  * `->` to separate input from method body
  * `{ }` contain the logic of what lambda is going to do with input parameters
    * optionals for single statement or expression
    * are needed in case of multiple statements

* Mainly used to implement functional interfaces (SAM - Single Abstract Method interfaces) optionally can be annotated with `@FunctionalInterface`
  ```java
    package java.lang;
    
    @FunctionalInterface
    public interface Runnable {
        void run();
    }
  ``` 

## Functional Interfaces

* Exists since java 1.0
* Known as Single Abstract Method, is an interface that has exactly one abstract method
* In java 8 use the optional annotation `@FunctionalInterface`
* Principals functional interfaces in java 8
  * Consumer
  * Predicate
  * Function
  * Supplier
* Presents in the `java.util.function` package

### Consumer Functional Interface

* Consumer can be used in all contexts where an object needs to be consumed.
  * Object is taken as input and some operations are performed on the object without returning any result
  * `(T t) -> {}`
* Since it is a functional interface, it can be used as the assignment target for a lambda expression or a method reference
* It has one abstract method `void accept(T t)` that accepts one single input argument and return nothing
* It has a default method `default Consumer<T> andThen(Consumer<? super T> after)` (From java 8 interfaces can have `default` methods) 
* It is a function that is representing side effects
* Iterable interface has a default `forEach` method that accepts a Consumer as argument
  ```java
    default void forEach(Consumer<? super T> action) {
      Objects.requireNonNull(action);
        for (T t : this) {
          action.accept(t);
        }  
    }
  ```
  
  ```java
    Consumer<String> consumer = s -> System.out.println(s.toUpperCase());
    List<String> names = Arrays.asList("John", "Doe");
    names.forEach(consumer);
    // output: JOHN \n DOE
  ```
