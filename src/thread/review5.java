package thread;

import java.util.concurrent.CountDownLatch;

public class review5 {
    public static void main(String[] args) throws Exception{


        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i=1; i<=6;i++)
        {
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+ "  同学走了" );
                countDownLatch.countDown();
            },i+"").start();
        }

        countDownLatch.await(); //一直等待我里面的计数器为空 为空往下执行
        System.out.println(Thread.currentThread().getName()+ "——————————————————————班长走人");
    }
}
