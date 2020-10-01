package org.simpleframework.aop.support;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.simpleframework.aop.annotation.Order;

/**
 * @author: linyongjin
 * @create: 2020-10-01 15:22:02
 */
@Getter
@AllArgsConstructor
public class AspectInfo {

    private final int order;

    private final DefaultAspect aspect;
}
