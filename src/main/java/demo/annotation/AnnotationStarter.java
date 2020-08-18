package demo.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author LinYongJin
 * @date 2020/8/18 14:21
 */
public class AnnotationStarter {

    /**
     * 元注解:
     * @Target: 表示注解修饰什么位置上(属性, 方法, 类...)
     * @Retention: 表示注解的生命周期, SOURCE只存在与源代码, CLASS存活于编译之后的class文件, RUNTIME: 运行时, 如果想要用反射取到注解的信息必须是RUNTIME
     * @Documented: 是否将注解生成到JavaDoc文档中
     * @Inherited: 子类是否继承被该注解修饰的注解
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException {
        Class<?> clazz = Class.forName("demo.annotation.Person");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == PersonInfo.class) {
                PersonInfo personInfo = (PersonInfo) annotation;
                System.out.println(personInfo.age() + ": " + personInfo.gender() + ": " + personInfo.name() + ": " + Arrays.toString(personInfo.hobby()));
            }
        }
        Field defaultAddress = clazz.getDeclaredField("defaultAddress");
        if (defaultAddress.isAnnotationPresent(AddressInfo.class)) {
            AddressInfo annotation = defaultAddress.getAnnotation(AddressInfo.class);
            System.out.println(annotation.city() + ": " + annotation.country() + ": " + annotation.province());
        }
        Method changeAddress = clazz.getMethod("changeAddress");
        if (changeAddress.isAnnotationPresent(AddressInfo.class)) {
            AddressInfo annotation = changeAddress.getAnnotation(AddressInfo.class);
            System.out.println(annotation.city() + ": " + annotation.country() + ": " + annotation.province());
        }
    }
}
