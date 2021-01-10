package Java8_In_action.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HashMap {
    public static void main(String[] args) {
        Map<String, List<String> > map = new java.util.HashMap<>();
       map.computeIfAbsent("1", s -> new ArrayList<>()).add("1");
      //  map.getOrDefault("2", new ArrayList<>()).add("2");
        List<String> strings = map.putIfAbsent("2", new ArrayList<>());
        System.out.println(strings );
        System.out.println( map.get("1") );
        System.out.println( map.get("2") );



    }
}
