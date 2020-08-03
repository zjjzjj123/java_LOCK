package lock;


import javax.swing.tree.TreeCellEditor;
import java.rmi.ConnectIOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//使用sychronized的版本
class ShareDateTrad1
{
    private volatile  int number = 0;

    public synchronized void produce()
    {
        while(number != 0)
        {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 生产 " + ++number);
        this.notifyAll();
    }

    public synchronized void consumer()
    {
        while(number == 0)
        {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " 消费 " + --number);
        this.notifyAll();
    }
}

class ShareDateTrad2
{
    private volatile int number = 0;

    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void produce()
    {
        lock.lock();
        try {
                while (number!=0)
                    condition.await();
                System.out.println(Thread.currentThread().getName() + " 生产 " + ++number);
                condition.signalAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void consumer()
    {
        lock.lock();
        try {
            while (number==0)
                condition.await();
            System.out.println(Thread.currentThread().getName() + " 消费 " + --number);
            condition.signalAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

//三个线程轮流打印5次 10次 15次 并且这个轮训执行5次

class MoreCondition
{
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private volatile  int flag=1;


    public void print5()
    {
        lock.lock();
        try {

            while (flag!=1)
                c1.await();
            for(int i=0;i<5; i++)
            {
                System.out.println(Thread.currentThread().getName() + i + "次");
            }
            flag = 2;
            c2.signal();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10()
    {
        lock.lock();
        try {

            while (flag!=2)
                c2.await();
            for(int i=0;i<10; i++)
            {
                System.out.println(Thread.currentThread().getName() + i + "次");
            }
            flag = 3;
            c3.signal();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15()
    {
        lock.lock();
        try {

            while (flag!=3)
                c3.await();
            for(int i=0;i<15; i++)
            {
                System.out.println(Thread.currentThread().getName() + i + "次");
            }
            flag = 1;
            c1.signal();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}

//传统的消费生产者问题
public class Prod_ConsumerDemo {

    public static void main(String[] args) {
//        proCon();

        MoreCondition moreCondition = new MoreCondition();

        new Thread(()->{
            for(int i=0; i<10; i++)
            {
                System.out.println("第几轮 ： " + i);
                moreCondition.print5();
            }
        },"AAA").start();
        new Thread(()->{
            for(int i=0; i<10; i++)
            {
                System.out.println("第几轮 ： " + i);
                moreCondition.print10();
            }
        },"BBB").start();
        new Thread(()->{
            for(int i=0; i<10; i++)
            {
                System.out.println("第几轮 ： " + i);
                moreCondition.print15();
            }
        },"CCC").start();

    }

    private static void proCon() {
        ShareDateTrad1 shareDateTrad1 = new ShareDateTrad1();
        ShareDateTrad2 shareDateTrad2 = new ShareDateTrad2();

        new Thread(()->{
            for(int i=0; i<5; i++)
            {
                shareDateTrad2.produce();
            }

        },"AAA").start();

        new Thread(()->{
            for(int i=0; i<5;i++)
            {

                shareDateTrad2.consumer();
            }
        },"BBB").start();
    }

}
