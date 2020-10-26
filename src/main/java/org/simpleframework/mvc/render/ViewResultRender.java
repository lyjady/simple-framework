package org.simpleframework.mvc.render;

import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.type.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: linyongjin
 * @create: 2020-10-21 09:08:28
 */
public class ViewResultRender implements ResultRender {

    private ModelAndView modelAndView;

    private static final String VIEW_PATH = "/templates/";

    public ViewResultRender(Object mv) {
        if (mv instanceof ModelAndView) {
            modelAndView = (ModelAndView) mv;
        } else if (mv instanceof String){
            modelAndView = new ModelAndView().setView((String) mv);
        } else {
            throw new RuntimeException("illegal request result type");
        }
    }

    @Override
    public void render(RequestProcessorChain chain) throws Exception {
        HttpServletRequest request = chain.getRequest();
        HttpServletResponse response = chain.getResponse();
        String path = modelAndView.getView();
        Map<String, Object> model = modelAndView.getModel();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        request.getRequestDispatcher(VIEW_PATH + path).forward(request, response);
    }
}
