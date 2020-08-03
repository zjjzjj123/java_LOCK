package thread;


import jdk.nashorn.internal.codegen.CompilerConstants;
import sun.reflect.generics.tree.Tree;

import java.util.concurrent.*;

class  MyThread4 implements Runnable
{
    @Override
    public void run() {
        System.out.println("in runnable");
    }
}

class MyThread5 implements Callable
{
    @Override
    public Object call() throws Exception {
       return 1024;
    }
}
public class Review4 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Thread t = new Thread(new MyThread4());
        t.start();

//        Thread t1 = new Thread(new MyThread5());  //需要找到中间 实现类
        //FutureTask实现了runabble  他的构造方法既可以接受runnable 也可callable 只是一个具有返回值一个没有返回值

        FutureTask future1 = new FutureTask(()->
        {
            System.out.println("----coming callable");
            TimeUnit.SECONDS.sleep(2);  //类似一个好事任务
            return 1024;
        });


        new Thread(future1).start();


        System.out.println("等待完毕");  //主线程去执行其他任务，让futuretask在后台去计算耗时的工作
        future1.get();
    }
}
