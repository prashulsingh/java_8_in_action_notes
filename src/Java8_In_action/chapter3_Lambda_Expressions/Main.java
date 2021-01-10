package Java8_In_action.chapter3_Lambda_Expressions;

import Java8_In_action.chapter2_Pasing_Code_With_Behaviour_Parameterization.Apple;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        List<Apple> apples = new ArrayList<>();
        apples.add( new Apple("green",100));
        apples.add( new Apple("green",101));
        apples.add( new Apple("red",102));
        apples.add( new Apple("green",103));
        apples.add( new Apple("red",104));
        apples.add( new Apple("green",105));


        behaviourParameterizationExample1((a,b) -> a-b);
        Predicate<Integer> predicate = (a)-> a>5;
        //predicate.negate();
        //predicate.or(<Another predicate>);
        //predicate.and(<Another predicate>);
        // Predicates have three more methods built in methods
        //.
        behaviourParameterizationExample2( predicate );
        Function<Integer,String> function = (a)->a.toString();
        behaviourParameterizationExample3( function );
        //Same lambda, different functional interfaces
        /*
        Because of the idea of target typing, the same lambda expression can be associated with different functional interfaces
        if they have a compatible abstract method signature. For example, both interfaces Callable and PrivilegedAction
        described earlier represent functions that accept nothing and return a generic type T.
         */
        Callable<Integer> c = () -> 42;
        PrivilegedAction<Integer> p = () -> 42;
        Comparator<Integer> comparator = (a,b)->a-b;
        //  (Apple a) -> a.getWeight() is same as Apple::getWeight
        // (str, i) -> str.substring(i) is same as String::substring, since this is a function
        // method to an object that will be supplied.
        //str.toUpperCase() can be written as String::toUpperCase

        /************Important********/
        //(args)->ClassName.staticMethod(args) is same as ClassName::staticMethod
        //(args0,rest)->args0.instanceMethod(rest) is same ClassName::instanceMethod
        //(Args)->exp.instanceMethod(args) is same as expr::instanceMethod
        //(String s) -> Integer.parseInt(s); is same as Integer::parse
        Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
        stringToInteger = Integer::parseInt;
        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);
        contains = List::contains;
        //sort apples by decreasing weight
//        app(Apple a1,Apple a2) -> a1.getWeight()-a2.getWeight();


    }

    private static void behaviourParameterizationExample3(Function<Integer, String> function) {
        System.out.println( function.apply(5) );
    }

    public static void behaviourParameterizationExample1( FunctionalInterface behaviour )
    {
        int a = 4;
        int b = 5;
        System.out.println( behaviour.add(a,b) );

    }

    public static void behaviourParameterizationExample2( Predicate<Integer> predicate )
    {
        System.out.println( predicate.test(5) );//returns false
        System.out.println( predicate.test( 6) );//returns true

    }


}
