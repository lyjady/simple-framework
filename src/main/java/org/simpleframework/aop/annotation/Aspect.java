package org.simpleframework.aop.annotation;

import java.lang.annotation.*;

/**
 * @author: linyongjin
 * @create: 2020-10-01 15:03:22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {

    Class<? extends Annotation> value();
}
