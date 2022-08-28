package com.myflx.servlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.io.IOException;
import java.util.Set;

@HandlesTypes(MyWebInitializer.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("待处理的类型：" + c);
        ctx.addServlet("dispatch2", new GenericServlet() {
            @Override
            public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
                res.getWriter().write("this is dispatch1 servlet...");
                res.getWriter().flush();
            }
        }).addMapping("/dispatch2");
    }
}
