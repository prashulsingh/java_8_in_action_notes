package Java8_In_action.chapter2_Pasing_Code_With_Behaviour_Parameterization;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Apple_Sort_Using_Anonymous_Class {

    public static void main(String[] args) {
        List< Apple > apples = new ArrayList<>();
        apples.add( new Apple("green",100));
        apples.add( new Apple("green",101));
        apples.add( new Apple("red",102));
        apples.add( new Apple("green",103));
        apples.add( new Apple("red",104));
        apples.add( new Apple("green",105));

        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.color.compareTo(o2.color);
            }
        });

    }
}
