# Microservicecloud
**this is a simple service of springcloud**
## Eureka 注册与发现

## Ribbon 负载均衡

## Feign 负载均衡

## Hystrix 断路器
### 概述
分布式系统面临的问题
>复杂的分布式系统结构中的应用程序可能有数十几个依赖关系，每个依赖关系将不可避免的失败。

服务雪崩
>多个微服务之间调用的时候，假设微服务A调用微服务B,微服务B调用微服务C，微服务C又调用其他的微服务，这种调用方式称为“扇出”。如果在扇出的某个微服务环节调用响应时间过长或者不可用，对该服务调用就会占用越来越多的系统资源，进而引起系统的崩溃，就称之为“雪崩效应”。<br>
对于高流量的系统来说，单一的后端依赖可能会在几秒钟达到饱和状态。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加。备份队列、线程和其他系统资源紧张，导致整个系统发生更多的级联故障。这些都表示需要对故障和延迟进行隔离和管理，以便单个依赖关系的失败，不能取消整个应用程序或系统。

Hystrix是什么？
>Hystrix是一个用于处理分布式系统的**延迟和容错**的开源库。在分布式系统中，许多依赖不可避免的会调用失败，比如超时，异常等等。**Hystrix能够保证在一个依赖出问题的情况下，不会导致整体服务出现失败，避免级联故障，以提高分布式系统的弹性。** <br>
"断路器"本身是一种开关装置，当某个服务单元发生故障后，通过断路器的故障监控向调用方法返回一个符合预期的、可处理的备选响应（FallBack),而不是长时间的等待或者抛出无法处理的异常等，这样就保证了服务调用方的线程不会长时间不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

Hystrix能做什么？
>服务降级、服务熔断、服务监控、服务限流等

### 服务熔断
什么是服务熔断？
>熔断机制是应对雪崩效应的一种微服务链路保护机制.
>当扇出链路某个微服务不可用或者响应时间不长时，会进行服务的降级，**进而熔断该节点微服务的调用，快速返回“错误”的响应信息** 即Fallback。当检测到该节点微服务调用响应正常后恢复调用链路。在SpringCloud框架里熔断机制通过Hystrix实现。Hystrix会监控微服务之间的调用情况。当失败的调用到达一定的阈值，缺省是5s内20次调用失败就会启动熔断机制。熔断机制的注解是：**@HystrixCommand**

服务熔断simple
pom.xml添加支持hystrix jar
```xml
<!-- 引入hystrix 熔断器 -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
	<version>1.4.7.RELEASE</version>
</dependency>
```
java 主要代码如下:
```java
public class DeptController{
  /**
     * 模拟熔断机制
     * @HystrixCommand(fallbackMethod = "processHystrix_Get")
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findDeptById", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public String findDeptById(@RequestBody String id) {
        debugLog.info("根据部门ID查询部门");
        debugLog.info("请求参数:" + id);
        Dept dept = new Dept();
        try {
            dept = deptService.getDeptById(Long.valueOf(id));
        } catch (Exception e) {
            debugLog.error("数据处理异常!", e);
        }
        if(null == dept){
            throw new RuntimeException("未找到对应信息");
        }
        return JSONObject.toJSONString(dept);
    }
    /**
     * hystrix 熔断机制模拟调用的方法
     * 只要在@HystrixCommand(fallbackMethod = "processHystrix_Get")注解方法上 出现异常等信息才调用
     * @param id
     * @return
     */
    public String processHystrix_Get(@RequestBody String id) {
        debugLog.info("开始调用熔断方法!");
        return "这是一个熔断方法！！！！";
    }
}
 
```
在Application启动类中添加@EnableHystrix注解
```java
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
```
当业务调用findDeptById方法，传入id不存在的编号时会抛出一个异常信息，Hystrix 检测到异常信息会触发服务熔断机制，调用**fallbackMethod **中定义的processHystrix_Get方法，也就是说processHystrix_Get方法是调用findDeptById 出现异常后的后备方法。

### 服务降级
什么是服务降级？
>整体资源快不够用了，忍痛将某些服务先关掉，待度过难关，在开启回来。
所谓降级，就是一般是从整体符合考虑，就是当某个服务熔断之后，服务器将不再被调用，此刻客户端可以自己准备一个本地的fallback回调，返回一个缺省值，这样做，虽然服务水平下降，但好歹可用，比直接挂掉要强。

