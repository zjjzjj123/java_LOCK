package thread;

import com.sun.corba.se.impl.presentation.rmi.DynamicMethodMarshallerImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache
{
    private Map<String,Object> map = new HashMap<>();

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public void put(String key,Object value)  //不加锁 出现一个写操作还没有写完另一个操作就对
    {
        readWriteLock.writeLock().lock();
        try
        {
            System.out.println(Thread.currentThread().getName() + "   ----写入数据");
            Thread.sleep(200);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "   ----写入完成");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
             readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key)
    {

        readWriteLock.readLock().lock();  //读的时候可以随意读取啊 加上和不加都一样
        try {
            System.out.println(Thread.currentThread().getName() + "   ----读出数据");
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "   ----读出完成");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            readWriteLock.readLock().unlock();
        }
    }
}


//读写锁
//读时共享 写的时候唯一
public class RandWLock {

    public static void main(String[] args) {


        Cache cache = new Cache();
        for(int i=0; i<5; i++)
        {
            final int temp =i;
            new Thread(()->{
                cache.put(temp+"", temp);
            },i +"").start();
        }

        for(int i=0; i<5; i++)
        {
            final int temp = i;

            new Thread(()->{
                cache.get(temp+"");
            },i+"").start();
        }
    }
}
