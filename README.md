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
* Embrace code reuse

### Consumer<T> Functional Interface

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

### BiConsumer<T, U> Functional Interface

* It is an extension of `Consumer` functional interface
* It accepts two parameters as input argument `void accept(T t, U u);` and returns nothing
  * `accept` method performs the operation defined by BiConsumer

  ```java
    BiConsumer<String, String> biConsumer = (s1, s2) -> System.out.println("s1: " + s1.toUpperCase() + " - s2: " + s2.toUpperCase());
    biConsumer.accept("john", "doe");
    // output: s1: JOHN - s2: DOE
  
    Map<String, String> map = new HashMap<>();
    BiConsumer<String, String> biConsumerMethodReference = map::put;
    biConsumerMethodReference.accept("john", "doe");
    System.out.println("BiConsumer with method reference result for map -> " + map);
  // output: BiConsumer with method reference result for map -> {john=doe}
  ```

### Predicate<T> Functional Interface

* Represents a predicate (boolean-valued function) of one argument as input
* It functional method is `boolean test(T t);` that returns true if the input argument matches the predicate
* It has 3 default methods and 1 static method:
  * `default Predicate<T> and(Predicate<? super T> other)`
  * `default Predicate<T> negate()`
  * `default Predicate<T> or(Predicate<? super T> other)`
  * `static <T> Predicate<T> isEqual(Object targetRef)`
* Typical use case of the Predicate lambda is to filter a collection of values
* There are versions that receive primitive values
  * IntPredicate
  * DoublePredicate
  * LongPredicate

  ```java
  // check even number
    Predicate<Integer> predicate = (i) -> i%2 == 0;
    System.out.println(predicate.test(8));
    // output: true
    System.out.println(predicate.test(3));
    // output: false
  ```

### BiPredicate<T, U> Functional Interface

* Accepts two argument and returns Boolean value
* Apply business logic for the values passed as an argument and return the boolean value
* It has 3 default methods
  * `default BiPredicate<T, U> and(BiPredicate<? super T, ? super U> other)`
  * `default BiPredicate<T, U> negate()`
  * `default BiPredicate<T, U> or(BiPredicate<? super T, ? super U> other)`
  ```java
    // example of BiPredicate<T, U>
    BiPredicate<Integer, String> ageGreaterThan21AndNameStartsWithJ = (age, name) -> age > 21 && name.startsWith("J");
    System.out.println(ageGreaterThan21AndNameStartsWithJ.test(23, "John")); // true
    System.out.println(ageGreaterThan21AndNameStartsWithJ.test(21, "John")); // false
  ```
