package thread;


/*
* 1、有顺序通知，需要有标识位

2、有一个锁Lock，3把钥匙Condition

3、判断标志位

4、输出线程名+第几次+第几轮

5、修改标志位，通知下一个
* */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Coun
{
    private Lock lock = new ReentrantLock();  //有顺序的执行所有就需要有顺序的开锁 设置标志位
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private  int flag = 1; //一定要先修改标志位然后再去释放锁

    public  void print5(int t)  //增明写成一份函数
    {
        lock.lock();
        try
        {
            while(flag != 1)  //如果不该比执行
                c1.await();
            for(int i=1; i<=5; i++)
            {
                System.out.println(Thread.currentThread().getName()+ " 第 " + i +" 次 ，" + "第 " +
                        t + "轮");
            }
            flag = 2;
            c2.signal();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public  void print10(int t)
    {
        lock.lock();
        try
        {
            while(flag != 2)  //如果不该比执行
                c2.await();
            for(int i=1; i<=10; i++)
            {
                System.out.println(Thread.currentThread().getName()+ " 第 " + i +" 次 ，" + "第 " +
                        t + "轮");
            }
            flag = 3;
            c3.signal();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }

    public  void print15(int t)
    {
        lock.lock();
        try
        {
            while(flag != 3)  //如果不该比执行
                c3.await();
            for(int i=1; i<=15; i++)
            {
                System.out.println(Thread.currentThread().getName()+ " 第 " + i +" 次 ，" + "第 " +
                        t + "轮");
            }
            flag = 1;
            c1.signal();
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

public class Review2 {

    public static void main(String[] args) {
        Coun coun = new Coun();

        new Thread(()->{

            for(int i=1;i<5 ;i++)
            {
                coun.print5(i);
            }
        },"A").start();


        new Thread(()->{

            for(int i=1;i<5 ;i++)
            {
                coun.print10(i);
            }
        },"B").start();


        new Thread(()->{

            for(int i=1;i<5 ;i++)
            {
                coun.print15(i);
            }
        },"C").start();


    }
}
