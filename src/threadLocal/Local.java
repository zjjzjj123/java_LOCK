package threadLocal;


/*
*  threadLocal 就是保证每个线程对应的变量副本是相互隔离的
* 利用空间换时间的策略 增加高并发
* 使用synchronized则是直接锁住这个变量 所有根据获取锁的顺序来操作变量
* */

import java.util.concurrent.ThreadLocalRandom;

public class Local {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();  //使用到了threadLocal
    private  String content;

    public  String getContent() {
        String s = threadLocal.get();
        return s;
    }

    //为啥使用同步方法不行呢
    public  void setContent(String content) {
        threadLocal.set(content);
    }

    public static void main(String[] args) {
//        synMethod();
        Local l = new Local();


        for(int i=0; i<3; i++)
        {
            new Thread(()->{
                l.setContent(Thread.currentThread().getName() + "的数据");
//                thread.set(l.setContent(Thread.currentThread().getName()+"的数据"));
                System.out.println("---------------");
                System.out.println(Thread.currentThread().getName() + "-->" + l.getContent());
            },String.valueOf(i)).start();
        }
    }

    private static void synMethod() {
        Local l = new Local();

        for(int i=0; i<20; i++)
        {
            new Thread(()->{
                synchronized (Local.class)  //每次只能一个线程进来 操作公共数据 //因为同步方法加锁了 但是和现在这段代码没关系
                {
                    l.setContent(Thread.currentThread().getName() + "的数据");
                    System.out.println("----------------------------");
                    System.out.println(Thread.currentThread().getName() + "------->"+l.getContent());
                }

            },String.valueOf(i)).start();
        }
    }
}
