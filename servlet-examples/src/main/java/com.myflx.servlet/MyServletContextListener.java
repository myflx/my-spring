package com.myflx.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import java.io.IOException;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.addServlet("dispatch1", new GenericServlet() {
            @Override
            public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
                res.getWriter().write("this is dispatch1 servlet...");
                res.getWriter().flush();
            }
        }).addMapping("/dispatch1");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
