package thread;


class  Resource
{
    int number = 0;

    public synchronized  void up() throws Exception
    {
        while(number != 0)
            this.wait(); //等带被消费
        //干活
        number++ ; //增加之后需要通知别人来消费
        System.out.println(Thread.currentThread().getName()+ "  " + number);
        this.notifyAll(); //通知所有线程准备过来消费
    }
    public synchronized void down() throws Exception
    {
        while(number ==0 )  //等待生产  判断条件一定要用while来 在多个线程争抢一个对象的时候，释放锁 抢到锁之后需要重现判断状态
                            //if不能够重新判断 如果他拿到锁会在原来等待的地方继续往下执行
            this.wait();

        number--;  //消费了 通知他们去生产
        System.out.println(Thread.currentThread().getName()+ "  " + number);
        this.notifyAll();
    }
}

//判断 - 干活 - 通知

/*
* 两个线程操作一个变量，实现两个线程对同一个资源一个进行加1操作，
* 另外一个进行减1操作，且需要交替实现，变量的初始值为0。
* 即两个线程对同一个资源进行加一减一交替操作。*/
//生产者和消费者的问题
//生产一个消费一个 以此循环
public class ProduceOrConsumer {


    public static void main(String[] args) {
        Resource rs = new Resource();

        new Thread(()->{

            for(int i=0; i<10;i++)
            {
                try
                {
                    rs.up();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        },"A").start();
        new Thread(()->{
            for(int i=0; i<10; i++)
            {
                try
                {
                    rs.down();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        },"B").start();

        new Thread(()->{

            for(int i=0; i<10;i++)
            {
                try
                {
                    rs.up();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        },"C").start();
        new Thread(()->{
            for(int i=0; i<10; i++)
            {
                try
                {
                    rs.down();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        },"D").start();

    }


}
