package com.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(
        value = "com.test",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {Controller.class, RestController.class}
                )
        }, useDefaultFilters = false
)
public class MyServletConfig {
}
