package com.gpnu.boshen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {

            //首页轮播图加载路径
            private String INDEX_UPLOAD_FOLDER = "///E:/boshen/demos/news/images/carousel/";
            //关于我们轮播图加载路径
            private String ABOUT_UPLOAD_FOLDER = "///E:/boshen/images/about/";

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/index").setViewName("index");
                registry.addViewController("/file").setViewName("file");
            }

            //            拦截器
           /* @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html", "/", "/user/login");
            }*/

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/index/slider/**").addResourceLocations("file:" + INDEX_UPLOAD_FOLDER);
                registry.addResourceHandler("/about/slider/**").addResourceLocations("file:" + ABOUT_UPLOAD_FOLDER);
            }
        };
        return webMvcConfigurer;
    }
}
//@Configuration
//public class MvcConfig extends WebMvcConfigurationSupport{
//    private String UPLOAD_FOLDER = "///C:/Users/Administrator/Desktop/images/";
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        super.addResourceHandlers(registry);
//        registry.addResourceHandler("/img/**").addResourceLocations("file:" + UPLOAD_FOLDER);
//    }
//}