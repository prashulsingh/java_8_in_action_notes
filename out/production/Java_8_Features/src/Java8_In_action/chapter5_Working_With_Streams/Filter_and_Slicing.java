package Java8_In_action.chapter5_Working_With_Streams;

import Java8_In_action.chapter4_Introducing_Streams.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@SuppressWarnings("ALL")
public class Filter_and_Slicing {
    public static void main(String[] args) {

        //FILTERING WITH A PREDICATE
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 4);
        System.out.println(numbers.stream()
                .filter(i -> i % 2 == 0).collect(toList()));


        //FILTERING UNIQUE ELEMENTS
        //Stream supports a method called Distinct which gives stream of distinct elements

        numbers.stream()
                .filter(i -> i % 2 == 0)
                //.distinct().forEach(element->System.out.println(element));
                .distinct().forEach(System.out::println);

        // TRUNCATING A STREAM
        //Streams support the limit(n) method, which returns another stream that’s no longer than a given size.

        List<Dish> menu=getDieshes();
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());

        //SKIPPING A STREAM
        //skip(n) method to return  a stream that discards the first n elements.
        //If the stream has less than n elements then empty stream is returned.
        //limit(n) and skip(n) are complimentary in nature

        List<Dish> skippedDishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(3)//skip first three
                .collect(toList());

        //FILTER TWO MEAT DISHES
        List<Dish> twoMeatDishes = menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT )
                                                .limit(2).collect(toList());


        /*************MAPPING **************************/
        /**
        5.2.1. Applying a function to each element of a stream
        mapping is used because it has a meaning similar to transforming but with the nuance of “creating a new
        version of rather than modifying
        Get all the dish names whose calories is greater than 300

        SQL QUery is Select dishName from dish where calories > 300.
        */
        List<String> dishNames = dishes.stream().filter(dish -> dish.getCalories()>300).
                                 map(Dish::getName).collect(toList());

        /**
         *Stream outputted by the map is string and then collected in a list
         * Given a list of words, you’d like to return a list of the number of characters for each word
         */


        List<String> words = Arrays.asList("Hello","How","Are","you");

        System.out.println( words.stream().map(String::length).collect(toList()) );

        //Let’s now return to the example where you extracted the name of each dish.
        // What if you wanted to find out the length of the name of each dish?
        System.out.println( dishes.stream().map(Dish::getName).map(String::length).collect(toList()) );

        /**
        <image src="image1.png"/>
         */

        /*****************FLATTENING STREAMS ******************/
        //how could you return a list of all the unique characters for a list of words?

        //solution one -
        words = Arrays.asList("Hello","How","Are","you");
       words.stream().map(s -> s.split("")).forEach(strings -> {
            for( int i = 0 ; i < strings.length; i++ )
                System.out.print(strings[i]);
           System.out.println();
        });
        /**
         <image src="image2.png"/>
         //THe below approach wont work since map returns char[] and then distinct
         //so basically map will return ['H','e','l','l','o'] ['H','o','w'] ['A','r','e'] ['Y','o','u']
         //then these four objects are compared for distinct, obviously all objects are unique
         //hence this approach will not work
         */

        System.out.println(words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList()));

        /*
        Attempt using map and Arrays.stream
        Attempt using map and Arrays.stream
        First, you need a stream of characters instead of a stream of arrays.
         */
        //conversion of Arrays to Streams
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        streamOfwords.forEach(System.out::println);

        System.out.println(words.stream()
                .map(word -> word.split(""))
                .map( Arrays::stream)
                .distinct()
                .collect(toList()));

        //working solution
        //In a nutshell, the flatMap method lets you replace each value of
        // a stream with another stream and then concatenates all the generated streams into a single stream.
        System.out.println(words.stream()
                .map(word -> word.split(""))
                .flatMap( Arrays::stream)
                .distinct()
                .collect(toList()));

        /**Quiz 5.2: Mapping
        Given a list of numbers, how would you return a list of the square of each number?
         */
        numbers = Arrays.asList(1,2,3,4,5);
        System.out.println( numbers.stream().map(integer -> integer*integer).collect(Collectors.toList()) );

        /**
         * 2. Given two lists of numbers, how would you return all pairs of numbers?
         * For example, given a list [1, 2, 3] and a list [3, 4]
         * you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)].
         * For simplicity, you can represent a pair as an array with two elements.
         */

        numbers = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);
        System.out.println(numbers.stream()
                .flatMap(i -> numbers2.stream().filter(j->(i+j)%3==0).map(j -> Arrays.asList(i, j))).collect(toList()));

        //5.3. FINDING AND MATCHING
        // allMatch, anyMatch, noneMatch, findFirst, and findAny methods of a stream.
        //5.3.1. Checking to see if a predicate matches at least one element
        //you can use it to find out whether the menu has a vegetarian option:
        if( dishes.stream().anyMatch(Dish::isVegetarian) ) //same as Collection.contains
            System.out.println("There are vegetarian dishes available ");


        //    5.3.2. Checking to see if a predicate matches all elements

        boolean isHealthy = menu.stream()
                .allMatch(d -> d.getCalories() < 1000);

        //oppposite of allMatch is noneMatch


