package com.myflx.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/async", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object asyncManager = req.getAttribute("AsyncManager");
        if (asyncManager != null) {
            resp.getWriter().print("SUCCESS");
            resp.getWriter().flush();
            return;
        }
        AsyncContext asyncContext = req.startAsync();
        asyncContext.start(() -> {
            try {
                req.setAttribute("AsyncManager", new Object());
                asyncContext.dispatch();
//                TimeUnit.SECONDS.sleep(2L);
//                resp.getWriter().print("SUCCESS");
//                resp.getWriter().flush();
//                asyncContext.complete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
