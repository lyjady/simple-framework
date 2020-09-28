package demo.proxy.service.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: linyongjin
 * @create: 2020-09-28 22:55:48
 */
public class PayInvocationHandler implements InvocationHandler {

    private Object targetObject;

    public PayInvocationHandler(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(targetObject, args);
        after();
        return invoke;
    }

    public static void before() {
        System.out.println("从银行取钱");
    }

    public static void after() {
        System.out.println("转账给老婆");
    }
}
