package Jvm.oom;

public class SOF {

    public static  void stackOverFlow()
    {
        stackOverFlow();
    }
    public static void main(String[] args) {
        //java.lang.StackOverflowError
        //栈管运行 堆管存储 循环调用函数 栈帧增加导致栈溢出
        stackOverFlow();

        System.out.println("up");
    }
}
