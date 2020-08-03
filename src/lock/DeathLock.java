package lock;

class HoldLockTread implements  Runnable
{

    private String lockA;
    private String lockB;

    public HoldLockTread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {

        synchronized (lockA)
        {
            System.out.println(Thread.currentThread().getName() + "持有锁 " + lockA +",想获取" + lockB);

            try {
                Thread.sleep(1000);
                }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB)
            {
                System.out.println(Thread.currentThread().getName() + "持有锁 " + lockB +",想获取" + lockA);
            }
        }
    }
}

public class DeathLock {

    private static Object lockA = new Object();
    private static Object lockB = new Object();
    public static void main(String[] args) {

//        death1();

        new Thread(()->
        {
            synchronized (lockA)
            {
                System.out.println(Thread.currentThread().getName() + "持有锁 " + lockA +",想获取" + lockB);
                try {
                    Thread.sleep(1000);
                    }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB)
                {
                    System.out.println(Thread.currentThread().getName() + "结束");
                }
            }
        },"A").start();

        new Thread(()->{
            synchronized (lockB)
            {
                System.out.println(Thread.currentThread().getName() + "持有锁 " + lockB +",想获取" + lockA);
                try {
                    Thread.sleep(1000);
                    }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(lockA)
                {
                    System.out.println(Thread.currentThread().getName()+"结束");
                }
            }
        },"B").start();
    }

    private static void death1() {
        String lockA = "AAA";
        String lockB = "BBB";
        new Thread(new HoldLockTread(lockA,lockB),"AA").start();
        new Thread(new HoldLockTread(lockB,lockA),"BB").start();
    }
}
