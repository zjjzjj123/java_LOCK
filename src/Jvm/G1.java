package Jvm;

import java.util.Random;

public class G1 {

    public static void main(String[] args) {

        System.out.println("GCDemo...");
        try {
            String str = "bjtu";
            while (true) {
                str += str + new Random().nextInt(77777777) + new Random().nextInt(88888888);
                str.intern(); //返回字符串的规范表示
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
