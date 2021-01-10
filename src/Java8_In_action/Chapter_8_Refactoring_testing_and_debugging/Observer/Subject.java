package Java8_In_action.Chapter_8_Refactoring_testing_and_debugging.Observer;

interface Subject{
    void registerObserver(Observer o);
    void notifyObservers(String tweet);
}
