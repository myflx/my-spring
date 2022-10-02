package com.test.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    void doAction(HttpServletRequest servletRequest, HttpServletResponse response);
}
