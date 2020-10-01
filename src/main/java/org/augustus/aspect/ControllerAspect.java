package org.augustus.aspect;

import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.support.DefaultAspect;
import org.simpleframework.core.annotation.Controller;

import java.lang.reflect.Method;

/**
 * @author: linyongjin
 * @create: 2020-10-01 16:41:46
 */
@Aspect(Controller.class)
@Order(0)
public class ControllerAspect extends DefaultAspect {
    @Override
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {
        System.out.println("controller before");
    }

    @Override
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object value) throws Throwable {
        System.out.println("controller after");
        return value;
    }

    @Override
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable throwable) throws Throwable {
        super.afterThrowing(targetClass, method, args, throwable);
    }
}
