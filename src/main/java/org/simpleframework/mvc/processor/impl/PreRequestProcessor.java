package org.simpleframework.mvc.processor.impl;

import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.processor.RequestProcessor;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @author: linyongjin
 * @create: 2020-10-15 16:01:48
 */
public class PreRequestProcessor implements RequestProcessor {
    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        HttpServletRequest request = requestProcessorChain.getRequest();
        request.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        String path = requestProcessorChain.getPath();
        if (path.length() > 1 && path.endsWith("/")) {
            requestProcessorChain.setPath(path.substring(0, path.length() - 1));
        }

        return true;
    }
}
