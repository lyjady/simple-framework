package demo.reflect;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/8/18 11:26
 */
public class FieldCollector {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = Class.forName("demo.reflect.ReflectTarget");
        System.out.println("-----------全部的public属性(可取得父类的属性)-----------");
        Field[] fields = clazz.getFields();
        System.out.println(Arrays.toString(fields));
        System.out.println("-----------全部的属性(不可取得父类的属性)-----------");
        fields = clazz.getDeclaredFields();
        System.out.println(Arrays.toString(fields));
        System.out.println("-----------获取指定的public属性-----------");
        ReflectTarget target = new ReflectTarget("field");
        Field publicField = clazz.getField("publicField");
        System.out.println(publicField);
        System.out.println("--------field的set get方法--------");
        publicField.setInt(target, 20);
        System.out.println(publicField.get(target));
        System.out.println("-----------获取指定的属性-----------");
        Field privateField = clazz.getDeclaredField("privateField");
        System.out.println(privateField);
        privateField.setAccessible(true);
        privateField.setInt(target, 26676);
        System.out.println(privateField.get(target));
    }
}
