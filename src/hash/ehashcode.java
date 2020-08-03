package hash;


import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;

public class ehashcode  extends Object{

    public static void main(String[] args) {
        Student s1 =  new Student("aa","aa");
        Student s2 =  new Student("aa","aa");  //这是两个实例
        Student s3 = s1;
        System.out.println("hashcode  " + s1.hashCode() + "  " + s2.hashCode());
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s3);

    }
}
