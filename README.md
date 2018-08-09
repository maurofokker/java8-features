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

### Local variables in lambda

* Any variable declared inside a method is called a local variable
* Restrictions in lambda
  * Not allowed to use the same local variable name as lambda parameter or inside a lambda body
    ```java
      public void someMethod() {
        int i = 0;
        
        // compile error bc: Variable 'i' is already defined in the scope
        // Consumer<Integer> consumer = i -> System.out.printLine("value is: " + i);
        Consumer<Integer> consumer = x -> System.out.printLine("value is: " + x);
      }
    ```
    
    ```java
      public void someMethod() {
        int i = 0;
              
        // compile error bc: Variable 'i' is already defined in the scope
        Consumer<Integer> consumer = x -> {
          int i = 1;      // same variable in the method and lambda body
          System.out.printLine("value is: " + x);
        };
      }    
    ```
  * Not allowed to re-assign a new value to a local variable in the lamda expression scope
    ```java
      public void someMethod() {
        int i = 0;
              
        // compile error bc: Variable used in lambda expression should be final or effectively final
        Consumer<Integer> consumer = x -> {
          i++; // here is the compilation error
          System.out.printLine("value is: " + (i + x));
        };
      }    
    ```
    
* No restrictions associated to instance variables in lambda (it is allowed to modify values of instances variables and static variables)
```java
public class Demo {
    static int value = 1;
    
    public void someMethod() {
      Consumer<Integer> consumer = x -> {
        value++; // it is ok to modify static variable or instance variable
        System.out.printLine("value is: " + (value + x)); 
      }; 
      consumer.accept(2);
    }
}
```

### Effectively Final variables

* Are called like effectively lambda, because lambdas can use local variables but can not modify them even though they are not declared final
* Prior to java 8 variables inside anonymous classes should be mark as final explicitly
* Advantages of effectively final:
  * Easy to perform concurrent operations
  * Promotes functional programming and demotes the imperative style of programming

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

### Function<T, R> Functional Interface

* Introduced as part of java 1.8
* Can implement a function (method) and assign to a variable
* It functional method is `R apply(T t);` computes the result of type R of applying the function to the input argument of type T
* It has 2 default methods
  * `default <V> Function<V, R> compose(Function<? super V, ? extends T> before)`
  * `default <V> Function<T, V> andThen(Function<? super R, ? extends V> after)`
```java
  // T t: input type String, R r: output type String but uppercase
  Function<String, String> takesNameAndReturnNameInUpperCase = (name) -> name.toUpperCase();
  System.out.println("Result of apply to function : " + takesNameAndReturnNameInUpperCase.apply("john"));
  // output: Result of apply to function : JOHN
  Function<String, String> prefixGreeting = (name) -> "Hello, ".concat(name.toLowerCase());
  System.out.println("Result of _andThen_ default method : " + takesNameAndReturnNameInUpperCase.andThen(prefixGreeting).apply("John"));
  // apply first takesNameAndReturnNameInUpperCase function -> JOHN
  // and after apply andThen prefixGreeting -> Hello, john 
  // output: Result of _andThen_ default method : Hello, john
  System.out.println("Result of _compose_ default method : " + takesNameAndReturnNameInUpperCase.compose(prefixGreeting).apply("John"));
  // first apply prefixGreeting function inside of COMPOSE -> Hello, john
  // and after apply takesNameAndReturnNameInUpperCase to that result -> HELLO, JOHN
  // output: Result of _compose_ default method : HELLO, JOHN
```

### BiFunction<T, U, R> Functional Interface

* Represents a function that accepts two arguments of type `T` and `U` and produces a result of type `R`
* Is the two-arity specialization of Function
* It functional method is `R apply(T t, U u);`
* It has a default method `default <V> BiFunction<T, U, V> andThen(Function<? super R, ? extends V> after)`
  ```java
    BiFunction<String, String,String> bi = (x, y) -> {      
      return x + y;
    };

    System.out.println(bi.apply("Hello, ", "John"));
    // output: Hello, John
  ```

### UnaryOperator<T> Functional Interface

* Introduced in java 1.8 
* Can be used as lambda expression to pass as an argument
* Extends `Function<T, T>`
* Represents an operation on a single operand that produces a result of the same type as its operand
```java
  UnaryOperator<String> toUpperCaseUnaryOperator = (s -> s.toUpperCase());
  List<String> names = Arrays.asList("John", "Jane", "Freddy");
  names.forEach(name -> System.out.println(toUpperCaseUnaryOperator.apply(name)));
  // output: JOHN \n JANE \n FREDDY
```

