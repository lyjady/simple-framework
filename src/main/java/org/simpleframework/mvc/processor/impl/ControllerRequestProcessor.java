package org.simpleframework.mvc.processor.impl;

import org.simpleframework.core.BeanContainer;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.annotation.RequestMapping;
import org.simpleframework.mvc.annotation.RequestParam;
import org.simpleframework.mvc.processor.RequestProcessor;
import org.simpleframework.mvc.type.ControllerMethod;
import org.simpleframework.mvc.type.RequestPathInfo;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author: linyongjin
 * @create: 2020-10-15 16:03:10
 */
public class ControllerRequestProcessor implements RequestProcessor {

    private ConcurrentMap<RequestPathInfo, ControllerMethod> pathMap = new ConcurrentHashMap<>();

    private BeanContainer beanContainer = null;

    public ControllerRequestProcessor() {
        beanContainer = BeanContainer.getInstance();
        // 获取全部被RequestMapping注解标记的类
        Set<Class<?>> mappingClass = beanContainer.getClassByAnnotation(RequestMapping.class);
        if (mappingClass != null && mappingClass.size() > 0) {
            for (Class<?> clazz : mappingClass) {
                // 获取到RequestMapping的一级路径
                RequestMapping classAnnotation = clazz.getAnnotation(RequestMapping.class);
                String firstPath = classAnnotation.value();
                if (!firstPath.startsWith("/")) {
                    firstPath = "/" + firstPath;
                }
                // 获取到类中全部被RequestMapping标记的方法
                Method[] declaredMethods = clazz.getDeclaredMethods();
                if (declaredMethods.length > 0) {
                    for (Method declaredMethod : declaredMethods) {
                        if (declaredMethod.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping methodAnnotation = declaredMethod.getAnnotation(RequestMapping.class);
                            String secondPath = methodAnnotation.value();
                            if (!secondPath.startsWith("/")) {
                                secondPath = "/" + secondPath;
                            }
                            String method = methodAnnotation.method().toString();
                            // 获取到该方法的参数列表
                            Parameter[] parameters = declaredMethod.getParameters();
                            if (parameters.length > 0) {
                                Map<String, Class<?>> methodParams = new HashMap<>();
                                for (Parameter parameter : parameters) {
                                    if (!parameter.isAnnotationPresent(RequestParam.class)) {
                                        throw new RuntimeException("方法的参数必须要有RequestParam注解标记");
                                    }
                                    RequestParam paramAnnotation = parameter.getAnnotation(RequestParam.class);
                                    methodParams.put(paramAnnotation.value(), parameter.getType());
                                }
                                RequestPathInfo requestPathInfo = new RequestPathInfo(method, firstPath + secondPath);
                                ControllerMethod controllerMethod = new ControllerMethod(clazz, declaredMethod, methodParams);
                                pathMap.put(requestPathInfo, controllerMethod);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        return false;
    }
}
