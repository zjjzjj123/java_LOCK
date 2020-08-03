import com.sun.javaws.exceptions.CacheAccessException;
import sun.font.TextRecord;
import sun.security.pkcs11.wrapper.CK_ATTRIBUTE;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.lang.Math;


class Opreat extends Object
{

    private  int number = 1;
    private  String str = "";
    private  char n = 'a';
    private String re = "";

    public synchronized  void printNumber() throws Exception
    {
        //会自动释放锁
        //判断
        if(number < 52)
        {
            if(number%2 == 0)
            {
                this.wait();
            }
            //操作
            str = str+number;
            number++;
            System.out.println(number);
            System.out.println(str);
            this.notifyAll(); //唤醒其他的锁
        }


    }

    public synchronized void printAbc() throws Exception
    {
        if(number<52)
        {
            if(number%2!=0)
            {
                this.wait();
            }
            re = n + str;
            n +=1;
            System.out.println(re);
            this.notifyAll();
        }

    }
}

/*
判断/干活/通知  --> 对于多线程

//多个线程
//操作
//资源类

两个线程，一个线程打印1-52，另一个打印字母A-Z打印顺序为12A34B...5152Z,
要求用线程间通信
* */

public class Main {

    public static void main(String[] args)
    {

        int[] arr = {1,2,3};


    }

}

