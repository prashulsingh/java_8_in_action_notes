package Java8_In_action.Chapter_10_Using_Optional_as_a_better_alternative_to_null;

import java.util.Optional;

public class Person {
    private Car car;
    public Car getCar(){
        return car;
    }

    private Optional<Car> _car;

    public Optional<Car> get_car(){return _car;}
}
