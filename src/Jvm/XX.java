package Jvm;

import org.omg.CORBA.INTERNAL;

import java.util.concurrent.TimeUnit;

public class XX {

    public static void main(String[] args) {
        System.out.println("heheh");
        try {
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
