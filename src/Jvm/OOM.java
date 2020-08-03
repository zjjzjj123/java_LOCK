package Jvm;

import java.util.ArrayList;
import java.util.List;

public class OOM {
    public static void main(String[] args) {
        //-Xmx -Xms java的堆内存空间
        long maxMemory = Runtime.getRuntime().maxMemory();
        long stratMemory = Runtime.getRuntime().totalMemory();

        System.out.println("maxMemory = " + maxMemory + "字节，" + ( maxMemory / (double)1024 / 1024) + "Mb");
        System.out.println("stratMemory = " + stratMemory + "字节，" + ( stratMemory / (double)1024 / 1024) + "Mb");

    }
}
