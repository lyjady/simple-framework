package org.augustus;

import lombok.extern.slf4j.Slf4j;
import org.augustus.entity.bo.HeadLine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LinYongJin
 * @date 2020/8/10 20:16
 */
@Slf4j
@WebServlet(urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("SimpleFramework...");
        HeadLine headLine = new HeadLine();
        headLine.setLineId(1L);
        headLine.setLineName("Servlet");
        System.out.println(headLine);
        req.setAttribute("name", "SimpleFramework");
        req.getRequestDispatcher("/WEB-INF/jsp/Welcome.jsp").forward(req, resp);
    }
}
