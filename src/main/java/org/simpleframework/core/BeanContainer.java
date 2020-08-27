package org.simpleframework.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.utils.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Bean的容器负责装载实例化对象
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {

    private final ConcurrentMap<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    public static final List<Class<? extends Annotation>> ANNOTATION = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class);

    private boolean loaded = false;

    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    @SuppressWarnings("all")
    private enum ContainerHolder {
        HOLDER;
        private BeanContainer instance;

        ContainerHolder() {
            instance = new BeanContainer();
        }
    }

    public boolean isLoaded() {
        return this.loaded;
    }

    /**
     * 加载bean的数量
     *
     * @return
     */
    public int loadBeanSize() {
        return beanMap.size();
    }

    /**
     * 向容器添加bean, bean由容器来创建
     *
     * @param clazz 创建bean的类型
     */
    public void addBean(Class<?> clazz) {
        beanMap.put(clazz, ClassUtil.newInstance(clazz));
    }

    /**
     * 移除容器中的bean
     *
     * @param clazz 要移除的bean的类型
     */
    public void removeBean(Class<?> clazz) {
        beanMap.remove(clazz);
    }

    /**
     * 向容器添加bean, bean的实例又开发者提供
     *
     * @param clazz
     * @param value
     */
    public void addBean(Class<?> clazz, Object value) {
        beanMap.put(clazz, value);
    }

    /**
     * 获取指定类型的bean
     *
     * @param clazz 要获取bean的类型
     * @return
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }

    /**
     * 返回容器内全部的bean的类型集合
     *
     * @return
     */
    public Set<Class<?>> classes() {
        return beanMap.keySet();
    }

    /**
     * 返回容器内的全部bean实例
     *
     * @return
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
     * 获取被指定注解修饰的bean
     *
     * @param annotation 指定的注解类型
     * @return
     */
    public Set<Object> getBeanByAnnotation(Class<? extends Annotation> annotation) {
        if (beanMap.size() == 0) {
            return null;
        }
        Set<Object> beans = new HashSet<>();
        for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
            if (entry.getKey().isAnnotationPresent(annotation)) {
                beans.add(entry.getValue());
            }
        }
        return beans.size() == 0 ? null : beans;
    }

    /**
     * 获取被指定注解修饰的类
     *
     * @param annotation 指定的注解类型
     * @return
     */
    public Set<Class<?>> getClassByAnnotation(Class<? extends Annotation> annotation) {
        if (beanMap.size() == 0) {
            return null;
        }
        Set<Class<?>> beans = new HashSet<>();
        for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
            if (entry.getKey().isAnnotationPresent(annotation)) {
                beans.add(entry.getKey());
            }
        }
        return beans.size() == 0 ? null : beans;
    }

    /**
     * 获取指定类子类或者实现接口的bean
     *
     * @param interfaceOrSuper 指定的父类或者接口
     * @return
     */
    public Set<Object> getBeanBySuper(Class<?> interfaceOrSuper) {
        if (beanMap.size() == 0) {
            return null;
        }
        Set<Object> beans = new HashSet<>();
        for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
            if (interfaceOrSuper.isAssignableFrom(entry.getKey()) && entry.getKey() != interfaceOrSuper) {
                beans.add(entry.getValue());
            }
        }
        return beans.size() == 0 ? null : beans;
    }

    /**
     * 获取指定类子类或者实现接口的类
     *
     * @param interfaceOrSuper 指定的父类或者接口
     * @return
     */
    public Set<Class<?>> getClassBySuper(Class<?> interfaceOrSuper) {
        if (beanMap.size() == 0) {
            return null;
        }
        Set<Class<?>> beans = new HashSet<>();
        for (Map.Entry<Class<?>, Object> entry : beanMap.entrySet()) {
            if (interfaceOrSuper.isAssignableFrom(entry.getKey()) && entry.getKey() != interfaceOrSuper) {
                beans.add(entry.getKey());
            }
        }
        return beans.size() == 0 ? null : beans;
    }

    /**
     * 根据指定的包名, 加载该包下所有被实例化注解标记的类
     *
     * @param packageName 制定的包名
     */
    public synchronized void loadBeans(String packageName) {
        if (this.loaded) {
            log.info("该容器已经加载过了");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (classSet == null || classSet.size() == 0) {
            log.warn("该包下没有要被实例化的类");
            return;
        }
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotation : ANNOTATION) {
                if (clazz.isAnnotationPresent(annotation)) {
                    // 实例化对象
                    Object targetObj = ClassUtil.newInstance(clazz);
                    beanMap.put(clazz, targetObj);
                }
            }
        }
        loaded = true;
    }
}
