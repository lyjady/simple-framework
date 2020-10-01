package org.simpleframework.aop;

import org.simpleframework.aop.annotation.Aspect;
import org.simpleframework.aop.annotation.Order;
import org.simpleframework.aop.support.AspectInfo;
import org.simpleframework.aop.support.DefaultAspect;
import org.simpleframework.core.BeanContainer;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @author: linyongjin
 * @create: 2020-10-01 15:49:38
 */
public class AspectWeaver {

    private BeanContainer beanContainer;

    public AspectWeaver() {
        this.beanContainer = BeanContainer.getInstance();
    }

    public void doAop() {
        // 1.获取所有的切面类
        Set<Class<?>> aspectObjects = beanContainer.getClassByAnnotation(Aspect.class);
        if (aspectObjects == null || aspectObjects.size() == 0) {
            return;
        }
        // 2.将切面类按照不同的织入目标进行切分
        Map<Class<? extends Annotation>, List<AspectInfo>> aspectMap = new HashMap<>(8);
        for (Class<?> aspectObject : aspectObjects) {
            if (verifyAspect(aspectObject)) {
                categorizeAspect(aspectMap, aspectObject);
            } else {
                throw new RuntimeException("不符合规范的切面类");
            }
        }
        if (aspectMap.size() == 0) {
            return;
        }
        for (Map.Entry<Class<? extends Annotation>, List<AspectInfo>> entry : aspectMap.entrySet()) {
            weaveByCategory(entry.getKey(), entry.getValue());
        }
    }

    private void weaveByCategory(Class<? extends Annotation> key, List<AspectInfo> value) {
        Set<Class<?>> classes = beanContainer.getClassByAnnotation(key);
        if (classes == null || classes.size() == 0) {
            return;
        }
        for (Class<?> clazz : classes) {
            AspectExecutor executor = new AspectExecutor(clazz, value);
            Object proxy = ProxyCreator.createProxy(clazz, executor);
            beanContainer.addBean(clazz, proxy);
        }
    }

    /**
     * 对切面类进行分类
     */
    private void categorizeAspect(Map<Class<? extends Annotation>, List<AspectInfo>> aspectMap, Class<?> aClass) {
        Class<? extends Annotation> targetComponent = aClass.getAnnotation(Aspect.class).value();
        Order order = aClass.getAnnotation(Order.class);
        DefaultAspect defaultAspect = (DefaultAspect) beanContainer.getBean(aClass);
        AspectInfo aspectInfo = new AspectInfo(order.value(), defaultAspect);
        if (aspectMap.containsKey(targetComponent)) {
            // 包含当前的注解
            List<AspectInfo> aspectInfos = aspectMap.get(targetComponent);
            aspectInfos.add(aspectInfo);
        } else {
            // 不包含当前注解
            List<AspectInfo> aspectInfos = new ArrayList<>();
            aspectInfos.add(aspectInfo);
            aspectMap.put(targetComponent, aspectInfos);
        }
    }

    /**
     * 验证切面类
     *
     * @param clazz
     * @return
     */
    private boolean verifyAspect(Class<?> clazz) {
        return clazz.isAnnotationPresent(Aspect.class)
                && clazz.isAnnotationPresent(Order.class)
                && DefaultAspect.class.isAssignableFrom(clazz)
                && clazz.getAnnotation(Aspect.class).value() != Aspect.class;
    }
}
