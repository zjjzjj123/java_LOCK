package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Review8 {

    //抢车位
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);  // 信号量最多三个
        for(int i=1; i<=6; i++)
        {
            new Thread(()->{

                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "占领");
                    TimeUnit.SECONDS.sleep(2); //2秒才能处理完
                    System.out.println(Thread.currentThread().getName() + "离开");
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally {
                    semaphore.release(); //当有人离开 时 信号量-1
                }
            },i+"").start();
        }
    }
}
