package lock;
/*
* 使用阻塞队列实现生产者消费者问题
* */


import java.lang.reflect.Array;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource
{
//    private volatile boolean flag = true;
//    private BlockingQueue<String> blockingQueue = null;
//    private AtomicInteger atomicInteger = new AtomicInteger();
//
//    public  MyResource(BlockingQueue<String> blockingQueue)
//    {
//        System.out.println(blockingQueue.getClass().getName());
//        this.blockingQueue = blockingQueue;
//    }
//
//
//    //加入队列
//    public  void produce()
//    {
//        String data = null;
//        boolean ret;
//        while(flag)  //需要一致循环 对于一个线程来说
//        {
//
//            try {
//                data = atomicInteger.incrementAndGet()+"";
//                ret = blockingQueue.offer(data,2 , TimeUnit.SECONDS);
//                if(ret)
//                {
//                    System.out.println(Thread.currentThread().getName() + " "+ data + " 加入队列成功");
//                }
//                else
//                {
//                    System.out.println(Thread.currentThread().getName() + " "+ data + " 加入队列失败");
//                }
//                Thread.sleep(1000);  //不让他生产那么快
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println(Thread.currentThread().getName() + "线程结束 不再生产");
//    }
//
//
//    public  void consumer()
//    {
//        String data=null;
//        while(flag)
//        {
//            try {
//
//                data = blockingQueue.poll(2,TimeUnit.SECONDS);
//                if(data == null || data.equalsIgnoreCase(""))
//                {
//                    flag = false; //读取不到了 就直接停止
//                    System.out.println(Thread.currentThread().getName() + "超过两秒没能成功读取队列结束任务");
//                    return;
//                }
//                System.out.println(Thread.currentThread().getName() + " 读取队列成功"+ data);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void stop()
//    {
//        this.flag = false;
//    }

    private volatile boolean FLAG = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        System.out.println(blockingQueue.getClass().getName());
        this.blockingQueue = blockingQueue;
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;

        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
            }
            Thread.sleep(1000);
        }
        System.out.println("大老板叫停，" + Thread.currentThread().getName() + "生产结束.");
    }

    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t超过2秒没取到蛋糕，消费退出.");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列蛋糕" + result + "成功.");
        }
    }

    public void stop() {
        this.FLAG = false;
    }

}


public class Prod_ConsBlockingQueue {

    public static void main(String[] args) {

        MyResource myResource = new MyResource(new ArrayBlockingQueue <String>(3));

        new Thread(()->{
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(()->{
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"BB").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();  //结束任务
    }
}
