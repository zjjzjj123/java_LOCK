package thread;


import com.sun.xml.internal.ws.addressing.ProblemHeaderQName;

import java.util.concurrent.TimeUnit;

class PhoneObj
{
    public static synchronized void sendEmail() throws InterruptedException {

        System.out.println("******sendEmail");
}

    public static synchronized void sendMes() throws InterruptedException
    {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("******sendMes");
    }

    public void sendHello()
    {
        System.out.println("******sendHello");
    }
}

/*
* 各类锁的问题关于 synchronized
 *
 * @Description: 8锁
 *
 1 标准访问，先打印短信还是邮件
 2 停4秒在短信方法内，先打印短信还是邮件
 3 新增普通的hello方法，是先打短信还是hello
 4 现在有两部手机，先打印短信还是邮件
 5 两个静态同步方法，1部手机，先打印短信还是邮件
 6 两个静态同步方法，2部手机，先打印短信还是邮件
 7 1个静态同步方法,1个普通同步方法，1部手机，先打印短信还是邮件
 8 1个静态同步方法,1个普通同步方法，2部手机，先打印短信还是邮件
 * ---------------------------------
 *
* */
public class Review3 {

    public static void main(String[] args) throws InterruptedException {
        PhoneObj  obj = new PhoneObj();
        PhoneObj  obj1 = new PhoneObj();
        new Thread(()->{
            try {
                obj.sendMes();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(100);

        new Thread(()->{
            try {
                obj1.sendEmail();
//                obj.sendHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
//            obj.sendHello();
        }).start();

    }
}
