package map;

import sun.applet.resources.MsgAppletViewer;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapDemo {

    public static void main(String[] args) {
        Map<String,String> m1 = new Hashtable<>();
        Map<String,String> m2 = new HashMap<>();
        Map<String,String> cm = new ConcurrentHashMap<>();

        List<String> list = new ArrayList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        for(int i=0; i<list.size(); i++)
        {
            list.remove(i);
        }

        System.out.println(list.size());
        list.stream().forEach(System.out::println);

        for(int i=list.size()-1; i>=0; i--)
        {
            list.remove(i);
        }

        System.out.println(list.size());
        list.stream().forEach(System.out::println);
    }
}
