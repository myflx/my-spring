package com.test.config;

import lombok.SneakyThrows;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAction implements Action {
    @SneakyThrows
    @Override
    public void doAction(HttpServletRequest servletRequest, HttpServletResponse response) {
        response.getWriter().print("this is UserAction!!!!");
    }
}
