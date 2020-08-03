package lock;

//可重入
//也就是同个线程在外面获得锁之后，他可以进入这个锁范围内的任意代码块 即使
//里面嵌套了锁，也能进入 会自动获取里面的锁

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FileM implements  Runnable
{
    private Lock lock = new ReentrantLock();

    public static  synchronized void readFile()
    {
        System.out.println(Thread.currentThread().getName() +  " *******readFile");
        writeFile(); //这里面有锁如果一个线程能进来就是可重入的

    }

    public static  synchronized void writeFile()
    {
        System.out.println(Thread.currentThread().getName() +  " *******writeFile");
    }
    @Override
    public void run() {
        get();
    }


    public void set()
    {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " set()");
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void get()
    {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() +" get");
            set();
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

public class ReLock {
    public static void main(String[] args) {
        FileM fileM = new FileM();
        //可重入锁验证
        new Thread(()->{
            fileM.readFile();
        },"AAA").start();  //但是他们拿的是同一个全局锁

        new Thread(()-> {
            fileM.readFile();
        },"BB").start();

        try {
            Thread.sleep(100);
            }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        //ReentrantLock可重入 默认是非公平锁

        new Thread(new FileM(),"CC").start();
        new Thread(new FileM(),"DD").start();
    }
}
