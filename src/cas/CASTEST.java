package cas;


//可见性 volatile 禁止指令重排（在汇编的语言的情况下）
//如何证明不保证原子性呢  i++问题 多个线程去i++ 可能会出现写重复 写覆盖的问题

import java.util.concurrent.atomic.AtomicInteger;

class  MyNum
{
    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger(); //默认是0

    public void setNum()
    {
        number = 30;
    }

    public synchronized void  addOne() //可以解决
    {
        number++;
    }

    public void  addOneAtomic()
    {
        atomicInteger.getAndIncrement(); //先获得再加1 底层使用的是cas
    }



}
public class CASTEST {
    public static void main(String[] args) {
        //可见性
//        volatileAtomic();
        //不保证原子性
        MyNum myNum = new MyNum();
        for(int i=0; i<20; i++)
        {
            new Thread(()->
            {
                for(int j=0; j<1000; j++)
                {
                    myNum.addOne();
                    myNum.addOneAtomic();
                }
            },String.valueOf(i)).start();
        }

        //要等上面的线程算完
        while (Thread.activeCount() > 2)
        {
            Thread.yield(); //释放时间片 自己和其他线程重新去争取 而wait则是自己解锁 让其他人来强
        }

        System.out.println("reslut: " + myNum.number);
        System.out.println("Atomic: " + myNum.atomicInteger) ;

    }

    private static void volatileAtomic() {
        MyNum myNum = new MyNum();
        new Thread(()->
        {
            System.out.println("in");
            try{Thread.sleep(100);}catch (Exception e){e.printStackTrace();}
            myNum.setNum();
            System.out.println(Thread.currentThread().getName() + " number = " + myNum.number);
        },"AA").start();

        while(myNum.number == 0)
        {}
        System.out.println("missoin");
    }
}
