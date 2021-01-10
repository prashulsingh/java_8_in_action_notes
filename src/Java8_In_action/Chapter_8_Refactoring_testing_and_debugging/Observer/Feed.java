package Java8_In_action.Chapter_8_Refactoring_testing_and_debugging.Observer;
import java.util.*;

class Feed implements Subject{

    private final List<Java8_In_action.Chapter_8_Refactoring_testing_and_debugging.Observer.Observer> observers = new ArrayList<>();

    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    public void notifyObservers(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}
