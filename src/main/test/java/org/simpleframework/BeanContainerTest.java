package org.simpleframework;

import org.augustus.controller.DispatcherServlet;
import org.augustus.controller.superadmin.HeadLineController;
import org.augustus.service.solo.HeadLineService;
import org.augustus.service.solo.impl.HeadLineServiceImpl;
import org.junit.jupiter.api.*;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.core.annotation.Service;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    public static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @Test
    @Order(1)
    @DisplayName("测试加载Bean")
    public void loadBeanTest() {
        beanContainer.loadBeans("org.augustus");
        Assertions.assertEquals(6, beanContainer.loadBeanSize());
    }

    @Test
    @Order(2)
    @DisplayName("根据类获取Bean")
    public void getBeanTest() {
        Object headLineController = beanContainer.getBean(HeadLineController.class);
        Assertions.assertTrue(headLineController instanceof HeadLineController);
        Object dispatcherServlet = beanContainer.getBean(DispatcherServlet.class);
        Assertions.assertNull(dispatcherServlet);
    }

    @Test
    @Order(3)
    @DisplayName("根据注解获取Bean")
    public void getBeanByAnnotation() {
        Set<Object> beans = beanContainer.getBeanByAnnotation(Service.class);
        Assertions.assertEquals(3, beans.size());
    }

    @Test
    @Order(4)
    @DisplayName("根据接口获得Bean")
    public void getBeanByInterface() {
        Set<Object> bean = beanContainer.getBeanBySuper(HeadLineService.class);
        Assertions.assertTrue(bean.contains(HeadLineServiceImpl.class));
    }
}
