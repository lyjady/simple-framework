package org.simpleframework.mvc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.mvc.processor.RequestProcessor;
import org.simpleframework.mvc.render.DefaultResultRender;
import org.simpleframework.mvc.render.InternalErrorResultRender;
import org.simpleframework.mvc.render.ResultRender;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

/**
 * @author: linyongjin
 * @create: 2020-10-15 15:58:10
 */
@Data
@Slf4j
public class RequestProcessorChain {

    private Iterator<RequestProcessor> iterator;

    private HttpServletRequest request;

    private HttpServletResponse response;

    private String method;

    private String path;

    private int responseCode;

    private ResultRender resultRender;

    public RequestProcessorChain(Iterator<RequestProcessor> iterator, HttpServletRequest request, HttpServletResponse response) {
        this.iterator = iterator;
        this.response = response;
        this.request = request;
        this.method = request.getMethod();
        this.path = request.getPathInfo();
        this.responseCode = HttpServletResponse.SC_OK;
    }

    /**
     * 执行请求链
     */
    public void doRequestProcessorChain() throws Exception {
        try {
            while (iterator.hasNext()) {
                if (!iterator.next().process(this)) {
                    break;
                }
            }
        } catch (Exception e) {
            this.resultRender = new InternalErrorResultRender(e.getMessage());
            log.error("error message: {}", e.getMessage());
        }
    }

    /**
     * 执行处理器
     */
    public void doRender() {
        if(this.resultRender == null){
            this.resultRender = new DefaultResultRender();
        }
        //2.调用渲染器的render方法对结果进行渲染
        try {
            this.resultRender.render(this);
        } catch (Exception e) {
            log.error("doRender error: ", e);
            throw new RuntimeException(e);
        }
    }
}
