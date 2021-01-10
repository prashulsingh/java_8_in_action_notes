package Java8_In_action.chapter2_Pasing_Code_With_Behaviour_Parameterization;

public class AppleGreenColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
