package cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo1 {

    public static void main(String[] args) {
        //cas最多是用在原子类上

        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5,20));
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,30));
        System.out.println(atomicInteger.compareAndSet(20,30));

        System.out.println(atomicInteger.get());

//        double left = 1.414;
//        double right = 1.5;
//        double e = 0.0000000001;
//        double mid = 0;
//        while(right - left > e)
//        {
//             mid = (right + left) / 2;
//            if(mid*mid>2)
//            {
//                right = mid;
//            }
//            else
//            {
//                left = mid;
//            }
//        }
//
//        System.out.println("1.414: " + mid);
//
//        System.out.println(Math.sqrt(2));
    }
}
