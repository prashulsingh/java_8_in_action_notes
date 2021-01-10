package Java8_In_action.chapter5_Working_With_Streams;

import lombok.Data;

@Data
public class Trader{

    private final String name;
    private final String city;

    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }
}
