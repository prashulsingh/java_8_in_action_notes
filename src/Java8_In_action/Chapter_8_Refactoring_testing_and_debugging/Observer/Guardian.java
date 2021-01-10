package Java8_In_action.Chapter_8_Refactoring_testing_and_debugging.Observer;

class Guardian implements Observer{
    public void notify(String tweet) {
        if(tweet != null && tweet.contains("queen")){
            System.out.println("Yet another news in London... " + tweet);
        }
    }
}