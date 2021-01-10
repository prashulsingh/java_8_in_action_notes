package Java8_In_action.Chapter_11_CompletableFuture_composable_asynchronous_programming;

import lombok.Data;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Data
public class Shop {
    private final String shopName;
    private final int price=0;
//    public double getPrice(String product) {
//        return calculatePrice(product);
//    }

//    public Future<Double> getPriceAsync(String product) {
//        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//        new Thread( ()->{
//            futurePrice.complete(calculatePrice(product));
//        }).start();
//        return futurePrice;
//    }

/*
The supplyAsync method accepts a Supplier as argument and returns a Completable-Future
that will be asynchronously completed with the value obtained by invoking that Supplier.
This Supplier will be run by one of the Executors in the ForkJoinPool, but you can specify
 a different Executor by passing it as a second argument to the overloaded version of this method.
 */
    public Future<Double> getPriceAsync(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        return Math.random()* product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
