package thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class NoSafeDemo1 {

    public static void main(String[] args) {

//        List<String> list = ;  //全局锁  对象锁
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());  //相当于加了一个对象锁
//        List<String> list = new CopyOnWriteArrayList<>();
////
////        for(int  i=0 ; i<30; i++)
////        {
////            new Thread(()->{
////                list.add(UUID.randomUUID().toString().substring(0,8));
////                System.out.println(list);
////            },String.valueOf(i)).start();
////        }

        int[][] a = {{1,2},{3,4}};

        System.out.println(Arrays.deepToString(a));

    }
}
