package Java8_In_action.chapter2_Pasing_Code_With_Behaviour_Parameterization;

import java.util.ArrayList;
import java.util.List;

public class FilterApples {

    public static void main(String[] args) {
        //First attempt: filtering green apples
        List< Apple > apples = new ArrayList<>();
        apples.add( new Apple("green",100));
        apples.add( new Apple("green",101));
        apples.add( new Apple("red",102));
        apples.add( new Apple("green",103));
        apples.add( new Apple("red",104));
        apples.add( new Apple("green",105));

        //Farmers wants to filter green apples
        System.out.println( filterGreenApples(apples).toString() );


        //Farmers wants to filter red apples also , requirements change'
        System.out.println( filterColorApples(apples,"red").toString() );
        System.out.println( filterColorApples(apples,"green").toString() );

        //he farmer comes back to you and says, It would be really cool to differentiate between light apples and heavy apples.
        // Heavy apples typically have a weight greater than 150 g.
        // things become messy if extra constraints are added to filter the apples

        //********* STRATERGY PATTERN ***************
        System.out.println("********* STRATERGY PATTERN ***************");
        System.out.println( filterApples(new AppleGreenColorPredicate(), apples ) );
        System.out.println( filterApples(new AppleHeavyWeightPredicate(), apples ) );

        //Tackling Verbosity , use of Anonymous classes
        System.out.println("********* STRATERGY PATTERN With Anonymous classes***************");
        //No need to declare AppleGreenColorPredicate and AppleHeavyWeightPredicate
        //But in case of using it again and again we have to write these code
        System.out.println( filterApples(new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                //filter green apples.
                return "green".equals(apple.getColor());
            }
        }, apples) );

        System.out.println( filterApples(new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getWeight()>150;
            }
        }, apples) );



    }
    private static List<Apple> filterApples(ApplePredicate predicate, List<Apple> apples)
    {
        List<Apple> filteredApples = new ArrayList<>();
        for( Apple apple : apples )
        {
            if( predicate.test(apple) )
                filteredApples.add( apple );
        }
        return filteredApples;
    }
    private static List<Apple> filterGreenApples(List<Apple> apples) {
        List< Apple > greenApples = new ArrayList<>();
        for( Apple apple : apples )
        {
            if( "green".compareTo( apple.color ) == 0 )
                greenApples.add( apple );
        }
    return greenApples;
    }

    private static List<Apple> filterColorApples(List<Apple> apples, String color) {
        List< Apple > colorApples = new ArrayList<>();
        for( Apple apple : apples )
        {
            if( color.compareTo( apple.color ) == 0 )
                colorApples.add( apple );
        }
        return colorApples;
    }
}
