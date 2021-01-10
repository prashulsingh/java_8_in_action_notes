package Java8_In_action.chapter_9_Default_Methods;

public class Main {
    public static void main(String[] args) {
        /*
        . First, Java 8 allows static methods inside interfaces.
        Second, Java 8 introduces a new feature called default methods that allows you to provide
         a default implementation for methods in an interface.
         In other words, interfaces can provide concrete implementation for methods.
         */

        //.naturalOrder method.

        //9.1 Evolving APIs

        Resizable square  = new Square();
        //Methods present in the Resizable interface
//        square.getHeight();
//        square.getWidth();
//        square.setAbsoluteSize();
//        square.setHeight();
//        square.setWidth();

        /*
        After the release of the APIs. we realize there is a missing feature and we need setRelativeSize  method
        Abstract classes vs. interfaces in Java 8
        S  o what’s the difference between an abstract class and an interface? They both can contain abstract methods and methods with a body.

        First, a class can extend only from one abstract class, but a class can implement multiple interfaces.

        Second, an abstract class can enforce a common state through instance variables (fields). An interface can’t have instance variables.
         */
        //Inheritance considered harmful
        //inheriting from a class that has 100 methods and fields just to reuse one method is a bad idea, because it
        // adds unnecessary complexity

        //what if a class implements two interfaces that have the same default method signature? Which method is the
        // class allowed to use? We explore this problem in the next section.

        /**** IMPORTANT RESOLUTION RULES ***/

        /*
        9.4.1. Three resolution rules to know
        There are three rules to follow when a class inherits a method with the same signature from multiple places (such as another class or interface):


        1.  Classes always win. A method declaration in the class or a superclass
        takes priority over any default method declaration.

        2.  Otherwise, sub-interfaces win:
        the method with the same signature in the most specific default-providing interface is selected.
        (If B extends A, B is more specific than A).

        3.  Finally, if the choice is still ambiguous, the class inheriting from multiple interfaces
        has to explicitly select which default method implementation to use by overriding it and calling
        the desired method explicitly.

         */

    }
}
