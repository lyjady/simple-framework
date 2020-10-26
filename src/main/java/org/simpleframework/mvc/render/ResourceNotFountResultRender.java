package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: linyongjin
 * @create: 2020-10-21 09:08:11
 */
public class ResourceNotFountResultRender implements ResultRender {

    private String method;

    private String path;

    public ResourceNotFountResultRender(String method, String path) {
        this.method = method;
        this.path = path;
    }

    @Override
    public void render(RequestProcessorChain chain) throws Exception {
        chain.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND,
                "获取不到对应的请求资源：请求路径[" + path + "]" + "请求方法[" + method + "]");
    }
}
