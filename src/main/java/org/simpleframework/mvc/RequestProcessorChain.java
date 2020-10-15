package org.simpleframework.mvc;

import org.simpleframework.mvc.processor.RequestProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * @author: linyongjin
 * @create: 2020-10-15 15:58:10
 */
public class RequestProcessorChain {

    public RequestProcessorChain(Iterator<RequestProcessor> iterable, HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 执行请求链
     */
    public void doRequestProcessorChain() {

    }

    /**
     * 执行处理器
     */
    public void doRender() {

    }
}
