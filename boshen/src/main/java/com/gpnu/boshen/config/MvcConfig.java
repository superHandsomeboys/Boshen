package com.gpnu.boshen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {

            /*//首页轮播图加载路径
            private String INDEX_UPLOAD_FOLDER = "///E:/boshen/demos/news/images/carousel/";
            //关于我们轮播图加载路径
            private String ABOUT_UPLOAD_FOLDER = "///E:/boshen/images/about/";*/

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/index").setViewName("首页.html");
                registry.addViewController("/test").setViewName("test.html");
            }

            /*//            拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new HandlerInterceptor() {
                    @Override
                    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                        return false;
                    }

                    @Override
                    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

                    }

                    @Override
                    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

                    }
                }).addPathPatterns("/**")
                        .excludePathPatterns("/index.html", "/", "/user/login", "/index", "/textboxio/**", "/static/**" );
            }*/

            /*@Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/index/slider/**").addResourceLocations("file:" + INDEX_UPLOAD_FOLDER);
                registry.addResourceHandler("/about/slider/**").addResourceLocations("file:" + ABOUT_UPLOAD_FOLDER);
            }*/

        };
        return webMvcConfigurer;
    }
}
