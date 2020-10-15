package org.simpleframework.mvc;

import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInject;
import org.simpleframework.mvc.processor.RequestProcessor;
import org.simpleframework.mvc.processor.impl.ControllerRequestProcessor;
import org.simpleframework.mvc.processor.impl.JspRequestProcessor;
import org.simpleframework.mvc.processor.impl.PreRequestProcessor;
import org.simpleframework.mvc.processor.impl.StaticResourcesRequestProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LinYongJin
 * @date 2020/8/13 14:47
 */
@WebServlet("/*")
public class DispatcherServlet extends HttpServlet {

    List<RequestProcessor> PROCESSOR = new ArrayList<>(4);

    @Override
    public void init() throws ServletException {
        BeanContainer beanContainer = BeanContainer.getInstance();
        new DependencyInject().doInject();
        PROCESSOR.add(new PreRequestProcessor());
        PROCESSOR.add(new JspRequestProcessor());
        PROCESSOR.add(new StaticResourcesRequestProcessor());
        PROCESSOR.add(new ControllerRequestProcessor());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 创建责任链对象实例
        RequestProcessorChain requestProcessorChain = new RequestProcessorChain(PROCESSOR.iterator(), req, resp);
        // 通过责任链模式调用请求处理器对请求进行处理
        requestProcessorChain.doRequestProcessorChain();
        // 对处理结果记性渲染
        requestProcessorChain.doRender();
    }

}
