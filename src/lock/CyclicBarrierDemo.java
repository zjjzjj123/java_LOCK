package lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
用来循环等待线程数达到 一定的数量后
然后执行指定的线程
人齐了开会 集齐7可龙珠召唤神龙
* */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("开始开会");
        });

        for(int i=0; i<7; i++)
        {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 来了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