代码演示：
修改服务接口的提供者项目(microservicecloud-api)，让service接口实现一个FallbackFactory接口类DeptClientServiceFallbackFactory
**注意：直接在接口定义的熔断机制中进行服务熔断，之前在controller上的@HystrixCommand(fallbackMethod=”methodName”)将弃用**
```java
@Component//不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept get(long id) {
                Dept dept = new Dept();
                dept.setDeptno(id);
                dept.setDname("该ID：" + id + "没有对应的信息，Consumer客户端提供的信息，此服务Provider已关闭");
                dept.setDb_source("no this database in mysql");
                return dept;
            }
            @Override
            public List<Dept> list() {
                return null;
            }
            @Override
            public boolean add(Dept dept) {
                return false;
            }
        };
    }
}
```
修改提供服务的service熔断处理的机制
此处是在公共的service对某个service的方法访问进行统一的fallback处理
```java
@FeignClient(value = "MICROSERVICECLOUD-DEPT",fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") long id);
    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();
    @RequestMapping(value = "/dept/add}", method = RequestMethod.POST)
    public boolean add(Dept dept);
}
```
修改负载均衡项目的yml
>新增加配置项到microservicecloud-consumer-dep-feign

```yaml
feign:
  hystrix:
    enabled: true
```
测试：1、先启动3个eureka2、启动microservicecloud-provider-dept-8001,3、启动microservicecloud-consumer-dept-feign<br>
服务熔断和服务降级区别
>服务熔断：一般是某个服务故障或者异常引起的，类似于现实世界中的“保险丝”,当某个异常条件被触发，直接熔断整个服务，而不是一直等到此服务超时。

>服务降级：所谓降级，一般是从整体负荷考虑，就是当某个服务熔断之后，服务器将不再调用，此时客户端可以自己准备一个本地的fallback回调，返回一个缺省值。这样做，虽然服务水平下降，但好歹可用，比直接挂掉强。

### 服务监控hystrixDashboard
服务监控描述
>除了隔离依赖服务的调用之外，Hystrix还提供了**准实时的调用监控(Hystrix Dashboard) **, Hystrix会持续地记录所有通过Hystrix发起的请求的执行信息，并以统计报表的形式展示给客户。包括每秒执行多少请求，有多少成功多少失败的等。Netflix通过hystrix-metrics-event-stream项目实现对以上指标的监控。SpringCloud 也提供了Hystrix Dashboard 的整合，对监控内容转化可视化界面。

核心文件介绍
1、新建项目
>microservicecloud-consumer-hystrix-dashboard

pom文件的修改
```xml
<!-- hystrix和 hystrix-dashboard相关 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
</dependency>
```
yml文件修改
```yaml
server:
  port: 9001
```
主启动类添加EnableHystrixDashboard
**@ EnableHystrixDashboard**开启仪表盘监控注解
```java
@SpringBootApplication
@EnableHystrixDashboard
public class DeptConsumer_DashBoard_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_DashBoard_App.class, args);
    }
}
```
微服务提供者添加监控jar
>例如微服务提供者microservicecloud-provider-dept-8001/8002/8003
添加以下jar包，才能被dashboard监控
```xml
<!-- actuator监控信息完善 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
测试：
>1、启动dashboard监控微服务http://localhost:9001/hystrix

![DashBoard](https://img-blog.csdn.net/20180722190306219?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d3dzEwNTY0ODExNjc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70 "DashBoard")
>2、然后启动3个集群服务，启动microservicecloud-provider-dept-hystrix-8001
输入访问地址：http://localhost:8001/hystrix.stream

>3、在9001的监控界面输入要监控的微服务（http://localhost:8001/hystrix.stream）

会看到以下监控内容
![dashboard_show](https://img-blog.csdn.net/20180722202703747?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d3dzEwNTY0ODExNjc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70 "dashboard_show")

>Deplay 该参数用来控制服务器上轮询监控信息的延迟时间，默认是2000毫秒，可以通过配置该属性来降低客户端的网络和cpu消耗。

>Title该参数对应了头部标题Hystrix Stream之后的内容，默认会使用哦具体监控实例的URL，可疑通过配置该信息来展示更合适的标题。

## zuul 路由网关
什么是zuul?
>Zuul包含了对请求的**路由和过滤**两个主要的功能。
>其中路由功能负责将外部请求转发到具体的微服务实例上，是实现外部访问统一的入口的基础而过滤器功能则负责对请求的处理过程进行干预，是实现请求校验、服务聚合等功能的基础。Zuul和Eureka进行整合，将Zuul自身注册为Eureka服务治理下的应用，同时从Eureka中获取其他微服务的消息，也即以后访问微服务都是通过Zuul跳转获得。

注意：Zuul服务最终还是会注册进Eureka
**Zuul提供了代理、路由、过滤三大功能**

环境搭建过程
>新建项目microservicecloud-zuul-gateway-9527
>新增加系统映射域名
Windows对应的位置是：C:\Windows\System32\drivers\etc\hosts
Linux对应的是：/etc/hosts
127.0.0.1      myzuul.com

pom核心文件
```xml
<-- zuul路由网关 -->
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-zuul</artifactId>
</dependency>
<dependency>
   <groupId>org.springframework.cloud</groupId>
   <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
