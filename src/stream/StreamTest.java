package stream;


import com.sun.org.apache.xerces.internal.util.Status;
import jdk.nashorn.internal.runtime.options.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

public class StreamTest {

     public  static List<Employee> emp = Arrays.asList(

        new Employee(101, "Z3", 19, 9999.99),
        new Employee(102, "L4", 20, 7777.77),
        new Employee(103, "W5", 35, 6666.66),
        new Employee(104, "Tom", 44, 1111.11),
        new Employee(105, "Jerry", 60, 4444.44)
    );

    public static void main(String[] args) {

        //有了四大函数式接口  也有扩展的 就不需要我们自己定义函数式接口了

//        emp.stream()
//                .sorted((e1,e2)->
//                {
//                    if(e1.getAge() > e2.getAge())
//                        return -1;
//                    else if(e1.getAge() < e2.getAge())
//                        return 1;
//                    else
//                        return e1.getName().compareTo(e2.getName());
//                })
//                .forEach(System.out::println);


        List<Integer> list = Arrays.asList(1,2,3,6,4,5);

       Long count =  list.stream().count();
        System.out.println(count);
    }

    private static void testOne() {
        //        emp.stream().forEach(System.out::println);

        Comparator<Integer> cmp = (x, y)->Integer.compare(x,y);

        Comparator<Integer> cmp1 = Integer::compareTo;  //类的实例方法 条件是参数，或者返回值要保持一致

        System.out.println(cmp.compare(1,2));
        System.out.println(cmp1.compare(1,2));

        BiPredicate<String ,String> pr1 = (x, y)->{
            return x.equals(y);
        };
        BiPredicate<String,String> pr2 = String::equals;
        System.out.println(pr1.test("a","a"));
        System.out.println(pr2.test("a","a"));

        Supplier<List> sip = ()->{return new ArrayList();};

        Supplier<List> sip1 = ArrayList::new;
    }
}
