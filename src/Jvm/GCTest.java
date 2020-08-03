package Jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GCTest {

    public static void main(String[] args) {
        GC1();
//
//        String str = "www.atguigu.com"; //说明堆里面是有常量池的
//        while (true)
//        {
//            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999) ;
//        }

//        List<List> list = new ArrayList<>();
//        while (true)
//        {
//            list.add(new ArrayList());
//        }

        System.out.println("hello GC");
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void GC1() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long talMemory = Runtime.getRuntime().totalMemory();

        System.out.println("核心数" + Runtime.getRuntime().availableProcessors());
        //heap可能得到最大内存是本机内存的 1/4
        System.out.println("MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 / 1024) + "MB");
        //虚拟机中的内存总量 是当前电脑的1/64  也就是初始化内存
        System.out.println("talMemory = " + talMemory + "（字节）、" + (talMemory / (double)1024 / 1024) + "MB");
    }
}
