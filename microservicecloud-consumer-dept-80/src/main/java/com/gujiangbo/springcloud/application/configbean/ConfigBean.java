package com.gujiangbo.springcloud.application.configbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * SprngBoot 配置类 作用相当于Spring的applicationContext.xml 文件
 *
 * @author gujiangbo
 */
@Configuration
public class ConfigBean {

    /**
     * @return
     * @description 提供Rest服务接口调用模板
     * RestTemplate 提供了多种便捷访问远程HTTP服务的方法
     * 是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集
     */
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}