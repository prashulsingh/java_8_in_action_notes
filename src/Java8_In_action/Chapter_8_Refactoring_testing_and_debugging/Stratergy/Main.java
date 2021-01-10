package Java8_In_action.Chapter_8_Refactoring_testing_and_debugging.Stratergy;

public class Main {
    public static void main(String[] args) {

        Validator validator = new Validator(new IsAllLowerCase());
        validator.validate("aaaa");
        validator = new Validator(new isNumeric());
        validator.validate("aaaa");

        validator = new Validator( (a)->a.matches("[a-z]+") );
        validator.validate("aaaa");






    }
}
