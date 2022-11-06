package com.myflx.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("this is HelloServlet...");
        resp.getWriter().flush();
        /*req.setCharacterEncoding("utf-8");
        BufferedReader reader = req.getReader();
        String s = reader.readLine();
        System.out.println(s);*/
        ServletInputStream inputStream = req.getInputStream();
        byte[] bytes = new byte[6];
        inputStream.read(bytes);
        String s = new String(bytes,"utf-8");
        System.out.println(s);

        resp.setCharacterEncoding("UTF-8");
    }
}
