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
