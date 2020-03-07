package com.gpnu.boshen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
    private String UPLOAD_FOLDER = "///C:/Users/Administrator/Desktop/images/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + UPLOAD_FOLDER);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
                                                        .addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        //favicon.ico图标不生效
    }
}
