package lock;


import sun.reflect.generics.tree.Tree;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

//callable具有返回值的多线程接口类似runnable 里面的方法是call
class Call implements Callable<Integer>
{
    @Override
    public Integer call() throws Exception {

        TimeUnit.SECONDS.sleep(5); //任务是5完成
        return 102;
    }
}
public class CallAbleDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        FutureTask<Integer> futureTask = new FutureTask<>(new Call());
//        new Thread(futureTask,"AAAA").start();
//
//
//
//        TimeUnit.SECONDS.sleep(2);
//        int ret1 = 10;
//        System.out.println("********mian 的任务完成了");
//        int ret = futureTask.get();//  具有堵塞的效果
//
//
//        System.out.println("都完成了" + (ret + ret1));
//        System.out.println(sum());
        ListNode head = new ListNode(1);
        ListNode newHead = head;
        int[] a = {1 ,2 , 3, 3, 4, 4, 4 ,5};
        for(int b : a)
        {
            head.next = new ListNode(b);
            head = head.next;
        }
        ListNode gg = noCopy(newHead);
        while(gg != null)
        {
            System.out.print(gg.val + " ");
            gg = gg.next;
        }

    }

    public static long sum()
    {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        long ret = 0;
        for(int i=1; i<=b; i++)
        {
            ret += a/i;
        }
        return ret;
    }

    //1 1 2 2 3 3 4 4 4 5
    public static ListNode noCopy(ListNode head)
    {
        ListNode newHead = head;
        while( head != null)
        {
            if(head.next == null)
            {
                head.next = new ListNode(head.val);
                head.next.next = null;
                return newHead;
            }
            else if(head.val == head.next.val) //这个有重复的可能性 怎么维护重复的问题
            {
                ListNode pre = head.next; //判断重复
                ListNode cor = head.next.next;
                while(cor!=null && cor.val == pre.val) //防止重复的出现
                {
                    cor = cor.next; //一致找到不相等或者为空的
                }
                pre.next = cor; //只要改变head的指针即可 有重复的时候需要将pre和cor连接上
                head = cor;
            }
            else  if(head.val != head.next.val) //插入
            {
                //插入一个数值
                ListNode after = head.next;
                head.next = new ListNode(head.val);
                head.next.next = after;
            }
        }
        return newHead;
    }
}
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
