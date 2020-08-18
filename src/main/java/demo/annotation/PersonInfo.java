package demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author LinYongJin
 * @date 2020/8/18 14:10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonInfo {

    public String name() default "";

    public int age() default 20;

    public String gender() default "男";

    public String[] hobby() default {};
}
