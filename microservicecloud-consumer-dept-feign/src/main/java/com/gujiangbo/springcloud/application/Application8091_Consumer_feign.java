package com.gujiangbo.springcloud.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author gujiangbo
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.gujiangbo.springcloud"})//启动feign负载均衡能力
@ComponentScan("com.gujiangbo.springcloud")
public class Application8091_Consumer_feign {

    public static void main(String[] args) {
        SpringApplication.run(Application8091_Consumer_feign.class, args);
    }
}
