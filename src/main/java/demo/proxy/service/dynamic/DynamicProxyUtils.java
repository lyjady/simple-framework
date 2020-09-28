package demo.proxy.service.dynamic;

import demo.proxy.service.AlipayServiceImpl;
import demo.proxy.service.PayService;
import demo.proxy.service.WeChatPayServiceImpl;

import java.lang.reflect.Proxy;

/**
 * @author: linyongjin
 * @create: 2020-09-28 22:58:01
 */
public class DynamicProxyUtils {

    public static <T> T getProxyObject(T targetObject, PayInvocationHandler invocationHandler) {
        return (T) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), invocationHandler);
    }

    public static void main(String[] args) {
        PayService alipayService = new AlipayServiceImpl();
        PayService weChatService = new WeChatPayServiceImpl();
        PayInvocationHandler handler1 = new PayInvocationHandler(alipayService);
        PayInvocationHandler handler2 = new PayInvocationHandler(weChatService);
        PayService proxyObject1 = DynamicProxyUtils.getProxyObject(alipayService, handler1);
        PayService proxyObject2 = DynamicProxyUtils.getProxyObject(weChatService, handler2);
        proxyObject1.pay();
        System.out.println("---------");
        proxyObject2.pay();
    }
}
