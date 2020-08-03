package lock;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/*
* synchronounsQueue 只能存储一个线程 当存在是要么取走要么阻塞
* */
public class SynchronousDemo {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();


        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                blockingQueue.put(1);  //会在这里堵塞

                System.out.println(Thread.currentThread().getName() + " put 2");
                blockingQueue.put(2);

                System.out.println(Thread.currentThread().getName() + " put 3");
                blockingQueue.put(3);

            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"AA").start();

        new  Thread(()->{
            try {
                    System.out.println(Thread.currentThread().getName() + " take 1");
                    blockingQueue.take();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " take 2");
                    blockingQueue.take();
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " take 3");
                    blockingQueue.take();
                    Thread.sleep(1000);
                }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BB").start();
    }
}
