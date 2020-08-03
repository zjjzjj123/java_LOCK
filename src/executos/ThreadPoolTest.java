package executos;


import java.util.concurrent.*;

/*
* 自定义线程池
* */
public class ThreadPoolTest {
    public static void main(String[] args) {

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2l,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),

                //拒接策略
//                new ThreadPoolExecutor.AbortPolicy()  //会直接抛出拒绝执行异常
//                new ThreadPoolExecutor.CallerRunsPolicy()  //当主线程里面处理不完需求的时候阻塞满  最大线程数满了 那就直接回调到main
//                new ThreadPoolExecutor.DiscardPolicy() //有些业务直接丢掉 无法完成 对于一些允许任务丢失的逻辑业务来说最好
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        try
        {
            for(int i=0; i<10; i++)
            {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() + " 办业务");
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
