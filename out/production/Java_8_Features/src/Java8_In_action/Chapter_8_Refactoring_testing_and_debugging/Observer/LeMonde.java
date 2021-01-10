package Java8_In_action.Chapter_8_Refactoring_testing_and_debugging.Observer;

class LeMonde implements Observer{
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("wine")){
            System.out.println("Today cheese, wine and news! " + tweet);
        }
    }
}