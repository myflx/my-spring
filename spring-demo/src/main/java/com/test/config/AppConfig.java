package com.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration("appConfig")
@ComponentScan("com.test")
//@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
//@EnableAsync(mode = AdviceMode.ASPECTJ)
@EnableAsync
public class AppConfig {
}