//        This is called short circuiting and it is possible in Streams also
          // we dont need to procees the whole stream
//        boolean flag = false;
//        for( Dish dish : dishes )
//        {
//            if( dish.getCalories()>150)
//            {
//                flag = true;
//                break;
//            }
//        }

        /*****5.3.3. Finding an element *******/
        Optional<Dish> anyVegeterianDish = dishes.stream().filter(Dish::isVegetarian).findAny();
        //The stream pipeline will be optimized behind the scenes to perform a single pass and finish as soon as a result is found by using short-circuiting.
        //Optional<T> is used for null checking will be covered in Chapter 10
        anyVegeterianDish.isPresent();
        // isPresent() returns True if Optional has a value, otherwise return false

        /***** 5.3.4. Finding the first element ******/
         //findFirst element similary to findAny , it returns the first element encountered in the stream pipeline
        //findFirst is more constraining when working in parallel


        /*********5.4. REDUCING *******/
        /*“Calculate the sum of all calories in the menu,”
                        or
          “What is the highest calorie dish in the menu?” using the reduce operation.
         */
        //TO calculate the sum of all the numbers in an array
        //Sum initial value which is set to 0 and what is added to sum every time
        int sum = 0;
        for (int x : numbers) {
            sum += x;
        }

        //Using reduction

        int finalsum = numbers.stream().reduce(0, (tempSum,x)->tempSum+x);


        //A BinaryOperator<T> to combine two elements and produce a new value; here you use the lambda (a, b) -> a + b.

        int finalProduct = numbers.stream().reduce(0, (tempSum,x)->tempSum*x);


        // refer Using_reduce_to_sum_the_numbers_in_a_stream image

        //Concise using the method reference

        finalsum = numbers.stream().reduce(0, Integer::sum);

        //OPtional object because it can contain null also
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        max.empty();
        max.isPresent();
        //value returned by max
        max.get();

        //        Quiz 5.3: Reducing

//        How would you count the number of dishes in a stream using the map and reduce methods?
        // This pattern is called map - reduce pattern
        Integer reduce = dishes.stream().map(dish -> 1).reduce(0, (dishSum,s) -> s + 1);

        long count = dishes.stream().count();

        //Benefit of the reduce method and parallelism
        // Important breaking the input into chunks, calcuate the sum and merge them together

        /******Stream operations: stateless vs. stateful ******/
        // Stateless - like map and filter which doesnt depend on the previous state
        // State - which depends on the previous state. like sorting


        //5.6.1. Primitive stream specializations

        /*
        Java 8 introduces three primitive specialized stream interfaces to tackle this issue,
        IntStream, DoubleStream, and LongStream, that respectively specialize the elements of
         a stream to be int, long, and double—and thereby avoid hidden boxing costs.
         */

        OptionalInt reduce1 = dishes.stream().mapToInt(Dish::getCalories).reduce(Integer::sum);

        System.out.println(reduce1.getAsInt() );

        //Provide default values if

        //5.6.2. Numeric ranges

        IntStream range = IntStream.range(1, 100);

        IntStream.rangeClosed(1, 100);

        for( int i = 1 ; i < 100; i++ )
        {
            for( int j = 1 ; j < 100; j++ )
            {
                int c = i*i + j*j;
                if( c < 100 )
                    System.out.println("(c,a,b)" +c+","+i +"," +j);
            }
        }

        //****NOT DONE *************        5.6.3. Putting numerical streams into practice: Pythagorean triples

        //5.7.1. Streams from values

        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.forEach(System.out::println);

//        5.7.2. Streams from arrays

        int[] arrayOfNumber = {2,3,5,7,11,13};

        IntStream stream1 = Arrays.stream(arrayOfNumber);


//        5.7.3. Streams from files

        //5.7.4. Streams from functions: creating infinite streams!

        Stream.iterate(0, n->n+2).limit(100).forEach(System.out::println);

        //Fibonacci tupple

        Stream.iterate(new int[]{0,1}, n->new int[]{n[1],n[1]+n[0]}).limit(20).forEach(n->System.out.println(n[0]+
                " "+n[1]));
        //An infinite stream is a stream that has no fixed size.


    }

    public static List<Dish> getDieshes() {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        return menu;
    }
}
