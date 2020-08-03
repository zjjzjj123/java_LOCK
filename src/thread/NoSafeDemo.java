package thread;


import java.util.concurrent.locks.ReentrantLock;

class Ticket
{
    private int number=30;
    ReentrantLock lock = new ReentrantLock();

    public void saleTicket()  //加锁之后每次进来操作线程的时候只能有一个线程操作
    {
        lock.lock();
        try
        {
            if(number > 0)
            {
                System.out.println(Thread.currentThread().getName() + "卖出去第" + number-- +
                        ",还剩" + number + "张票") ;
            }
        }
        catch (Exception e)
        {
            throw  new RuntimeException(e);
        }
        finally {
            lock.unlock();
        }

    }
}



//函数是借口
@FunctionalInterface
interface Foo
{
    void foo1(int x);
    default void foo2()
    {
        System.out.println("moren 方法");
    }

    public static  void foo3()
    {
        System.out.println("静态方法");
    }
}

/*
* 1.函数接口编程
* 2.一个方法
* 3.静态方法
* 4.默认方法
*
* //线程 操作 资源类
* 多个线程操作资源类
* */
public class NoSafeDemo {

    //买票30张
    public static void main(String[] args) {

//        Ticket ticket = new Ticket();
//
//        new Thread(()->{for(int i=0; i<40; i++) ticket.saleTicket();},"A").start();
//        new Thread(()->{ for(int i=0; i<40; i++) ticket.saleTicket();},"B").start();
//        new Thread(()->{ for(int i=0; i<40; i++) ticket.saleTicket(); },"C").start();
//
        Foo f1 = (x) -> {
            System.out.println("f1" + x);
        };
        f1.foo1(2);

        f1.foo2();
        Foo.foo3(); //通过类直接调用

    }
}
