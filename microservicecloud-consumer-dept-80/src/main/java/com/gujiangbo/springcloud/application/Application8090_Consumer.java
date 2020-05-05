package com.gujiangbo.springcloud.application;

import com.gujiangbo.springcloud.rule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author gujiangbo
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "MICROSERVICECLOUD-DEPT" , configuration = MyRule.class)
public class Application8090_Consumer {

    public static void main(String[] args) {
        SpringApplication.run(Application8090_Consumer.class, args);
    }
}
