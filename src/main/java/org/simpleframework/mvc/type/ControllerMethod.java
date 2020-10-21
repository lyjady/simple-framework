package org.simpleframework.mvc.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author: linyongjin
 * @create: 2020-10-21 10:05:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerMethod {

    private Class<?> controllerClass;

    private Method invokeMethod;

    private Map<String, Class<?>> methodParameters;
}
