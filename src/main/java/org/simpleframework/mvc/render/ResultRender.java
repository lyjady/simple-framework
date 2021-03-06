package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

/**
 * @author: linyongjin
 * @create: 2020-10-21 09:04:26
 */
public interface ResultRender {

    void render(RequestProcessorChain chain) throws Exception;
}
