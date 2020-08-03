package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements  Runnable
{
    @Override
    public void run() {
        System.out.println("coning ");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

//class  MyThread1 implements Callable<Integer>
//{
//    @Override
//    public Integer call() throws Exception {
//        System.out.println("coming here");
//        Thread.sleep(4000);
//        return 1024;
//    }
//}

class Thread1 implements Callable<Integer>
{
    @Override
    public Integer call() throws Exception {
        System.out.println("******in call");
        try {
            Thread.sleep(3000);
            }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 2;
    }
}
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> integerFutureTask = new FutureTask<Integer>(new Thread1());
        new Thread(integerFutureTask,"AA").start();  //只能有一个线程出现 只要存在就直接复用这个线程
        new Thread(integerFutureTask,"BB").start();

        System.out.println("********mian");  //主线程处理代码块
        try {
            Thread.sleep(1000);
            }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        int res = 10; //

        int rr = integerFutureTask.get(); //得到call里面的额计算结果或者处理结果  具有堵塞的功能
        System.out.println(res + rr);

//        FutureTask<Integer> futureTask = new FutureTask(new MyThread1());
//        new Thread(futureTask,"A").start();
//        new Thread(futureTask,"B").start();
//        System.out.println(Thread.currentThread().getName()+"计算完成");
//        Integer a = futureTask.get();//需要等现成里的东西处理完了才能继续走 在得到返回值的时候 get方法具有堵塞功能
//        System.out.println(a);
    }


    public  static  void run1()
    {
        new Thread(new MyThread(),"B").start();
    }
}
