package Jvm;

public class StackTest {

    public int add(int i,int j)
    {
        int result = -1;
        result = i + j;
        return result;
    }

    public static void m1()
    {
//        System.out.println("*********222");
//        System.out.println("*************m1");
//        System.out.println("**********333");
        m1(); //递归调用自己 无限的压栈 造成栈内存溢出
//        System.out.println(Integer.MAX_VALUE);
    }

    public static void main(String[] args) {

        System.out.println("111111111");
//        m1();
        char a = '2';
        int b = 2;
        int d = a - '0';
        String cc = "fda";
        cc = cc +2;
        System.out.println(cc);
        System.out.println(b == d);
    }
}
