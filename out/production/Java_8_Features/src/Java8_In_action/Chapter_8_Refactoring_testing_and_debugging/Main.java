package Java8_In_action.Chapter_8_Refactoring_testing_and_debugging;

import Java8_In_action.chapter4_Introducing_Streams.Dish;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Main{
    public static void main(String[] args) {
        //*********8.1.2. From anonymous classes to lambda expressions*********

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Using anonymous classes");
            }
        };
        r.run();
        r = ()-> System.out.println("Using Lambda Expressions");
        r.run();

        //Errors while converting anonymous classes to Lambda expressions
        //variable shadowing

        r = new Runnable() {
            @Override
            public void run() {
                int a = 2;
                System.out.println("Using anonymous classes" + a);
            }
        };

        int a = 5;
        r = ()-> {
            //int a = 6; //cant shadow variables in Lambda , as 'a' is declared on the heap not on stack, declared as
            // final
            System.out.println("Using Lambda Expressions");
        };


        //Lambda expression can make the resulting code ambiguous in the context of overloading
        //TODO____when functional interface and our interface has same method name and arguments



        //*********8.1.3. From lambda expressions to method references*********
        //wherever possible use Method references as it makes the code more readable

        //comparing( Dish::getCalories )
        //Int::sum or Int::max in reduce stage of streams

        //**********8.1.4. From imperative data processing to Streams*********
        /* convert all the code that processes the collection with typical
        processing pattern with an iterator to use Stream API instead. Filtering and extracting use
        Stream API, dont use Stream API for imperative programming as it is difficult to read and convert

         */
        List<Dish> dishes = new ArrayList();
        System.out.println(dishes.stream().
                filter(dish -> dish.getCalories() > 150).
                sorted(Comparator.comparing(Dish::getCalories)).
                map(Dish::getName).collect(Collectors.toList()));

        //*********8.1.5. Improving code flexibility*********
        //conditional deferred execution and execute around.
        /*
        if (logger.isLoggable(Log.FINER)){
            logger.finer("Problem: " + generateDiagnostic());
        }
        if check
        logger.log(Level.FINER, "Problem: " + generateDiagnostic());
        irrespective of Level.FINER, generateDiagnostic is always evaluated
         */

        /*
        What you need is a way to defer the construction of the message so it can be generated only under a given condition (here, when the logger level is set to FINER). It turns out that the Java 8 API designers knew about this problem and introduced an overloaded alternative to log that takes a Supplier as argument. This alternative log method has the following signature:

public void log(Level level, Supplier<String> msgSupplier)
You can now call it as follows:

logger.log(Level.FINER, () -> "Problem: " + generateDiagnostic()
The log method will internally execute the lambda passed as argument only if the logger is of the right level.
The internal implementation of the log method is along the lines of this:

public void log( Level level, Supplier<String> msgSupplier )
{
    if( logger.isLoggable( level ) )
        log( level, msgSupplier.get() );
}

***************IMPORTANT ************

What’s the takeaway from the story?
If you see yourself querying the state of an object many times in client code (for example, the state of the logger),
 only to call some method on this object with arguments (for example, log a message),
  then consider introducing a new method that calls that method (passed as a lambda or method reference) only
  after internally checking the state of the object. Your code will be more readable (less clutter) and better
  encapsulated (the state of the object isn’t exposed in client code)!
         */


//8.2. REFACTORING OBJECT-ORIENTED DESIGN PATTERNS WITH LAMBDAS

        //8.2.1. Strategy


//8.3. TESTING LAMBDAS
        /*
        at Debugging.lambda$main$0(Debugging.java:6)
    at Debugging$$Lambda$5/284720968.apply(Unknown Source)
    Unfortunately, because lambda expressions don’t have a name, the compiler has to make up a name to refer to them.

     In this case it’s lambda$main$0, which isn’t very intuitive.
     use method by reference , then its name will be pointe in the stack trace

         */
    }

    //function with the same name

    public static void doSomething( Runnable r )
    {
        r.run();
    }

    public static void doSomething1( Task task )
    {
        task.execute();
    }
}