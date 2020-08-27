package org.simpleframework;

import org.augustus.controller.superadmin.HeadLineController;
import org.augustus.service.solo.HeadLineService;
import org.junit.jupiter.api.*;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInject;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DependencyInjectTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    public static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @Test
    @DisplayName("测试依赖注入")
    public void injectTest() {
        beanContainer.loadBeans("org.augustus");
        Assertions.assertTrue(beanContainer.isLoaded());
        new DependencyInject().doInject();
        HeadLineController headLineController = (HeadLineController) beanContainer.getBean(HeadLineController.class);
        Assertions.assertNotNull(headLineController.getHeadLineService());
    }
}
