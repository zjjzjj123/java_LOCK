package Jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

//引用被回收后放到应用队列中可以执行最后的命令 类似死前遗言
public class ReferenceQueueDemo {

    public static void main(String[] args) {
        Object o1 = new Object();
        //gc后的引用放到队列中去
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        WeakReference<Object> reference = new WeakReference(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());

        o1 = null;
        System.gc();
        try {
            Thread.sleep(100);
            }
        catch (InterruptedException e) {
            e.printStackTrace();
        };

        System.out.println(o1);
        System.out.println(reference.get());
        System.out.println(referenceQueue.poll());
    }
}
