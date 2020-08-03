package cas;

import com.sun.javafx.image.IntPixelGetter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;


//访问同一个list的由于不线程时不安全的所以可能出现concureentModificationException

public class ArrayNoSafeDemo {

    public static void main(String[] args) {
        //均是线程不安全的
//        Collections.synchronized**() list set map 都有
        List<Integer> list = new ArrayList<>();  //可以使用Vector 或者collections里面的
        //对应的线程安全
        List<Integer> list1 = new CopyOnWriteArrayList<>();
        Set<String> set = new HashSet<>();
        Set<String > set1 = new CopyOnWriteArraySet<>();
        Map<String,String>  map = new HashMap<>(); //
        Map<String,String> map1 = new ConcurrentHashMap<>(); //线程安全的
//        Map<Integer,Integer> map2 = new Hashtable<>();  //使用的是同步方法 一般不用


        //会出现线程安全问题
//        for(int i=0; i<300; i++)
//        {
//            new Thread(()->{
////                list.add((int)(Math.random()*10));
////                System.out.println(list);
////                set.add(UUID.randomUUID().toString().substring(0,8));
////                System.out.println(set);
//                map.put(UUID.randomUUID().toString().substring(0,6),UUID.randomUUID().toString().substring(0,8));
//                System.out.println(map);
//
//            },String.valueOf(i)).start();
//        }

        //不会出现线程安全问题
//        for(int i=0; i<300; i++)
//        {
//            new Thread(()->{
//                list1.add((int)(Math.random()*10));
//                System.out.println(list1);
////                set1.add(UUID.randomUUID().toString().substring(0,8));
////                System.out.println(set1);
////        map1.put(UUID.randomUUID().toString().substring(0,6),UUID.randomUUID().toString().substring(0,8));
////        System.out.println(map1);
//            },String.valueOf(i)).start();
//        }
        Set<Integer> s = new HashSet<>();

        System.out.println(s.add(1));
        System.out.println(s.add(2));
        System.out.println(s.add(1));

    }
}
