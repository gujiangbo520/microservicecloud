package com.gujiangbo.springcloud.zuul.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *
 * @author gujiangbo
 */
@EnableZuulProxy
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Zuul_9527_StartSpringCloudApp extends SpringBootServletInitializer implements CommandLineRunner {

    private Log debuglog = LogFactory.getLog(Zuul_9527_StartSpringCloudApp.class);

    public static void main(String[] args) {
        SpringApplication.run(Zuul_9527_StartSpringCloudApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(new Class[] { Zuul_9527_StartSpringCloudApp.class });
    }

    @Override
    public void run(String... args) throws Exception {
        debuglog.info("=========System of Zuul Server 9527 start info!=================");
        debuglog.debug("========System of Zuul Server 9527 start debug!================");
    }
}