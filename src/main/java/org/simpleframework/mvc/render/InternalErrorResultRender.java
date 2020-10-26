package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: linyongjin
 * @create: 2020-10-21 09:05:50
 */
public class InternalErrorResultRender implements ResultRender {

    private String errorMessage;

    public InternalErrorResultRender(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void render(RequestProcessorChain chain) throws Exception {
        chain.getResponse().sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, this.errorMessage);
    }
}
