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
