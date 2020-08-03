package thread;

import javax.swing.text.TableView;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Review7 {

    private final static int num = 7;
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(num,()->{
            System.out.println("------------数量达到进来nba"); });  //到达一定条件才能执行 后面的县城
        for(int i=1; i<=num; i++)
        {
            new Thread(()->{

                try {
                    System.out.println(Thread.currentThread().getName() + "到达");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            },i+"").start();
        }
        System.out.println("------------主线程结束");  //主线程不会等待 不会出现堵塞
    }
}
