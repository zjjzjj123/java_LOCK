package lock;

//读写锁实例
/*
读时共享 写时复制 生活的例子看排名 看的时候共享 修改的时候复制只能有一个人去修改
读写map来实现 每次写操作都应该是完整的 而读操作无所谓可以随便读
*/

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Cache
{
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(); //

    private volatile Map<String, Object>  map = new Hashtable<>();

    public void put(String key,Object value)
    {
        rwLock.writeLock().lock();  //写入锁  对于锁而言 每一把锁都要匹配一把解锁 可以多把锁 但是一定要匹配不然会运行出错
        rwLock.writeLock().lock();
        try {
                System.out.println(Thread.currentThread().getName() + "正在写入......");
                try {
                    Thread.sleep(100);  //模拟写的过程比较慢
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(key,value);

                System.out.println(Thread.currentThread().getName() + "写入完成......");
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key)
    {
        rwLock.readLock().lock();
        try {
                System.out.println(Thread.currentThread().getName() + "正在读取......");

                Object result = map.get(key);

                System.out.println(Thread.currentThread().getName() + "读取完成...... result = " + result);
        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }
}

public class RWLock {

    public static void main(String[] args) {
        Cache cache = new Cache();
        for(int i=0; i<5; i++)
        {
            final int  tempInt = i;
            new Thread(()->{
                cache.put(String.valueOf(tempInt),tempInt);
            },String.valueOf(i)).start();
        }
        for(int i=0; i<5; i++)
        {
            final int  tempInt = i;
            new Thread(()->{
                cache.get(String.valueOf(tempInt));
            },String.valueOf(i)).start();
        }
    }
}
