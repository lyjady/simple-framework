package org.simpleframework.mvc.processor.impl;

import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.processor.RequestProcessor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * @author: linyongjin
 * @create: 2020-10-15 16:02:21
 */
public class JspRequestProcessor implements RequestProcessor {

    private static final String JSP_SERVLET = "jsp";

    private static final String  JSP_RESOURCE_PREFIX = "/templates/";

    private RequestDispatcher jspServlet;

    public JspRequestProcessor(ServletContext servletContext) {
        jspServlet = servletContext.getNamedDispatcher(JSP_SERVLET);
        if (null == jspServlet) {
            throw new RuntimeException("there is no jsp servlet");
        }
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        String path = requestProcessorChain.getPath();
        if (isJapResource(path)) {
            jspServlet.forward(requestProcessorChain.getRequest(), requestProcessorChain.getResponse());
            return false;
        }
        return true;
    }

    private boolean isJapResource(String path) {
        return path.startsWith(JSP_RESOURCE_PREFIX);
    }
}
