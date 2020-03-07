package com.gpnu.boshen.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义mybatis配置
 */

@Configuration
public class MybatisConfig {

    @Bean   //注解意味着加入到
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                //开启驼峰命名法
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };

    }
}
