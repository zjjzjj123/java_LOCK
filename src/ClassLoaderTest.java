


class Car
{
    public void fun1()
    {
        System.out.println("hehe");
    }
}

public class ClassLoaderTest {
    public static void main(String[] args) {

        Car car = new Car();
        System.out.println(car.getClass().getClassLoader());
        System.out.println(car.getClass().getClassLoader().getParent());
        System.out.println(car.getClass().getClassLoader().getParent().getParent());  //启动类其实显示的是null  默认启动泪肯定要加载
        //启动类加载那些一定能用的 最基础的类文件class
    }
}
