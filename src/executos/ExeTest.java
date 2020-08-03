package executos;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExeTest {

    public static void main(String[] args) {

//        ExecutorService  threadPool = Executors.newFixedThreadPool(4);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();


        try{
            for(int i=0; i<22; i++)
            {
//                TimeUnit.SECONDS.sleep(1);
                threadPool.execute(()->
                {
                    System.out.println(Thread.currentThread().getName() + "进来了");
                });
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
        }

    }
}
