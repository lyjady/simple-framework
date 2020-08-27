package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.utils.ClassUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author linyongjin
 */
@Slf4j
public class DependencyInject {

    private BeanContainer beanContainer;

    public DependencyInject() {
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 为Bean注入依赖
     */
    public void doInject() {
        Set<Class<?>> classes = beanContainer.classes();
        if (classes == null || classes.size() == 0) {
            log.warn("容器为空");
            return;
        }
        for (Class<?> clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            if (fields.length == 0) {
                log.info("该类型{}没有属性", clazz.toString());
                continue;
            }
            for (Field field : fields) {
                if (field.isAnnotationPresent(Autowird.class)) {
                    // 获取该成员变量的类型, 并从容器中取得对应的Bean
                    Class<?> fileType = field.getType();
                    Autowird annotation = field.getAnnotation(Autowird.class);
                    String value = annotation.value();
                    Object bean = getFieldInstance(fileType, value);
                    if (bean != null) {
                        // 为属性注入
                        Object target = beanContainer.getBean(clazz);
                        ClassUtil.fieldInject(target, field, bean);
                    } else {
                        log.warn("需要注入的属性{}没有对应的实例在容器中", field.toString());
                    }
                }
            }
        }
    }

    /**
     * 获取属性对应的Bean
     *
     * @param fileType 属性的类型
     * @param value    注解的value属性标明了注入的类
     * @return
     */
    private Object getFieldInstance(Class<?> fileType, String value) {
        // 判断属性类型是否为接口类型
        if (fileType.isInterface()) {
            return getInterfaceImplementsInstance(fileType, value);
        } else {
            return beanContainer.getBean(fileType);
        }
    }

    /**
     * 获取接口对应实现类的Bean
     *
     * @param fileType 接口类型
     * @param value    注解的value属性标明了注入的类
     */
    private Object getInterfaceImplementsInstance(Class<?> fileType, String value) {
        Set<Class<?>> beansBySuper = beanContainer.getClassBySuper(fileType);
        if (beansBySuper == null || beansBySuper.size() == 0) {
            return null;
        }
        if (beansBySuper.size() == 1) {
            return beanContainer.getBean(beansBySuper.iterator().next());
        } else {
            if (StringUtils.isNoneBlank(value)) {
                for (Class<?> clazz : beansBySuper) {
                    if (clazz.getSimpleName().equals(value)) {
                        return beanContainer.getBean(clazz);
                    }
                }
            } else {
                log.error("没有指定要注入的Bean的类型");
                throw new RuntimeException("没有指定要注入的Bean的类型");
            }
        }
        return null;
    }


}
