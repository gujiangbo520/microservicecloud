package com.gujiangbo.eureka.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * 
 * @author gujiangbo
 * @EnableEurekaServer 开启Eureka Server 服务
 */
@SpringBootApplication
@EnableEurekaServer
@EnableAsync
public class Eureka7002Application extends SpringBootServletInitializer implements CommandLineRunner{

	private Log debuglog = LogFactory.getLog(Eureka7002Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Eureka7002Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(new Class[] { Eureka7002Application.class });
	}

	@Override
	public void run(String... args) throws Exception {
		debuglog.info("=========System of Eureka Server 7002 start info!=================");
		debuglog.debug("========System of Eureka Server 7002 start debug!================");
	}
}
