package thread;


import java.util.concurrent.*;

//当阻塞队列满之后 开启最大线程数的时候 执行的是当前到达的线程任务
// 不是阻塞队列里面的
public class ThreadPoolDemo2 {

    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
                //丢弃等待最久的 丢弃后面来的 如果这边办完了 就再占领线程
        );
        try {
                for(int i=0; i<10; i++)
                {
                    final int temp = i;
                    threadPool.execute(()->{
                        System.out.println(Thread.currentThread().getName() + "窗口，服务" + temp);
                        try {
                            Thread.sleep(200);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
