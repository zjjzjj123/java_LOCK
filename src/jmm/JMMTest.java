package jmm;


//java内存模型
//主内存 方法的内存
//使用voliate 通过主内存通知另一个线程对公共内存里的值进行了修改
//jmm 保证原子性 可见性
//volatile 可见性 不保证原子性  也就是多线程访问可能出现问题
//如何解决原子性 1.synchronized 或者 atomicInteger juc下的原子类

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

class  MyNumber
{
    int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger(); //默认值为0
    public void addNumber()
    {
        number = 1024;
    }

    public  void addNum()
    {
        number ++ ;
    }


    public  void atomicMyAdd()
    {
        atomicInteger.getAndIncrement();  //底层使用的是cas问题

    }
}
public class JMMTest {

    public static void main(String[] args) {
        //new一个对象 就是将这个对象加到主物理内存里了 也就是jvm申请的堆内存
        automic();
//        automic_volatile();

    }

    //当方法加入synchronized 时结果就一样可 因为synchronized是具有原子性的
    private static void automic_volatile() {
        //多个线程去访问这个数值，最后输出 如果和我们想象的不一样就说明操作有损失
        MyNumber myNumber = new MyNumber();

        //20个线程 每个线程循环1000次
        for(int i=0; i<20; i++)
        {
            new Thread(()->
            {
                for(int j=0; j<1000; j++)
                {
                    myNumber.addNum();
                    myNumber.atomicMyAdd();
                }
            },String.valueOf(i)).start();
        }
        //等待上面的线程执行完成
        while(Thread.activeCount() > 2) //主线程 + GC的时候说明上面线程执行完了
        {
            Thread.yield();  //让出时间片 让其他线程执行 也可能自己执行
        }
        System.out.println("计算后的Integr number值: " + myNumber.number);
        System.out.println("计算后的Atomic number值: " + myNumber.atomicInteger);
    }

    //原子性验证
    private static void automic() {
        MyNumber myNumber = new MyNumber();
        new Thread(()->{
            System.out.println("thread ... come in");
            try { Thread.sleep(3000);}catch (Exception e) { e.printStackTrace(); }
            myNumber.addNumber(); //更新主内存中的变量
            System.out.println(Thread.currentThread().getName() + " update number --> number = " + myNumber.number);
        },"AAA").start();

        while(myNumber.number == 0)
        {
            //如果有线程修改这个值 那么我就会跳出循环 验证jmm可见性 使用volatile 用来修饰操作的变量
        }
        System.out.println("mission over");
    }
}
