package org.simpleframework.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author: linyongjin
 * @create: 2020-10-01 15:47:37
 */
public class ProxyCreator {

    /**
     * 创建代理对象
     *
     * @param targetClass       目标类
     * @param methodInterceptor 方法拦截器
     * @return
     */
    public static Object createProxy(Class<?> targetClass, MethodInterceptor methodInterceptor) {
        return Enhancer.create(targetClass, methodInterceptor);
    }
}
