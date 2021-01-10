package Java8_In_action.chapter5_Working_With_Streams;
import java.util.*;
import java.util.stream.Collectors;


public class StreamQuiz {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

       // 1.  Find all transactions in the year 2011 and sort them by value (small to high).

        List<Transaction> transactions_2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)).collect(Collectors.toList());

        System.out.println(transactions_2011.toString());

        //2.  What are all the unique cities where the traders work?
        List<String> uniqueCities = transactions.stream().
                map(transaction -> transaction.getTrader().getCity())
                .distinct().collect(Collectors.toList());
        System.out.println(uniqueCities.toString());

        //3.  Find all traders from Cambridge and sort them by name.
        System.out.println( transactions.stream().filter(transaction -> transaction.getTrader().getCity().compareTo(
                "Cambridge")==0)
                .map(Transaction::getTrader).sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList()) );


        //  Return a string of all traders’ names sorted alphabetically.

        System.out.println( transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct().sorted(Comparator.comparing(String::toLowerCase))
                .reduce("", (s, s2) -> s+s2));

        //first distinct and then sorting makes a huge difference

        System.out.println( transactions.stream().map(transaction -> transaction.getTrader().getName())
                .distinct().sorted(Comparator.comparing(String::toLowerCase))
                .collect(Collectors.toList()) );

        //5.  Are any traders based in Milan?
        boolean milan = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().compareTo("Milan") == 0);

        System.out.println(milan);

       // 6.  Print all transactions’ values from the traders living in .

        System.out.println( transactions.stream().
                filter(transaction -> transaction.getTrader().getCity().compareTo("Cambridge")==0)
                .collect(Collectors.toList()) );

        //7.  What’s the highest value of all the transactions?

        System.out.println( transactions.stream().map(Transaction::getValue).reduce(Integer::max) );


        //8.  Find the transaction with the smallest value.

        System.out.println( transactions.stream().map(Transaction::getValue).reduce(Integer::min) );




    }
}
