package Java8_In_action.Chapter_8_Refactoring_testing_and_debugging.Observer;

class NYTimes implements Observer{
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("money")){
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}