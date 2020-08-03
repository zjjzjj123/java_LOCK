package lock;
import java.util.concurrent.CountDownLatch;

//countDownLatch类似倒计时 当每次调用down的时候里面的额数值都会-1 知道为0的时候
//await才解除等待 去做该做的事 如火箭发射倒计时 ，班长最后关门走人
public class CountDownLatchDemo {

    public static void main(String[] args) {

        //使用枚举
        CountDownLatch countDownLatch = new CountDownLatch(6);

        //这个为啥有问题
        //不能把CountCountry.forEach_CountCountry(tempInt).getCounty()这个写在线程的名称上为啥呢
        //可以写 我爸枚举里面的下标搞错了
        for(int i=1; i<7; i++)
        {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "  被消灭了");
                countDownLatch.countDown();
            },CountCountry.forEach_CountCountry(i).getCounty()).start(); //CountCountry.forEach_CountCountry(tempInt).getCounty()
        }
//        for (int i = 1; i <= 6; i++) {
//            new Thread(CountCountry.forEach_CountCountry(i).getCounty()){
//                @Override
//                public void run() {
//                    System.out.println(getName() + "\t国被灭.");
//                    countDownLatch.countDown();
//                }
//            }.start();
//        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "  统一了啊");

    }

    private static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(6); //

        for(int i=0; i<6;i++)
        {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " go.........");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
            //其实在任何地方都可以用只是为什么不直接用一个变量倒计时呢 如果使用单纯的变量会出现线程安全问题
//            System.out.println(i + " go.........");
//            countDownLatch.countDown();
        }
        //直到上面的任务都完成，也就是countDownLatch里面完成我结束等待 去做自己的事
        try {
            countDownLatch.await(); // 不满足就会被阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " go..........");
    }
}
