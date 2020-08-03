package lock;
//共享资源的互斥使用
//并发数 多线程去访问多个资源 当所有资源都被抢占的时候，后来的需要等待线程执行完成再去占用资源
//吃火锅排队 3个车位 6个车想占用

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for(int i=0; i<6; i++)
        {
            new Thread(()->{
                try {
                        semaphore.acquire();  //线程请求资源
                        System.out.println(Thread.currentThread().getName() + " 占领车位了");
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + " 离开车位了");
                    }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    semaphore.release(); //直接 如果不释放不会自动释放
                }
            },String.valueOf(i)).start();
        }
    }
}
