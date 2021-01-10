package Java8_In_action.chapter3_Lambda_Expressions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Exercise {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("d");
        strings.add("cb");
        strings.add("dbc");
        strings.add("rbcd");
        strings.add("ebcde");
     //   strings.sort((o1, o2) -> o1.length()-o2.length());
        System.out.println(strings);
        //• length (i.e., shortest to longest)
        strings.sort(Comparator.comparing(String::length));
        System.out.println(strings);
        //• reverse length (i.e., longest to shortest)
        strings.sort(Comparator.comparing(String::length).reversed());
        System.out.println(strings);
        //• alphabetically by the first character only
        strings.sort(Comparator.comparing(s->s.charAt(0)));
        System.out.println(strings);
        /*
        Strings that contain e first, everything else second. For now, put the code directly in the lambda.
        (Hint: remember that the body of a lambda is allowed to have curly braces and a return statement.
        See the first two lambda examples in the notes.)
         */
        strings.sort(Comparator.comparing(s->{
                if( s.charAt(0) == 'e' )
                return -1;
            return 0;
        }));
        System.out.println(strings);




    }
}
