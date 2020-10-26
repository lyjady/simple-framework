package org.simpleframework.mvc.render;

import com.google.gson.Gson;
import org.simpleframework.mvc.RequestProcessorChain;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.PrintWriter;

/**
 * @author: linyongjin
 * @create: 2020-10-26 09:05:15
 */
public class JsonResultRender implements ResultRender {

    private Object result;

    public JsonResultRender(Object result) {
        this.result = result;
    }

    @Override
    public void render(RequestProcessorChain chain) throws Exception {
        HttpServletResponse response = chain.getResponse();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(new Gson().toJson(result));
        writer.flush();
    }
}
