package Java8_In_action.Chapter_8_Refactoring_testing_and_debugging.Observer;

public class Main {
    public static void main(String[] args) {
        Feed f = new Feed();
        f.registerObserver(new NYTimes());
        f.registerObserver(new Guardian());
        f.registerObserver(new LeMonde());
        f.notifyObservers("The queen said her favourite book is Java 8 in Action!");


        f.registerObserver(Main::notify);
    }

    private static void notify(String tweet) {
        System.out.println("The queen said her favourite book is Java 8 in Action!");
    }
}
