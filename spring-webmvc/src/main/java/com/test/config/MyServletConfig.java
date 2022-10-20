package com.test.config;

import com.test.controller.OrderController;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
//@EnableAsync
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
    public MyHttpRequestHandler myHttpRequestHandler() {
        return new MyHttpRequestHandler();
    }

    @Bean("/user-action")
    public UserAction userAction() {
        return new UserAction();
    }//当前系统依赖---> action jar

    @Bean
    public ActionHandlerAdapter actionHandlerAdapter() {
        return new ActionHandlerAdapter();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        System.out.println("开始处理请求");
                        return true;
                    }

                    @Override
                    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                        System.out.println("处理请求结束");
                    }

                    @Override
                    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
                        System.out.println("处理请求完成");
                    }
                });
            }
        };
    }

    @Bean
    public WebMvcConfigurer requestSourceMethodResolver(){
        return new WebMvcConfigurer() {
            @Override
            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
                resolvers.add(new RequestSourceArgHandler());
            }
        };
    }

}
