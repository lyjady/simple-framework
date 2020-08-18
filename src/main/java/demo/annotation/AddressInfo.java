package demo.annotation;

import java.lang.annotation.*;

/**
 * @author LinYongJin
 * @date 2020/8/18 14:14
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AddressInfo {

    public String country() default "中国";

    public String province() default "福建省";

    public String city() default "福州市";
}
