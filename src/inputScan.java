//对字符串的解析

import java.io.*;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.Scanner;


class ListNode  //节点包括数值  和自己本身的引用
{
    private  int val;
    public ListNode next;
    public ListNode(int x)
    {
         val = x;
    }

}

class TreeNode  //树也是  包含两个引用变量和一个值域
{
    public TreeNode left;
    public TreeNode right;
    public int val;

    TreeNode(int x){val = x;}

}




public class inputScan {

    public static void main(String[] args) throws IOException {
//


//        Scanner scan = new Scanner(System.in);
//
//        System.out.println(scan.nextInt());

//
//        readIn();
        String a = args[0];
        String b = args[1];
        System.out.println(a);
        System.out.println(b);


    }


    public static void add(int a,int b)
    {
        System.out.println(a+b);
    }

    //传统io流获取cmd参数
    public static void readIn() throws IOException {
        BufferedReader buf =  new BufferedReader(new InputStreamReader(System.in)); //这样读出来可以解析了
        String b = buf.readLine(); //这是字符流 读字符串 一行一行读回车就结束
        String a = buf.readLine();
        System.out.println(b);  //是不直接接收的需要设定一个buf
        System.out.println(a);
    }
}
