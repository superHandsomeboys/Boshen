package com.example.demo.config;

import com.example.demo.dynamic.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {
//    private String UPLOAD_FOLDER = "///C:/Users/Administrator/Desktop/images/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/img/**").addResourceLocations("file:" + UPLOAD_FOLDER);
        registry.addResourceHandler("/img/**").addResourceLocations("file:" + Data.UPLOAD_IMAGE_PATH);
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/")
                                                        .addResourceLocations("classpath:/public/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        //favicon.ico图标不生效
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
    }
}
