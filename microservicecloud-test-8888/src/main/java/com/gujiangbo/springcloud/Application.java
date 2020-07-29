package com.gujiangbo.springcloud;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 * 备注：spring boot发布jar包web程序的入口是main函数所在的类，使用@SpringBootApplication注解。
 * 但是如果war包发布至tomcat，需要增加 SpringBootServletInitializer 子类，并覆盖它的 configure 方法，
 * 或者直接将main函数所在的类继承 SpringBootServletInitializer 子类，并覆盖它的 configure 方法。
 *
 * @author gujiangbo
 */
//@MapperScan(basePackages = {"com.gujiangbo.application.mapper"})
@SpringBootApplication
@EnableAsync
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
    private Log debugLog = LogFactory.getLog(Application.class);


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(new Class[]{Application.class});
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        debugLog.info("=========system of Application start info !============");
        debugLog.debug("=========system of Application start debug !============");

    }
}

