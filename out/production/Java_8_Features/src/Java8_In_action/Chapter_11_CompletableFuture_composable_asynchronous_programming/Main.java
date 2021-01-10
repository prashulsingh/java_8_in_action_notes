package Java8_In_action.Chapter_11_CompletableFuture_composable_asynchronous_programming;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //11.1. FUTURES
        //The Future interface was introduced in Java 5 to model a result made available at some point in the future.
        // It models an asynchronous computation and provides a reference to its result that will be available when
        // the computation itself is completed. Triggering a potentially time-consuming action inside a Future
        // allows the caller Thread to continue doing useful work instead of just waiting for the operation’s result.

        /*
        Synchronous vs. asynchronous API
The phrase synchronous API is just another way of talking about a traditional call to a method: you call it,
 the caller then waits while the method computes, the method then returns, and the caller continues with the
 returned value. Even if the caller and callee were executed on different threads, the caller would still wait
  for the callee to complete; this gives rise to the phrase blocking call.

In contrast, in an asynchronous API the method returns immediately,
 or at least before its computation is complete, delegating its remaining computation to a thread, which runs
  asynchronously to the caller—hence the phrase non-blocking call. The remaining computation gives its value to
  the caller, either by calling a callback method or by the caller invoking a further “wait until the computation
  is complete” method. This style of computation is common for I/O systems programming: you initiate a disc access,
  which happens asynchronously while you do more computation, and when you have nothing more useful to do, you simply
  wait until the disc blocks are loaded into memory.


         */

        Shop shop = new Shop("s");
        Future<Double> product1 = shop.getPriceAsync("product1");
        product1.get();//Read the price of the Future or wait till it completes and value is fetched

        //11.3. MAKE YOUR CODE NON-BLOCKING
        List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
                new Shop("LetsSaveBig"),
                new Shop("MyFavoriteShop"),
                new Shop("BuyItAll"));

//        findPrices("product", shops);

        //11.3.2. Making asynchronous requests with CompletableFutures

        

    }
    //that given the name of a product returns a List of strings,
    // where each string contains the name of a shop and the price of the requested product in that shop:

//

}
//    public static List<String> findPrices(String product, List<Shop shops >){
////        //return shops.stream().map(Shop::getShopName + "," + Shop::getPrice).collect(Collectors.toList());
////        /* in filter , we can use parallel stream so that  it performs better */
////        return shops.parallelStream().map(Shop::getShopName + "," + Shop::getPrice).collect(Collectors.toList());
////    }