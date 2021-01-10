package Java8_In_action.chapter2_Pasing_Code_With_Behaviour_Parameterization;

public class AppleHeavyWeightPredicate implements  ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() >150;
    }
}
