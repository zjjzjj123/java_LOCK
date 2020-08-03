package executos;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"\t completableFuture2");
//            int i = 10/0;
            return 1024;
        });

        completableFuture2.whenComplete((t,u)->{
            System.out.println("-------t="+t);
            System.out.println("-------u="+u);
        }).exceptionally(f->{
            System.out.println("-----exception:"+f.getMessage());
            return 444;
        }).get();
    }
}
