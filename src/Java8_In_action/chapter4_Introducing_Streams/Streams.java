package Java8_In_action.chapter4_Introducing_Streams;

import Java8_In_action.chapter5_Working_With_Streams.Filter_and_Slicing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Streams {
    public static void main(String[] args) {

        List<Dish> dishes = new ArrayList<>();
        Filter_and_Slicing.getDieshes();
        // System.out.println(dishes);
        //SELECT name FROM dishes WHERE calorie < 400 Order by Calories.
        //Traditional approach
//    List<Dish> lowCaloriesDishes = new ArrayList<>();
//        for( Dish dish : dishes )
//        {
//            if( dish.getCalories() < 150 )
//                lowCaloriesDishes.add( dish );
//        }
//    lowCaloriesDishes.sort(Comparator.comparing(Dish::getCalories));
//    List<String> lowCalorieDishesName = new ArrayList<>();
//        for( Dish dish : lowCaloriesDishes )
//            lowCalorieDishesName.add( dish.getDishName() );
//        System.out.println(lowCalorieDishesName );

        //STream approach, filter, sort, map, collect

        System.out.println(dishes.stream().
                filter(dish -> dish.getCalories() > 150).
                sorted(Comparator.comparing(Dish::getCalories)).
                map(Dish::getName).collect(Collectors.toList()));
//To exploit a multicore architecture and execute this code in parallel, you need only change stream() to parallelStream():
        System.out.println(dishes.parallelStream().
                filter(dish -> dish.getCalories() > 150).
                sorted(Comparator.comparing(Dish::getCalories)).
                map(Dish::getName).collect(Collectors.toList()));

        // Collections are about data; streams are about computations.
        /*
        Streams support database-like operations and common operations from functional programming languages to manipulate data,
         such as filter, map, reduce, find, match, sort, and so on. Stream operations can be executed either sequentially or in parallel.
         */

        //External iteration in terms of collection, since user is held responsible for iteration
        //Internal iteration in terms of streams, handled by itself
        // Lazy loading and loop fusion ( filtering and map into the same step )
        //Terminal operations are forEach , count and collect
    }
}
