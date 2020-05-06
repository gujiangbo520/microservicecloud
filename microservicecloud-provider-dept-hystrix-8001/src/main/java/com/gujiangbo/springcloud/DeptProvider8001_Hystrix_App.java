package com.gujiangbo.springcloud;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author gujiangbo
 */
@EnableDiscoveryClient //开启服务发现
@EnableEurekaClient //开启eureka客户端
@MapperScan(basePackages = { "com.gujiangbo.springcloud.mapper" })
@SpringBootApplication
@EnableAsync
@EnableHystrix//开启服务熔断功能
public class DeptProvider8001_Hystrix_App extends SpringBootServletInitializer implements CommandLineRunner {

	private Log debugLog = LogFactory.getLog(DeptProvider8001_Hystrix_App.class);

	public static void main(String[] args) {
		SpringApplication.run(DeptProvider8001_Hystrix_App.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(new Class[] { DeptProvider8001_Hystrix_App.class });
	}

	@Override
	public void run(String... args) throws Exception {
		debugLog.info("=========system of deptProvider Hystrix start info !============");
		debugLog.debug("=========system of deptProvider Hystrix start debug !============");

	}

}
