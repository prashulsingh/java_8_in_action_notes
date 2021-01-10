package Java8_In_action.Chapter_10_Using_Optional_as_a_better_alternative_to_null;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        //We also clarify how this migration from nulls to Optionals requires you to rethink the way you deal with
        // optional values in your domain model.
        //new Person().getCar().getInsurance().getName();

        //what if person is null, or car is null or insurance is null

        //NullPointerException check, less readability

//        Person person = new Person();
//        if( person != null )
//        {
//            if( person.getCar() !=null )
//            {
//                if( person.getCar().getInsurance() != null )
//                {
//                    //extract the name of the insurance
//                }
//            }
//        }

        //Using Option is a good thing
        //The fact that a person references an Optional<Car>, and a car an Optional<Insurance>, makes it explicit in
        // the domain that a person might or might not own a car, and that car might or might not be insured.
        Optional<Car> car = new Person().get_car();

        car.isPresent();

        car.ifPresent(car1 -> {
            System.out.println("Car Present");
        });

        Car car1 = car.orElse(new Car());

        //Optional from a non-null value
        Optional<Car> car3 = Optional.of(new Car());

        //Optional from a null value
        Optional<Car> car2 = Optional.ofNullable(new Car());

        Optional<Insurance> optInsurance = Optional.ofNullable(new Insurance());
        Optional<String> insuranceName = optInsurance.map(Insurance::getName);

        Optional<Person> person = Optional.ofNullable(new Person());

        Optional<Car> car4 = person.map(Person::get_car).get();
        String not_found = person.flatMap(Person::get_car)
                .flatMap(Car::get_insurance).
                        map(Insurance::getName).orElse("Not Found");


        //10.3.6. Rejecting certain values with filter

        Insurance insurance = new Insurance();
        if(insurance != null && "CambridgeInsurance".equals(insurance.getName())){
            System.out.println("ok");
        }

        Optional<Insurance> insuranceOptional = Optional.ofNullable(new Insurance());
        insuranceOptional.filter(insurance1 -> "CambridgeInsurance".equals(insurance.getName()))
                .ifPresent(System.out::println);

    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(
            Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance_UsingLambda(
            Optional<Person> person, Optional<Car> car) {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    private Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance();
    }



}
