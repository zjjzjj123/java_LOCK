package thread;


import sun.awt.SunGraphicsCallback;

//8个锁的问题
class Phone {
    //发邮件
    public static synchronized void sendEmail() throws Exception
    {
        Thread.sleep(3000); //第一个暂停4s
        System.out.println("******sendEmail");
    }

    public  static synchronized void sendMes() throws Exception
    {
        System.out.println("******sendMes");
    }

    public  void sayHello()
    {
        System.out.println("hello");
    }
}

//1.标准情况下 都是同步方法 先执行哪一个不一定 有cpu决定
//2.在主函数里面加入延时之后肯定是A先执行 进资源类里面强资源


public class EightLock {

    public static void main(String[] args) {

        Phone phone = new Phone();
//        Phone phone1 = new Phone();

        new Thread(()->{
            try {
                phone.sendEmail();
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        },"A").start();

        try {
            Thread.sleep(100);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        new Thread(()->{
            try {
                phone.sendMes();
//                phone.sayHello();
//                phone1.sendMes();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        },"B").start();
    }
}
