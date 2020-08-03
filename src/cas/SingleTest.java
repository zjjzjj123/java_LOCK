package cas;


import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

class Single
{
    private static volatile Single single = null;

    private Single(){}

    public static Single getSingle()
    {
        if(single == null)  //双重检查还是有风险
        {
            synchronized(Single.class)
            {
                if(single == null)
                {
                    single = new Single();//可能在一个线程执行很快的时候在完成new之后 在对象初始化的时候
                    //由于指令重排的原因导致另一个线程过来取这个对象 但是此时对象并没有初始化完成
                    //因此这个线程得到的还是个空对象 使用volatile 最好
                }
            }
        }
        return single;
    }
}

class  Single1
{
    private static Single1 single = new Single1();

    private Single1(){}


    public  static Single1 getInstance()
    {
        return  single; //可以用线程不安全
    }
}

//静态内部类  利用类加载机制避免不同步问题

class Single2
{
    private static  Single2 single2 = null;

    //静态代码块》非》静态内部类》构造方法
    public static class SingleIn
    {
        private static  final Single2 Instance = new Single2(); //只有类装载的时候才执行 且只执行一次
    }

    public static synchronized Single2 getInstance()
    {
        return SingleIn.Instance; //被调用时才会装载
    }
}

class Test1{
    private static  final  int a = 10;
    static {
        System.out.println("static");
        System.out.println(a);
    }

    {
        System.out.println("no  static");
    }

    public Test1()
    {
        System.out.println("in constructor");
    }

    public static class Test2
    {
        private static  final  int b = 10;
        private Test2()
        {
            System.out.println("test2 constructor");
        }  //构造方法依然没有执行  //只有实例化的时候才会被执行
    }
    public static void getIns()
    {
        System.out.println("调用今天内部类" + Test2.b);
        Test2 test2 = new Test2();
    }
}

public class SingleTest {

    public static void main(String[] args) {

//        Single s = Single.getSingle();
////        Single s1 = Single.getSingle();
////        System.out.println(s == s1);
////
////        Single1 s2 = Single1.getInstance();
////        Single1 s3 = Single1.getInstance();
////
////        System.out.println(s2 == s3);

        Test1 test1 = new Test1();
        test1.getIns();

    }
}
