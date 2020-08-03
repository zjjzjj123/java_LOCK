package Jvm;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class ReferenceDemo {

    public static void main(String[] args){

//        HardReference();
//        softEnough();
//        softNotEnough();
//        weakReference();
//        myMap();

//        WeakHashMap<Integer,String> map = new WeakHashMap<>();
//        Integer key = 2;
//        String value = "he";
//        map.put(key,value);
//
//        key = null;
//        System.out.println(map);
//
//        System.gc();  //一旦执行gc就要被回收
//        System.out.println(map);

        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();  //按道理说肯定被回收的啊
        Integer key = 2;
        String value = "WeakHashMap";

        weakHashMap.put(key, value);
        System.out.println(weakHashMap);

        key = null;
        System.out.println(weakHashMap);

        System.gc();
        System.out.println(weakHashMap + "\t" + weakHashMap.size());


    }

    private static void myMap() {
        Map<Integer,String> map = new HashMap<>();
        Integer key = 1;
        String value = "he";
        map.put(key,value);

        key = null; //这个应用为空 已经和map中的没有关系了 map中存放的是node节点
        System.gc();
        System.out.println(map + " " + map.size());
    }

    private static void weakReference() {
        Object o1 = new Object();
        //弱引用只要执行gc都会回收
        WeakReference<Object> reference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(reference.get());
        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(reference.get());
    }

    private static void softNotEnough() {
        Object o1 = new Object();
        SoftReference<Object> reference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(reference.get());

        o1=null;
        try{
            //需要配合vm参数使用 -Xms5m -Xmx5m -XX:+PrintGCDetails
            byte[] bytes = new byte[1028*1024*8]; //8mb  //内存不足弱引用自动被GC回收
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            System.out.println(o1);
            System.out.println(reference.get());
        }
    }

    private static void softEnough() {
        Object o1 = new Object();
        SoftReference<Object> reference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(reference.get()); //得到软引用

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(reference.get());
    }


    private static void HardReference() {
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;
        System.gc();
        System.out.println(o2);  //只要有一个强引用指向这个对象，这个对象都不会被垃圾回收掉
    }
}
