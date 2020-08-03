package cas;


//原子引用是针对对象来说的
//原子类 也有其他基本数据类型的类

import java.util.concurrent.atomic.AtomicReference;

class User
{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class CasReference {

    public static void main(String[] args) {
        User zs = new User("z3",12);
        User ls = new User("l4",13);

        AtomicReference<User> ref = new AtomicReference<>(); // 对象的引用 和原子操作类似
        ref.set(zs);

        System.out.println(ref.compareAndSet(zs,ls) + " " + ref.toString());
        System.out.println(ref.compareAndSet(zs,ls) + " "+ ref.get().toString());
    }
}
