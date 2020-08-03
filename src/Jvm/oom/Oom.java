package Jvm.oom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class Oom {

    //要配合修改堆的大小 不然自己的电脑堆内存太大不容易观察
    //-Xms5m -Xmx5m -XX:+PrintGCDetails
    public static void main(String[] args) {
//        SpaceHeap();
//        DirectBuffer();

        char[] c = {'1','b','c'};

        System.out.println(new String(c));
    }

    private static void DirectBuffer() {
        //由于直接操作本地内存 堆内存，GC是不能管理本地内存的 导致本地内存分配太多 而heap
        //内存充足 ，在进行本地内存申请的时候由于本地内存太小导致申请失败
        //java.lang.OutOfMemoryError: Direct buffer memory
        System.out.println("配置的maxDirectMemory" + (sun.misc.VM.maxDirectMemory() / (double)1024 / 1024) + "MB");
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }

    private static void SpaceHeap() {
        //java.lang.OutOfMemoryError: Java heap space
//        byte[] bytes = new byte[8*1024*1024];
        //同样的效果都是申请空间 导致空间不足
        List<List<Integer>> lists = new ArrayList<>();

        while(true)
        {
            lists.add(new ArrayList<>());
        }

    }
}
