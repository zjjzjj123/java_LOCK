package cas;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CasABA {


   static AtomicReference<Integer>  atomicReference = new AtomicReference<>(100);
   static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
    public static void main(String[] args) {

        //ABA问题 使用原子引用造成ABA问题
        //需要两个线程 一个线程修改两次 另一个线程修改一次 但是这个线程需要等待

        new Thread(()->{
//            try{Thread.sleep(100);}catch (Exception e){e.printStackTrace();}  //保证线程2启动
            System.out.println(atomicReference.compareAndSet(100,101) + " " + atomicReference.get());
            System.out.println(atomicReference.compareAndSet(101,100) + " " + atomicReference.get());
        },"AA").start();

        new Thread(()->{
            try{Thread.sleep(1000);}catch (Exception e){e.printStackTrace();}  //保证线程1执行完
            System.out.println(atomicReference.compareAndSet(100,102) + " " +atomicReference.get());
        },"BB").start();

        //产生了ABA问题 虽然完成修改 但并不代表这个过程没有事

        //使用时间戳原子引用 避免了ABA问题


        new Thread(()->{
            int stamped = atomicStampedReference.getStamp(); //
            System.out.println("CC第一次版本号： " + stamped);
            try{Thread.sleep(1000);}catch (Exception e){e.printStackTrace();}  //保证线程DD能够得到相应的版本号
            //ABA操作
            System.out.println(atomicStampedReference.compareAndSet(100,101,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp()+1));
            System.out.println("CC第二次版本号： " + atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(101,100,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp()+1));
            System.out.println("CC第三次版本号： " + atomicStampedReference.getStamp()+1); //没操作一次就将版本号添加一次
            //完成ABA操作

        },"CC").start();
        new Thread(()->{
            int st = atomicStampedReference.getStamp(); //得到版本号
            System.out.println("DD 第一次版本号：" + st);
            try{Thread.sleep(3000);}catch (Exception e){e.printStackTrace();}  //保证线程CC能够完成ABA操作
            //我去操作
            System.out.println(atomicStampedReference.compareAndSet(100,102,
                   st,
                    st+1)); //此时的版本号已经和最初他获得版本号不一样了
            System.out.println("当前最新实际版本号：" + atomicStampedReference.getStamp()); //实际的版本号和你要修改的时候的版本
            System.out.println("当前实际最新值：" + atomicStampedReference.getReference());

        },"DD").start();

    }
}
