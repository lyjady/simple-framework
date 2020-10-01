package org.simpleframework.aop;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.simpleframework.aop.support.AspectInfo;
import org.simpleframework.aop.support.DefaultAspect;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;

/**
 * @author: linyongjin
 * @create: 2020-10-01 15:25:05
 */
public class AspectExecutor implements MethodInterceptor {

    private final Class<?> targetClass;

    private final List<AspectInfo> sortedAspectInfo;

    public AspectExecutor(Class<?> targetClass, List<AspectInfo> aspectInfo) {
        this.targetClass = targetClass;
        this.sortedAspectInfo = aspectInfo;
        sortedAspectInfo.sort(Comparator.comparingInt(AspectInfo::getOrder));
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object value;
        if (sortedAspectInfo == null || sortedAspectInfo.size() == 0) {
            value = methodProxy.invoke(o, args);
            return value;
        }
        invokeBeforeAspect(method, args);
        try {
            value = methodProxy.invoke(o, args);
        } catch (Throwable throwable) {
            invokeAfterThrowing(targetClass, method, args, throwable);
            return null;
        }
        value = invokeAfterReturningAspect(method, args, value);
        return value;
    }

    /**
     * 执行异常后置通知
     */
    private void invokeAfterThrowing(Class<?> targetClass, Method method, Object[] args, Throwable throwable) throws Throwable {
        for (int i = sortedAspectInfo.size() - 1; i >= 0; i--) {
            sortedAspectInfo.get(i).getAspect().afterThrowing(targetClass, method, args, throwable);
        }
    }

    /**
     * 执行后置通知
     */
    private Object invokeAfterReturningAspect(Method method, Object[] args, Object value) throws Throwable {
        for (int i = sortedAspectInfo.size() - 1; i >= 0; i--) {
            value = sortedAspectInfo.get(i).getAspect().afterReturning(targetClass, method, args, value);
        }
        return value;
    }

    /**
     * 按顺序调动前置通知
     */
    private void invokeBeforeAspect(Method method, Object[] args) throws Throwable {
        for (AspectInfo aspectInfo : sortedAspectInfo) {
            System.out.println("loop");
            DefaultAspect aspect = aspectInfo.getAspect();
            aspect.before(targetClass, method, args);
        }
    }
}
