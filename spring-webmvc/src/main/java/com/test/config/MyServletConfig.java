package com.test.config;

import com.test.controller.OrderController;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@ComponentScan(
        value = "com.test",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Controller.class, RestController.class}
                )
        }, useDefaultFilters = false
)
//@EnableWebMvc
public class MyServletConfig extends DelegatingWebMvcConfiguration {
    @SneakyThrows
    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping(ContentNegotiationManager mvcContentNegotiationManager, FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
        RequestMappingHandlerMapping requestMappingHandlerMapping = super.requestMappingHandlerMapping
                (mvcContentNegotiationManager, mvcConversionService, mvcResourceUrlProvider);
        RequestMappingInfo build = RequestMappingInfo.paths("/hello2").build();
        OrderController bean = getApplicationContext().getBean(OrderController.class);
        requestMappingHandlerMapping.registerMapping(build, bean, OrderController.class.getMethod("hello"));
        return requestMappingHandlerMapping;
    }

    @Override
    public HandlerMapping defaultServletHandlerMapping() {
        //SimpleUrlHandlerMapping(key-value)
        return super.defaultServletHandlerMapping();
    }

    @Bean("/my-http")
    public MyHttpRequestHandler myHttpRequestHandler(){
        return new MyHttpRequestHandler();
    }

    @Bean("/user-action")
    public UserAction userAction(){
        return new UserAction();
    }//当前系统依赖---> action jar

    @Bean
    public ActionHandlerAdapter actionHandlerAdapter(){
        return new ActionHandlerAdapter();
    }
}
