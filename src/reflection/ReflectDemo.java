package reflection;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

public class ReflectDemo {

    public String name;
    public int age;

    public ReflectDemo(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public ReflectDemo(String name) {
        this.name = name;
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
        return "ReflectDemo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
