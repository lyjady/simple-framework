package demo.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/8/18 11:19
 */
public class ConstructorCollector {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = Class.forName("demo.reflect.ReflectTarget");
        System.out.println("-----------全部的public构造方法-----------");
        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println(Arrays.toString(constructors));
        System.out.println("-----------全部的构造方法-----------");
        constructors = clazz.getDeclaredConstructors();
        System.out.println(Arrays.toString(constructors));
        System.out.println("-----------获取指定的public构造方法, 并生成对象实例-----------");
        Constructor<?> constructor = clazz.getConstructor(String.class);
        ReflectTarget reflectTarget = (ReflectTarget) constructor.newInstance("constructor");
        System.out.println(reflectTarget);
        System.out.println("-----------获取指定的构造方法, 并生成对象实例-----------");
        constructor = clazz.getDeclaredConstructor(boolean.class);
        // 设置允许访问私有方法
        constructor.setAccessible(true);
        reflectTarget = (ReflectTarget) constructor.newInstance(true);
        System.out.println(reflectTarget);
    }
}
