package org.simpleframework;

import org.junit.jupiter.api.*;
import org.simpleframework.core.BeanContainer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {

    private static BeanContainer beanContainer;

    @BeforeAll
    public static void init() {
        beanContainer = BeanContainer.getInstance();
    }

    @Test
    @Order(1)
    @DisplayName("测试加载bean")
    public void loadBeanTest() {
        beanContainer.loadBeans("org.augustus");
        Assertions.assertEquals(6, beanContainer.loadBeanSize());
    }
}
