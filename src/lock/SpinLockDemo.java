package lock;

import javafx.scene.control.Skin;

import java.util.concurrent.atomic.AtomicReference;

/*
自旋锁案例


* */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>(); //
    public  void myLock()
    {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " come in 等到或者直接操作");
        while(!atomicReference.compareAndSet(null,thread)) //满足条件就直接插入到这里 如果里面已经有锁 则需要等待直到里面的锁释放
        {

        }
        System.out.println(Thread.currentThread().getName() + "获取了锁");
    }

    public void UnMyLock()
    {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);//释放锁 不再占用这个空间
        System.out.println(Thread.currentThread().getName() + " 释放锁");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock(); //上锁
            try {
                Thread.sleep(5000);
                }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.UnMyLock(); //释放锁
        },"AA").start();

        new Thread(()->{
            spinLockDemo.myLock(); //想 AA获取锁之后再没有释放锁之前 这个线程一致在等待
            try {
                Thread.sleep(1000); //等到
                }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.UnMyLock(); //解锁
        },"BB").start();
    }
}