```
yml文件
```yaml
server:
  port: 9527
spring:
  application:
    name: microservicecloud-zuul-gateway
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka
  instance:
    instance-id: gateway-9527.com
    prefer-ip-address: true
info:
  app.name: atguigu-microcloud
  company.name: www.atguigu.com
  build.artifactId: $project.artifactId$
  build.version: $project.version$
```
新增加主启动类，添加@EnableZuulProxy
```java
@EnableZuulProxy
@SpringBootApplication
public class Zuul_9527_StartSpringCloudApp {
    public static void main(String[] args) {
        SpringApplication.run(Zuul_9527_StartSpringCloudApp.class, args);
    }
}
```
测试
>启动三个eureka集群,一个microservicecloud-provider-dept-8001，启动路由网关微服务
```html
使用路由：http://localhost:8001/dept/get/2
启用路由：http://myzuul.com:9527/microservicecloud-dept/dept/get/2
```
![image1](https://img-blog.csdn.net/20180722204101285?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d3dzEwNTY0ODExNjc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70 "image1")
![image2](https://img-blog.csdn.net/20180722204110313?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d3dzEwNTY0ODExNjc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70 "image2")
![image3](https://img-blog.csdn.net/20180722204137281?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d3dzEwNTY0ODExNjc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70 "image3")
路由访问映射规则
>修改microservicecloud-zuul-gateway-9527项目的yml文件：新增加以下内容
```yaml
# 路由映射
zuul:
  prefix: /atguigu #增加统一的访问前缀
  #此处添加ignored-services的意义是忽略通过服务名可以访问微服务(添加完成之后不能通过服务名访问微服务)
  ignored-services:  microservicecloud-dept
  #ignored-services:  microservicecloud-dept
  routes:
    mydept.serviceId: microservicecloud-dept
    mydept.path: /mydept/**
```
**ignored-services:特别注意此处的配置。通过配置此选项，限制不能通过访问服务名去访问微服务**
如果想把其他很多的微服务都通过配置忽略掉真实访问路径则直接使用”*”来代替
修改后，实际的访问地址是以下地址：
```yaml
http://myzuul.com:9527/microservicecloud-dept/dept/get/2(原始)
http://myzuul.com:9527/mydept/dept/get/2(修改后)
```
![image4](https://img-blog.csdn.net/20180722204515831?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d3dzEwNTY0ODExNjc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70 "image4")

## SpringCloud Config SpringCloud 配置中心
**架构图**
![springCloud-config](https://img-blog.csdnimg.cn/20200507095116851.jpeg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zNzU0NzE5Nw==,size_16,color_FFFFFF,t_70 "springCloud-config")

**SpringCloud Config 是什么？**
>SpringCloud Config 为微服务架构中的微服务提供了集中化的外部配置支持，配置服务为各个不同微服务应用的所有环境提供了一个中心化的外部配置。

**SpringCloud Config 怎么操作?**
>SpringCLoud Config 分为服务端和客户端两部分<br />
服务端也称为分布式配置中心，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置的信息，加密/解密信息等访问接口<br />
客户端则是通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息配置服务器默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理，并且可以通过git客户端工具来方便的管理和访问配置内容。

**SpringCloudConfig 能做什么？**
* 集中管理配置文件
* 不同环境不同的配置，动态化的配置更新，分环境部署比如dev/test/prod/release
* 运行期间动态调整配置，不再需要在每个服务器部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
* 当配置发生变动时，服务不需要重启即可赶至到配置的变化并应用新的配置
* 将配置信息以REST接口的形式暴露

**SpringCloud Config与GitHub整合配置**
>由于SpringCloud Config 默认使用Git来存储配置文件（也有其它方式，比如支持svn和本地文件)，但是最推荐的还是Git,而且使用的是http/https访问的形式

### SpringCloud Config服务端配置步骤
* 用自己的GitHub账号在GitHub上新建一个microservicecloud-config的新Repository
* 由上一步获取ssh协议的git地址
* 本地硬盘目录上新建git仓库并clone
* 在本地的microservicecloud-config里面新建一个application.yml
* 将上一步的yml文件推送到github上
* 新建modue模块microservicecloud-config-3344,它即为Cloud的配置中心模块
* pom文件增加依赖
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
yml 文件
```yaml
server:
  port: 3344
spring:
  application:
    name: microservicecloud-config
  cloud:
    config:
      server:
        git:
          #username:  xxxx
          #password: xxxx
          uri: https://github.com/gujiangbo520/microservicecloud-config.git #github上的原始地址
```
主启动类添加EnableConfigServer注解
```java
@SpringBootApplication
@EnableConfigServer
public class Config_3344_StartSpringCloudApp {
    public static void main(String[] args) {
        SpringApplication.run(Config_3344_StartSpringCloudApp.class, args);
    }
}
```
修改主机映射
>Windows对应的位置是：C:\Windows\System32\drivers\etc\hosts
Linux对应的是：/etc/hosts
127.0.0.1 config-3344.com

测试
>启动微服务3344
>访问http://config-3344.com:3344/application-dev.yml
![springcloud-config-2](https://img-blog.csdn.net/20180728131138281?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d3dzEwNTY0ODExNjc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70 "springcloud-config-2")

###本地config搭建
在本地的项目microservicecloud-config下创建microservicecloud-config-client.yml文件
microservicecloud-config-client.yml内容如下：
```yaml
spring:
  profiles:
    active:
      - dev
---
server:
  port: 8201
spring:
  profile: dev
  application:
      name: microservicecloud-config-client
eureka:
  client:
    service-url:
      defaultZone: http://eureka-dev.com:7001/eureka/
---
server:
  port: 8202
spring:
  profiles: test
  application:
    name:  microservicecloud-config-client
eureka:
  client:
    service-url:
        defaultZone:  http://eureka-test.com:7001/eureka/
```
然后上传到github上本人地址为https://github.com/gujiangbo520/microservicecloud-config

###客户端的搭建
>创建客户端modulemicroservicecloud-config-client-3355

pom文件
```yaml
<!-- SpringCloud Config客户端 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```
>**Application.ym(是用户级的配置资源项)<br/>
Bootstrap.yml(是系统级的，优先级更高)**

创建bootstrap.yml文件
```yaml
spring:
  cloud:
    config:
      name: microservicecloud-config-client # 需要从github上读取资源名称 注意没有yml
      profile: dev # 本次访问的配置项
      label:  master
      uri:  http://config-3344.com:3344 # 本资源微服务启动后先去找3344号服务，通过SpringCloudConfig获取Github的服务地址
```
application.yml文件
```yaml
spring:
  application:
    name:  microservicecloud-config-client
```
host下添加映射文件
>127.0.0.1 client-config.com

新建controller类ConfigClientRest
```java
package com.atguigu.springcloud.rest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ConfigClientRest
{
   @Value("${spring.application.name}")
   private String applicationName;
   @Value("${eureka.client.service-url.defaultZone}")
   private String eurekaServers;
   @Value("${server.port}")
   private String port;
   @RequestMapping("/config")
   public String getConfig() {
      String str = "applicationName: " + applicationName + "\t eurekaServers:" + eurekaServers + "\t port: " + port;
      System.out.println("******str: " + str);
      return "applicationName: " + applicationName + "\t eurekaServers:" + eurekaServers + "\t port: " + port;
   }
}
```
启动主动类
```java
@SpringBootApplication
public class ConfigClient_3355_StartSpringCloudApp{
   public static void main(String[] args) {
      SpringApplication.run(ConfigClient_3355_StartSpringCloudApp.class, args);
   }
}
```
测试
>启动config的服务器3344<br/>
然后启动3355作为client准备访问

测试1:
>切换bootstrap.yml的访问配置项
profile：dev(切换bootstrap.yml的访问配置项)
              1、dev默认在github上对应的端口就是8201
             2、http://client-config.com:8201/config

![image3.jpg](https://img-blog.csdn.net/20180728132752293?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d3dzEwNTY0ODExNjc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70 "image3.jpg")
Profile: test
> 1、test默认在github上对应的端口就是8202
2、http://client-config.com:8202/config

![image4.jpg](https://img-blog.csdn.net/20180728132849571?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3d3dzEwNTY0ODExNjc=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70 "image4.jpg")














