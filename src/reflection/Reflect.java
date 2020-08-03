package reflection;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Ref;

//反射演示
public class Reflect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        ReflectDemo rr = new ReflectDemo("ll",11);
        Class cl = rr.getClass();
        System.out.println(cl);
        //这个值new出来test一样 使用getClass一样都是去方法区找类信息
        Class clzz  = Class.forName("reflection.ReflectDemo"); //==
        System.out.println(clzz);

        //获得构造方法
        //不同的构造方法 创建不同的实例
        Constructor c1 = clzz.getDeclaredConstructor(String.class,int.class);
        Constructor c2 = clzz.getDeclaredConstructor(String.class);

        ReflectDemo r1 = (ReflectDemo) c1.newInstance("zs",12);
        ReflectDemo r2 = (ReflectDemo) c2.newInstance("ls");

        //调用函数 调用函数直接是从类模板中加载 通过传入不同的实例化对象
        // 获得相同的方法但是不同的执行结果
        Method m1 = clzz.getDeclaredMethod("getName");
        Method m2 = clzz.getDeclaredMethod("getAge");

        //调用类中的方法 调用获取函数
        System.out.println(m1.invoke(r1) + " " + m2.invoke(r1));
        System.out.println(m1.invoke(r2) + " "  + m2.invoke(r2));

        //调用设置函数
        Method m3 = clzz.getDeclaredMethod("setAge", int.class);
        System.out.println(m3.invoke(r2,12));
        System.out.println(m1.invoke(r2) + " "  + m2.invoke(r2));
    }
}
