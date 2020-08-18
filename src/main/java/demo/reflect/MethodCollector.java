package demo.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/8/18 11:35
 */
public class MethodCollector {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> clazz = Class.forName("demo.reflect.ReflectTarget");
        System.out.println("----------全部的public方法(能取到父类, 不包含构造方法)-------------");
        Method[] methods = clazz.getMethods();
        System.out.println(Arrays.toString(methods));
        System.out.println("----------全部的public方法(不能取到父类, 不包含构造方法)-------------");
        methods = clazz.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));
        System.out.println("-------获取public指定的方法并调用---------");
        ReflectTarget target = new ReflectTarget("Target");
        Method publicMethod = clazz.getMethod("publicMethod", boolean.class);
        System.out.println(publicMethod);
        System.out.println(publicMethod.invoke(target, true));
        System.out.println("-------获取指定的方法并调用---------");
        Method privateMethod = clazz.getDeclaredMethod("privateMethod", String.class);
        System.out.println(privateMethod);
        privateMethod.setAccessible(true);
        System.out.println(privateMethod.invoke(target, "private method"));
    }
}
