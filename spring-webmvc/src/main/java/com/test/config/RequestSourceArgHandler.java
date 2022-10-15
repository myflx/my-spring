package com.test.config;

import com.test.controller.RequestSource;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class RequestSourceArgHandler implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(RequestSource.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String sourceType = webRequest.getHeader("sourceType");
        RequestSource requestSource = null;
        if (sourceType != null){
            requestSource = new RequestSource();
            requestSource.setSource(sourceType);
        }
        return requestSource;
    }
}
