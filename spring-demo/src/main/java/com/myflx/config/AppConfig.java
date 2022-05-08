package com.myflx.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration("appConfig")
@ComponentScan("com.myflx")
//@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AppConfig {
}
