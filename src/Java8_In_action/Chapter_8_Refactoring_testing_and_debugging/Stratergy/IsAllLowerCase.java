package Java8_In_action.Chapter_8_Refactoring_testing_and_debugging.Stratergy;

public class IsAllLowerCase implements  ValidationStratergy{
    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}
