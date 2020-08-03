package thread;


import com.sun.deploy.model.LocalApplicationProperties;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public  void increment()
    {
        lock.lock();
        try {
            while(number!=0)
                condition.await();
            number++;
            System.out.println(Thread.currentThread().getName() + " - "  + number);
            condition.signalAll();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }

    public  void decrement()
    {
        lock.lock();
        try {
            while(number==0)
                condition.await();
            number--;
            System.out.println(Thread.currentThread().getName() + " - "  + number);
            condition.signalAll();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

    }
}


/*
 *现在两个线程，
 * 可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 交替，来10轮。
 *
 * 1.判断 要用while判断 以避免使用synchronized 时 多个线程间隙拿到资源，继续往下执行 而不去重新判断导致数据错乱
 * 2.干活
 * 3.操作
 * */

public class Review {

    public static void main(String[] args)
     {

         ShareData shareData = new ShareData();

         new Thread(()->{
             for (int i=0; i<10; i++)
                shareData.increment();
         },"A").start();


         new Thread(()->{
             for (int i=0; i<10; i++)
                 shareData.decrement();
         },"B").start();

         new Thread(()->{
             for (int i=0; i<10; i++)
                 shareData.increment();
         },"C").start();


         new Thread(()->{
             for (int i=0; i<10; i++)
                 shareData.decrement();
         },"D").start();
     }
}





