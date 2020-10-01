package org.simpleframework.aop.support;

import java.lang.reflect.Method;

/**
 * @author: linyongjin
 * @create: 2020-10-01 15:07:05
 */
public abstract class DefaultAspect {

    /**
     * 事前拦截
     *
     * @param targetClass 被代理对象的类型
     * @param method      被织入方法的方法对象
     * @param args        传入的参数
     * @throws Throwable
     */
    public void before(Class<?> targetClass, Method method, Object[] args) throws Throwable {

    }

    /**
     * 事后拦截
     *
     * @param targetClass 被代理对象的类型
     * @param method      被织入方法的方法对象
     * @param args        传入的参数
     * @param value       返回值
     * @return
     * @throws Throwable
     */
    public Object afterReturning(Class<?> targetClass, Method method, Object[] args, Object value) throws Throwable {
        return value;
    }

    /**
     * 异常拦截
     *
     * @param targetClass 被代理对象的类型
     * @param method      被织入方法的方法对象
     * @param args        传入的参数
     * @param throwable   异常对象
     * @throws Throwable
     */
    public void afterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable throwable) throws Throwable {

    }
}