### BinaryOperator<T> Functional Interface

* Can be used as lambda expression to pass as an argument
* Extends `BiFunction<T,T,T>`
* Accepts two operands of the same type and process it and then returns results of the same type as operands
* It has two static methods
  * `public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator)` which returns lesser of two elements
  * `public static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator)` which returns greater of two elements
  ```java
    // to test minBay and maxBy static methods of BinaryOperator a Comparator must be declare bc is the argument they accept
    Comparator<Integer> compareIntegers = (a, b) -> a.compareTo(b);
    // minBy
    BinaryOperator<Integer> min = BinaryOperator.minBy(compareIntegers);
    System.out.println("Min between 3 and 4 is -> " + min.apply(3, 4) ); // output: 3

    // maxBy
    BinaryOperator<Integer> max = BinaryOperator.maxBy(compareIntegers);
    System.out.println("Max between 3 and 4 is -> " + max.apply(3, 4) ); // output: 4
  ```

### Supplier<T> Functional Interface

* Since java 1.8
* It is a Function specialization that does not take any arguments
* Can be assigned in lambda expression
* Can be passed as argument in different methods in java 1.8
* Represents a supplier of results
* There is no requirement that a new or distinct result be returned each time the supplier is invoked
* Functional method is ` T get();`
* Can implement a producer, typically used for lazy generation of values
  * [java-8-lazy-argument-evaluation](https://blog.rapid7.com/2017/01/13/java-8-lazy-argument-evaluation/)
  
```java
  Supplier<String> helloWorldSupplier = () -> new String("Hello, world!");
  String helloWorld = helloWorldSupplier.get();
  System.out.println("String is: "+ helloWorld);
```

## Method Reference

* Since java 1.8
* Simplify implementation of functional interfaces
* Shortcut for writing lamda expressions
* Refer to a method in a class
* Syntax:
  * `ClassName::instance-methodName`
  * `ClassName::static-methodName`
  * `Instance::methodName`
  ```java
    // using lambda
    Function<String, String> toUpperCaseLambda = (s) -> s.toUpperCase();
    // using method reference - ClassName::instance-methodName
    Function<String, String> toUpperCaseMethodReference = String::toUpperCase;
  ```
* Method reference is not applicable where a logic is perform in lambda but in some cases can be refactored and extract to a method
  ```java
    // lambda expression
    BiPredicate<Student, Integer> biPredicateUsingLambda = ((student, integer) -> student.getGradeLevel() >= integer);

    // with method reference ... extracted method from biPredicateUsingLambda with intellij
    static BiPredicate<Student, Integer> biPredicateUsingMethodReference = (PredicateMethodReferenceDemo::gradeLevelGreaterThan);
    private static boolean gradeLevelGreaterThan(Student student, Integer integer) {
      return student.getGradeLevel() >= integer;  
    }
  ```

## Constructor Reference

* Since java 1.8
* Syntax:
  * `ClassName::new` it need to have an empty constructor
  ```java
    // require empty constructor in object
    Supplier<Student> studentSupplier = Student::new;

    // compilation error on objects
    // Student student = Student::new;
  
    // it needs a constructor with 1 argument or it will complain
    Function<String, Student> studentFromNameFunction = Student::new;
  ```

## Streams API

* Since java 1.8
* Main purpose is to perform some operation on Collections (List, Map ...)
* Parallel operations are easy to perform without having to spawn a multiple threads
* Can be used with Arrays or any other kind of I/O
* Streams is a sequence of elements which can be `created` out of a collections (List, Arrays) or any kind of I/O resources
* Streams operations can be performed either sequentially or parallel
  ```java
    List<String> names = Arrays.asList("john", "jane");
    names.stream();  // create a stream
    names.parallelStream(); // create a parallel stream
  ```

* Stream pipeline has intermediate operations that returns a Stream and a terminal operator that can return a Collection or a Stream or some value
```java
  List<String> names = Arrays.asList("john", "jane", "bill");
  names.stream()                        // output: Stream<String>
         .filter(s -> s.startsWith("j"))         // output: Stream<String> is an intermediate operation
         .collect(Collectors.toList())  // output: List<String> is the terminal operation
                                        // intermediate operations would not happened without the terminal operation
  // ["john", "jane"]
  ;
```

### Collections vs Streams

| Collections        | Streams           |
| ------------- | ------------- |
| can add or modify elements (`list.add(?)`)      | it is a fixed data set so it is not possible to add or modify elements |
| can be accessed in any order (depend on methods of the collection `list.get(0)`)      | allow access to elements in sequence      |
| is eagerly constructed | is lazily constructed      |
| can be traversed _n_ number of times  | can be traversed only once when is consumed  |
| performs external iteration to iterate through the elements `for (Item item: items)`  | performs internal iteration to iterate through the elements `items.stream().forEach()` |

* [Stream javadoc](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html#package.description)

### Using peek() method to debug stream

* This method exists mainly to support debugging, where you want to see the elements as they flow past a certain point in a pipeline
```java
  Stream.of("one", "two", "three", "four")
    .filter(e -> e.length() > 3)
    .peek(e -> System.out.println("Filtered value: " + e))
    .map(String::toUpperCase)
    .peek(e -> System.out.println("Mapped value: " + e))
    .collect(Collectors.toList());
```

### Operation: map()

* It is not the `Map` collection
* It is used to convert (transform) one type to another
* Returns a stream consisting of the results of applying the given function to the elements of this stream.
  * `java <R> Stream<R> map(Function<? super T, ? extends R> mapper);`
* It is an intermediate operation in the pipeline
```java
  List<String> studentsName = StudentDataBase.getAllStudents()
                                .stream()                       // Stream<Student>
                                // Function input Student -> Function output String (getName())
                                // Stream<String> map(Function<Student, String> mapper);
                                .map(Student::getName)          // Stream<String>
                                .collect(Collectors.toList())   // List<String>
                                ;
```

### Operation: flatMap()

* It is used to convert (transform) one type to another as like `map()` method
* Returns a stream consisting of the results of replacing each element of this stream with the contents of a mapped stream 
  produced by applying the provided mapping function to each element
  * `<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);`
* Used in the context of Streams where each element in the Stream represents multiple elements
  * `Stream<List>`
  * `Stream<Set>`
  * `Stream<Arrays>`
```java
  Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
  Stream<String> words = lines.flatMap(line -> Stream.of(line.split(" +")));
```
  * The `mapper` function passed to `flatMap` splits a line, using a simple regular expression, into an array of words, 
    and then creates a stream of words from that array
```java
    public static List<String> studentsActivities() {
        return StudentDataBase.getAllStudents()
                .stream()                       // Stream<Student>
                .map(Student::getActivities)    // Stream<List<String>>
                // Stream<String> flatMap(Function<List<Student>, String> mapper);
                .flatMap(List::stream)          // Stream<String>
                .collect(Collectors.toList())   // List<String>
                ;
    }
```

### Operations: distinct() - count() - sorted()

* `distinct()`
  * Returns a stream consisting of the distinct elements of this stream
  * For ordered streams, the selection of distinct elements is stable (for duplicated elements, the element 
    appearing first in the encounter order is preserved.) For unordered streams, no stability guarantees are made
  * It is a stateful intermediate operation
  * `Stream<T> distinct();`
  ```java
    public static List<String> uniqueStudentsActivities() {
        return StudentDataBase.getAllStudents()
                .stream()                       // Stream<Student>
                .map(Student::getActivities)    // Stream<List<String>>
                .flatMap(List::stream)          // Stream<String>
                .distinct()                     // Stream<String>
                .collect(Collectors.toList())   // List<String>
                ;
    }
  ```
* `count()`
  * Returns a `long` with the total number of elements in the Stream
  * It is a terminal operation
  * `long count();`
  ```java
    public static long countUniqueStudentsActivities() {
        return StudentDataBase.getAllStudents()
                .stream()                       // Stream<Student>
                .map(Student::getActivities)    // Stream<List<String>>
                .flatMap(List::stream)          // Stream<String>
                .distinct()                     // Stream<String>
                .count()                        // returns long
                ;
    }
  ```
* `sorted()`
  * Returns a stream consisting of the elements of this stream, sorted according to natural order.  
    If the elements of this stream are not `Comparable`, a `ClassCastException` may be thrown when the terminal operation is executed.
  * For ordered streams, the sort is stable.  For unordered streams, no stability guarantees are made.
  * It is a stateful intermediate operation
  * `Stream<T> sorted();`
  ```java
    public static List<String> sortedUniqueStudentsActivities() {
        return StudentDataBase.getAllStudents()
                .stream()                       // Stream<Student>
                .map(Student::getActivities)    // Stream<List<String>>
                .flatMap(List::stream)          // Stream<String>
                .distinct()                     // Stream<String>
                .sorted()                       // Stream<String>
                .collect(Collectors.toList())   // List<String>
                ;
    }
  ```
