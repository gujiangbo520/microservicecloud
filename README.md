# SpringCloud

## 微服务概述

### 什么是微服务

- 目前的微服务并没有一个统一的标准，一般是以业务来划分
- 将传统的一站式应用，拆分成一个个的服务，彻底去耦合，一个微服务就是单功能业务，只做一件事。
- 与微服务相对的叫巨石

### 微服务与微服务架构

- 微服务是一种架构模式或者一种架构风格，提倡将单一应用程序划分成一组小的服务==独立部署==，服务之间相互配合、相互协调，每个服务运行于自己的==进程==中。
- 服务与服务间采用轻量级通讯，如HTTP的RESTful API等
- 避免统一的、集中式的服务管理机制

### 微服务的优缺点

#### 优点

1. 每个服务足够内聚，足够小，比较容易聚焦
2. 开发简单且效率高，一个服务只做一件事情
3. 开发团队小，一般2-5人足以（当然按实际为准）
4. 微服务是松耦合的，无论开发还是部署都可以独立完成
5. 微服务能用不同的语言开发
6. 易于和第三方集成，微服务允许容易且灵活的自动集成部署（持续集成工具有Jenkins,Hudson,bamboo等）
7. 微服务易于被开发人员理解，修改和维护，这样可以使小团队更加关注自己的工作成果，而无需一定要通过合作才能体现价值
8. 微服务允许你融合最新的技术
9. ==微服务只是业务逻辑的代码，不会和HTML,CSS或其他界面组件融合==。
10. ==每个微服务都可以有自己的存储能力，数据库可自有也可以统一，十分灵活==。

#### 缺点

1. 开发人员要处理分布式系统的复杂性
2. 多服务运维难度，随着服务的增加，运维的压力也会增大
3. 依赖系统部署
4. 服务间通讯的成本
5. 数据的一致性
6. 系统集成测试
7. 性能监控的难度

### 微服务的技术栈

| 微服务条目                               | 落地技术                                                     |
| ---------------------------------------- | ------------------------------------------------------------ |
| 服务开发                                 | SpringBoot,Spring,SpringMVC                                  |
| 服务配置与管理                           | Netflix公司的Archaius、阿里的Diamond等                       |
| 服务注册与发现                           | Eureka、Consul、Zookeeper等                                  |
| 服务调用                                 | Rest、RPC、gRPC                                              |
| 服务熔断器                               | Hystrix、Envoy等                                             |
| 负载均衡                                 | Ribbon、Nginx等                                              |
| 服务接口调用（客户端调用服务的简化工具） | Feign等                                                      |
| 消息队列                                 | Kafka、RabbitMQ、ActiveMQ等                                  |
| 服务配置中心管理                         | SpringCloudConfig、Chef等                                    |
| 服务路由（API网关）                      | Zuul等                                                       |
| 服务监控                                 | Zabbix、Nagios、Metrics、Specatator等                        |
| 全链路追踪                               | Zipkin、Brave、Dapper等                                      |
| 服务部署                                 | Docker、OpenStack、Kubernetes等                              |
| 数据流操作开发包                         | SpringCloud Stream(封装与Redis，Rabbit，Kafka等发送接收消息) |
| 事件消息总线                             | SpringCloud Bus                                              |

### 为什么选SpringCloud作为微服务架构

#### 选型依据

1. 整体解决方案和框架的成熟度
2. 社区热度
3. 可维护性
4. 学习曲线

#### 当前各大IT公司的微服务架构

1. 阿里Dubbo/HSF
2. 京东JSF
3. 新浪Motan
4. 当当DubboX

#### 各微服务的框架对比

| 功能点/服务框架 | Netflix/SpringCloud                                          | Motan                                                        | gRPC                      | Thrift   | Dubbo/DubboX    |
| --------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------- | -------- | --------------- |
| 功能定位        | 完整的微服务架构                                             | RPC框架，但整合了ZK或Consul，实现集群环境的基本服务注册/发现 | RPC框架                   | RPC框架  | 服务框架        |
| 支持Rest        | 是，Ribbon支持多种可插拔的序列化选择                         | 否                                                           | 否                        | 否       | 否              |
| 支持RPC         | 否                                                           | 是                                                           | 是                        | 是       | 是              |
| 支持多语言      | 是（Rest形式）                                               | 否                                                           | 是                        | 是       | 否              |
| 服务注册/发现   | 是（Eureka） Eureka服务注册表，Karyon服务端框架支持服务自注册和健康检查 | 是（zookeeper/consul）                                       | 否                        | 否       | 是              |
| 负载均衡        | 是（服务端zuul+客户端Ribbon） zuul-服务，动态路由 云端负载均衡 Eureka（针对中间层服务器） | 是（客户端）                                                 | 否                        | 否       | 是（客户端）    |
| 配置服务        | Netflix Archaius SpringCloud Config Server集中配置           | 是（zookeeper提供）                                          | 否                        | 否       | 否              |
| 服务调用链监控  | 是（zuul） Zuul提供边缘服务，API网关                         | 否                                                           | 否                        | 否       | 否              |
| 高可用/容错     | 是（服务端Hystrix+客户端Ribbon）                             | 是（客户端）                                                 | 否                        | 否       | 是（客户端）    |
| 典型应用案例    | Netflix                                                      | Sina                                                         | Google                    | Facebook |                 |
| 社区活跃度      | 高                                                           | 一般                                                         | 高                        | 一般     | 2017年7月才重启 |
| 学习难度        | 中等                                                         | 一般                                                         | 高                        | 一般     | 低              |
| 文档丰富度      | 高                                                           | 一般                                                         | 一般                      | 一般     | 高              |
| 其他            | Spring Cloud Bus为我们应用程序带来了更多管理端点             | 支持降级                                                     | Netflix内部在开发集成gRPC | IDL定义  | 实践公司比较多  |

## SpringCloud入门概述

- Spring的三大模块：SpringBoot（构建），Spring Cloud（协调），Spring Cloud Data Flow（连接）

### SpringCloud是什么

- 分布式系统的简化版（官方介绍）
- SpringCloud基于SpringBoot提供了一整套微服务的解决方案，包括服务注册与发现，配置中心，全链路监控，服务网关，负载均衡，熔断器等组件，除了基于Netflix的开源组件做高度抽象封装之外，还有一些选型中立的开源组件
- SpringCloud利用SpringBoot的开发便利性巧妙地简化了分布式系统的基础设施开发，SpringCloud为开发人员提供了快速构建分布式系统的一些工具，包括配置管理、服务发现、断路器、路由、微代理、事件总线，全局所、决策精选、分布式会话等等，他们都可以用SpringBoot的开发风格做到一键启动和部署。
- ==一句话概括：SpringCloud是分布式微服务架构下的一站式解决方案，是各个微服务架构落地技术的几何体，俗称微服务全家桶==

### SpringCloud和SpringBoot的关系

SpringBoot：专注于快速方便的开发单个个体微服务（关注微观）

SpringCloud：关注全局的微服务协调治理框架，将SpringBoot开发的一个个单体微服务组合并管理起来（关注宏观）

- ==SpringBoot可以离开SpringCloud独立使用，但是SpringCloud不可以离开SpringBoot，属于依赖关系==

### Dubbo是怎么到SpringCloud的？哪些优缺点去技术选型

#### 目前成熟都互联网架构（分布式+服务治理Dubbo）

![目前成熟都互联网架构（分布式+服务治理Dubbo）](https://gitee.com/xiongrj/springcloud-base/raw/master/images%5C%E7%9B%AE%E5%89%8D%E6%88%90%E7%86%9F%E7%9A%84%E4%BA%92%E8%81%94%E7%BD%91%E6%9E%B6%E6%9E%84.png)

#### 对比

|              | Dubbo         | Spring                       |
| ------------ | ------------- | ---------------------------- |
| 服务注册中心 | Zookeeper     | Spring Cloud Netfilx Eureka  |
| 服务调用方式 | RPC           | REST API                     |
| 服务监控     | Dubbo-monitor | Spring Boot Admin            |
| 断路器       | 不完善        | Spring Cloud Netflix Hystrix |
| 服务网关     | 无            | Spring Cloud Netflix Zuul    |
| 分布式配置   | 无            | Spring Cloud Config          |
| 服务跟踪     | 无            | Spring Cloud Sleuth          |
| 消息总线     | 无            | Spring Cloud Bus             |
| 数据流       | 无            | Spring Cloud Stream          |
| 批量任务     | 无            | Spring Cloud Task            |

**最大区别：**

- Spring Cloud抛弃了RPC通讯，采用基于HTTP的REST方式。Spring Cloud牺牲了服务调用的性能，但是同时也避免了原生RPC带来的问题。REST比RPC更为灵活，不存在代码级别的强依赖，在强调快速演化的微服务环境下，显然更合适。
- ==一句话：Dubbo像组装机，Spring Cloud像一体机==
- 社区的支持与力度：Dubbo曾经停运了5年，虽然重启了，但是对于技术发展的新需求，还是需要开发者自行去拓展，对于中小型公司，显然显得比较费时费力，也不一定有强大的实力去修改源码

\####总结

1. 解决的问题域不一样：Dubbo的定位是一款RPC框架，Spring Cloud的目标是微服务架构下的一站式解决方案

### SpringCloud的参考资料

## 构建SpringCloud工程

概述：SpringCloud工程由一个父工程和若干个Module组成

==应该遵循的条件：约定 > 配置 > 编码==

### RestTemplate类

#### 介绍

RestTemplate是Spring提供的用于访问Rest服务的客户端模板工具集，提供了多种远程访问Http的方法

#### 意义

在一些不涉及实现方法的模块中（消费者），只需要调用其他服务暴露出的接口即可满足的需求，使用RestTemplate类中的方法可以发出需要的HTTP请求并得到返回结果。（类似Ajax）

#### RestTemplate用法

```
RestTemplate restTemplate = new RestTemplate();
//url:请求地址
//requestMap:请求参数
//type.class:HTTP响应转换成的对象类型
restTemplate.getForObject(url,type.class);
restTemplate.postForObject(url,requestMap,type.class);
```

### 构建父工程

- 创建一个Maven父工程并命名GAV
- 打包方式为==POM==
- 在pom.xml中定义各依赖的版本号（若Module中pom.xml的依赖没有指定版本号，则会根据父工程的版本号加入依赖）
- 加入通用的依赖和插件

### 构建Module

- 在父工程下新建Maven的Module，打包方式为jar
- 一般来时GAV中 GV随父工程，自己定义A即可
- 在该Module下pom.xml中加入其它需要的依赖
- 正常开发即可
- 完成后先clean一下Maven项目，然后再install提供给其它模块调用

#### 添加其它Module的依赖方法

- 直接引用其GAV即可

```
    <dependencies>
        <dependency>
            <groupId>com.lzl</groupId>
            <artifactId>microservice-api</artifactId>
            <version>${project.version}</version>
        </dependency>
    </dependencies>
```

#### 配置该module下的yml

- 微服务需要独立的端口
- ==微服务最重要的是取名字！！！！一定要给微服务配置一个名字！这个名字就是这个微服务对外暴露的名字！==
- 配置该模块下的其它相关配置（如本例配置了mybatis）

```
server:
  port: 8001

mybatis:
  config-location: classpath:mybatis/mybatis.cfg.xml
  type-aliases-package: com.XXX.entity
  mapper-locations:
  - classpath:mybatis/mapper/**/*.xml
spring:
  application:
    name: microservicecloud-dept   #为这个服务取名，非常重要！！！！！
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    driver-class-name: org.gjt.mm.mysql.Driver
    url: jdbc:mysql://localhost:3306/cloudDB01
    username: root
    password: 123456
    dbcp2:
      min-idle: 5         #最小连接数
      initial-size: 5    #初始化连接数
      max-total: 10      #最大连接数
      max-wait-millis: 200    #等待连接最长的超时时间
```

#### 编写主启动类

- 记得主启动类放在根包下,com.xxx.xxx

```
package com.XXX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Provider8001_APP {
    public static void main(String[] args) {
        SpringApplication.run(Provider8001_APP.class,args);
    }
}
```

## SpringCloud添加组件的基本套路

1. 新增这个组件的maven坐标GAV
2. 在启动类上面标注启动该组件（一般来说是@EnableXXXXX）
3. 编写业务逻辑代码

## Eureka服务注册与发现

### Eureka介绍及原理

#### 理解

==Eureka就像一个物业管理公司，其他微服务就像小区的住户，每个住户入住时都要向物业管理公司注册，并定时向物业公司交管理费==

#### 介绍

- Eureka是一个基于REST的服务，用于定位服务，以实现云端中间层服务发现和故障转移。
- Eureka主管服务注册与发现，在微服务中，以后了这两者，只需要使用服务的标识符（==就是那个在每个服务的yml文件中取得服务名称==），就可以访问到服务，不需要修改服务调用的配置文件
- Eureka遵循AP原则（高可用，分区容错性），因为使用了自我保护机制所以保证了高可用

#### 原理

- Eureka使用的是C-S结构（客户端-服务端）
- 两大组件：Eureka Server（提供注册服务）、 Eureka Client（JAVA客户端，负责发送心跳）
- 系统中的其他微服务使用Eureka客户端连接到Eureka服务端维持心跳连接（即注册）。SpringCloud的其他模块可以通过Eureka Server 来发现系统中的微服务并加以调用

![Eureka的架构图](https://gitee.com/xiongrj/springcloud-base/raw/master/images%5CEureka%E7%9A%84%E6%9E%B6%E6%9E%84%E5%9B%BE.png)

### Eureka服务注册中心构建

#### 加入服务端依赖

```
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
        </dependency>
```

#### 配置yml

- ==理解：物业公司肯定不向自己注册自己，并肯定知道自己在哪，不用参加检索==

```
server:
  port: 7001
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false     #false表示不向注册中心注册自己
    fetch-registry: false           #false表示自己就是注册中心，职责是维护实例，不参加检索
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/    #设置eureka server的交互地址，即对外暴露的地址
```

#### 添加启动类

- ==注意：要在类前加@EnableEurekaServer标注==

```
package com.XXX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Eureka7001_APP {
    public static void main(String[] args) {
        SpringApplication.run(Eureka7001_APP.class,args);
    }
}
```

#### 验证是否构建成功

启动主程序，访问该服务地址即可

### 向Eureka注册中心注册微服务

#### 增加依赖

在要注册的微服务的pom.xml文件中增加依赖

```
		<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>	
```

#### 修改yml

- 在application.yml中增加以内容，将客户端注册到服务列表内
- ==理解：小区用户要找到物业管理处的地址进行注册==

```
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka
```

#### 主启动类增加注解

- 增加@EnableEurekaClient注解

```
@SpringBootApplication
@EnableEurekaClient
public class Provider8001_APP {
    public static void main(String[] args) {
        SpringApplication.run(Provider8001_APP.class,args);
    }
}
```

### actuator与微服务注册完善

#### 主机名称与服务名称的修改

- 修改服务名称，在yml中eureka节点下添加如下内容

```
 eureka:
  instance:
    instance-id: dept8001		#修改别名
    prefer-ip-address: true		#显示IP地址
```

#### info内容的详细信息修改

##### 作用

在查看Eureka时点击进入某个微服务的info时，能给查看者一些必要的信息，可以帮助查看者快速的了解该微服务，开发中十分有意义。

##### 修改方法

1. ==当前工程==添加依赖

```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
```

1. ==总的父工程==的build节点下添加如下内容

```
<build>
        <finalName>microservicecloud</finalName>
        <resources>
            <resource>
             	<!--允许扫描该路径下的资源文件-->
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <configuration>
                    <delimiters>
                     	<!--指定动态获取以$标志开头结尾的信息-->
                        <delimit>$</delimit>
                    </delimiters>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

1. 在==当前工程== 的application.yml文件添加回显信息

```
info:
  author: XXX
  build-version: $project.version$
```

### Eureka的自我保护

#### 介绍

Eureka的自我保护机制主要是为了网络异常时保持高可用设计的，当在Eureka中注册的微服务超过设定是时间内（默认90秒）没有向Eureka服务端发送心跳，该微服务会进入自我保护模式。在自我保护模式中，Eureka会保护服务注册表中的信息，不会注销任何服务实例，直至收到的心跳数恢复至阈值以上，该微服务退出自我保护模式。

#### 理解

好死不如赖活：Eureka的设计哲学是宁可保留错误的服务信息，也不盲目注销可能健康的服务。所以异常的服务不会被注销，而是进入了自我保护模式。

#### 自我保护模式的开关

在Eureka Server模块下的yml文件中添加配置信息即可，true表示打开自我保护模式；false表示关闭自我保护模式（不推荐）

```
  server:
    enable-self-preservation: false
```

### Eureka的服务发现

#### 介绍

系统中的微服务可以通过Eureka的服务发现去获得在Eureka中注册的服务的信息，这是一个对外暴露的接口。

#### 使用方法（provider中）

1. 注入DiscoveryClient 对象（spring包下的），在controller方法中获取

```
@Autowired
private DiscoveryClient discoveryClient;    

@ResponseBody
@GetMapping("/provider/discovery")
public Object discovery(){
        List<String> list = discoveryClient.getServices();
        System.out.println(list);
        List<ServiceInstance> insList = discoveryClient.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance si:insList) {
            System.out.println(si.getHost() +"," + si.getServiceId() +"," +si.getPort() +"," +si.getUri() +"," +si.getMetadata());
        }
        return this.discoveryClient;
    }
```

1. 在主启动类中加入@EnableDiscoveryClient注解

```
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class Provider8001_APP {
    public static void main(String[] args) {
        SpringApplication.run(Provider8001_APP.class,args);
    }
}
```

#### 使用方法（consumer中）

在controller方法中使用restTemplate对象调用provider中暴露的URL 并获得返回对象即可

```
@GetMapping("/discovery")
public Object discovery() {
        return restTemplate.getForObject(URL_PREFIX+"/provider/discovery",Object.class);
    }
```

### Eureka的集群配置

#### 集群

集群就是在不同的机器上配置相同的服务来构建要一个大的运算整体

#### 实现集群

1. 新建N个Eureka Server模块
2. 每个模块的pom.xml中加入与单个Eureka Server相同的依赖
3. 每个模块加入主程序（记得加@EnableEurekaServer注解）
4. 修改hosts文件（Win7的路径是C:\Windows\System32\drivers\etc）

```
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
127.0.0.1 eureka7003.com
```

1. 修改Eureka Server模块的application.yml文件，加入集群，主要修改两个地方：

- hostname：修改为hosts文件中映射的地址
- service-url下的defaultZone节点：填入集群中另外的server服务端的地址

```
server:
  port: 7001
eureka:
  instance:
    hostname: eureka7001.com    #hostname为hosts文件中映射的地址
  client:
    register-with-eureka: false     #false表示不向注册中心注册自己
    fetch-registry: false           #false表示自己就是注册中心，职责是维护实例，不参加检索
    service-url:
      #defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/    #设置eureka server的交互地址
      defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/  #其他两个服务端的地址
```

1. 修改Eureka Client模块的application.yml文件，使其向集群注册服务

- service-url下的defaultZone节点：填入集群中需要向其注册server服务端的地址

```
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka,http://eureka7002.com:7002/eureka,http://eureka7003.com:7003/eureka
```

1. 访问地址

```
http://eureka7001.com:7001
http://eureka7002.com:7002
http://eureka7003.com:7003
```

1. ==注：defaultZone中eureka/后缀是必须的，如果删除，Server类不会报错，但是Client注册时会报404错误==

### Eureka与Zookeeper对比

#### CAP设计原则不同

Eureka遵守AP，Zookeeper遵守CP（C：强一致性，A：高可用，P：分区容错性，三者只能选其二，高并发下P必选）

#### 网络波动下两者的处理对比

| Zookeeper                                                    | Eureka                                                       |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| 当网络出现故障时，剩余zk集群会发起投票选举新的leader，但是此过程会持续30~120s，此过程对于高并发来说十分漫长，会导致整个注册服务的瘫痪，这是不可容忍的 | 在15分钟内85%的节点都没有心跳，则注册中心 会认为客户端与之出现了网络故障，则会进入自动保护模式。1.Eureka不会移除没有收到心跳的服务；2.新的服务仍能在服务端注册，但是暂时不会被同步到其他节点上直到网络稳定 |

#### 结论

Eureka可以很好的应对网络故障导致部分节点失去连接的情况，而不会像zookeeper那样导致整个注册服务系统的瘫痪。

## Ribbon负载均衡

Spring Cloud Ribbon是基于Netflix Ribbon实现的一套==客户端==负载均衡工具。Ribbon会自动帮助你基于某种规则（简单轮询、随机连接等），也可以实现自定义的负载均衡算法。

### 负载均衡

- 英文名称：Load Balance，微服务或分布式集群中常用的一种应用
- 简单来说负载均衡就是将用户的请求ping平摊的分配到多个任务上，从而是系统达到HA（高可用）
- 两种负载均衡：
  1. 集中式LB：偏硬件，服务的消费方和提供方之间使用独立的LB设施，由该设施负责把访问请求以某种策略转发至服务的提供方。
  2. 进程内LB：骗软件， 将LB逻辑集成到消费方，消费方从服务注册中心指导哪些地址可用，再自己选择一个合适的服务器。

#### Ribbon初步配置

- ==Ribbon是客户端负载均衡工具！！！Ribbon是客户端负载均衡工具！！！Ribbon是客户端负载均衡工具！！！==所以应该配置在客户端

1. 加入依赖，因为Riboon需要依赖Eureka运行，所以要同时加入Eureka依赖

```
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-ribbon</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
```

1. 对实现类加入@LoadBalanced注解

```
@Bean
@LoadBalanced
public RestTemplate getRestTemplate() {
        return  new RestTemplate();
    }
}
```

1. 在application.yml文件中配置向注册中心注册，如果是作为消费者模块不提供服务，不应该注册自己

```
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
    register-with-eureka: false				#作为消费者不提供服务，不应该注册自己
```

1. 主启动类中加入@EnableEurekaClient注解

```
@SpringBootApplication
@EnableEurekaClient
public class Consumer80_APP {
    public static void main(String[] args) {
        SpringApplication.run(Consumer80_APP.class,args);
    }
}
```

1. 以上步骤1~4完成后即可在controller中直接通过服务名访问系统中的微服务，服务名作为URI

```
private static final String URL_PREFIX = "http://MICROSERVICECLOUD-DEPT/";
```

#### Ribbon负载均衡实现

架构示意图：

![Ribbon负载均衡架构](https://gitee.com/xiongrj/springcloud-base/raw/master/images%5CRibbon%E8%B4%9F%E8%BD%BD%E5%9D%87%E8%A1%A1%E6%9E%B6%E6%9E%84.png)

##### 实现方法

目标：构建provider集群后consumer通过负载均衡轮询调用在Eureka中注册的服务

1. 构建集群，新开两个provider模块，将原provider的==代码部分和pom.xml中依赖照搬==到新的provider中
2. 将原provider中application.yml文件照搬到新provider，并修改端口号，若新的provider使用自己的数据库，则修改数据库信息（其他配置也一样，如修改别名）
3. 集群中服务名称必须一致！！！

```
spring:
  application:
    name: microservicecloud-dept   #同一集群下必须使用同一服务名！！！！！
```

1. 启动服务，进行测试

##### 总结

Ribbon其实就是一个软负载均衡的客户端组件，可以和其他需要请求的客户端结合使用。

### Ribbon核心组件IRule

IRule：根据特定算法从服务列表中选取一个需要访问的服务

#### 七大方法

==IRule是一个接口，七大方法是其自带的落地实现类==

- RoundRobinRule：轮询（默认方法）
- RandomRule：随机
- AvailabilityFilteringRule：先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，还有并发的连接数量超过阈值的服务，然后对剩余的服务进行轮询
- WeightedResponseTimeRule：根据平均响应时间计算服务的权重。统计信息不足时会按照轮询，统计信息足够会按照响应的时间选择服务
- RetryRule：正常时按照轮询选择服务，若过程中有服务出现故障，在轮询一定次数后依然故障，则会跳过故障的服务继续轮询。
- BestAvailableRule：先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务
- ZoneAvoidanceRule：默认规则，符合判断server所在的区域的性能和server的可用性选择服务

#### 切换规则方法

只需在==配置类==中配置一个返回具体方法的bean即可

```
@Bean
public IRule MyRule(){
        return new RandomRule();    
    }
```

### 自定义Ribbon负载均衡算法

#### 配置及包位置

1. 自定义的Ribbon算法类不能放在主启动类所在的包及子报下（确切来说是不能放在@ComponentScan注解的包及子包下），否则会被全局应用到Ribbon服务中。应该把自定义算法类放在另外新建的包下，且这个类应该是为==配置类==。（其实与普通切换负载均衡规则类似，只不过是位置不同而已，普通的可以放在主启动类所在的包，自定义的要放在外面的包下）
2. 主启动类添加@RibbonClient(name = "微服务名",configuration = XXX.class)注解指定需要用到负载均衡的微服务名及自定义算法的class对象。

```
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "MICROSERVICECLOUD-DEPT",configuration = MyRule.class)
public class Consumer80_APP {
    public static void main(String[] args) {
        SpringApplication.run(Consumer80_APP.class,args);
    }
}
```

\####通过修改源代码获得自定义算法

目标：每个服务调用5次后再进行轮询（调用次数不是很对，懒得改了)

```java
package com.Rules;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MyRule extends AbstractLoadBalancerRule {

    private AtomicInteger nextServerCyclicCounter;
    private static final boolean AVAILABLE_ONLY_SERVERS = true;
    private static final boolean ALL_SERVERS = false;
    private int total = 0;
    private int currentIndex = 0;

    private static Logger log = LoggerFactory.getLogger(RoundRobinRule.class);

    public MyRule() {
        nextServerCyclicCounter = new AtomicInteger(0);
    }

    public MyRule(ILoadBalancer lb) {
        this();
        setLoadBalancer(lb);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }
            if (total > 5) {
                total = 0;
                int nextServerIndex = incrementAndGetModulo(serverCount);
                currentIndex = nextServerIndex;
                server = allServers.get(nextServerIndex);
            }else {
                if (currentIndex>=serverCount) {
                    currentIndex = 0;
                }
                server = allServers.get(currentIndex);
                total++;
            }


            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }

    /**
     * Inspired by the implementation of {@link AtomicInteger#incrementAndGet()}.
     *
     * @param modulo The modulo to bound the value of the counter.
     * @return The next value.
     */
    private int incrementAndGetModulo(int modulo) {
        for (;;) {
            int current = nextServerCyclicCounter.get();
            int next = (current + 1) % modulo;
            if (nextServerCyclicCounter.compareAndSet(current, next))
                return next;
        }
    }


    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }


    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
```

## Feign负载均衡

Feign是一个声明式WebService客户端，使用方法时定义一个接口并在上面添加注解即可。Feign支持可拔插式的编码器和解码器。Spring Cloud对Feign进行了封装，使其支持SpringMVC和HttpMessageConverters。Feign可以与Eureka和Ribbon组合使用以支持负载均衡。

### 使用案例

1. 新建Feign模块，加入依赖（其实跟80消费者差不多，主要是多了Feign依赖）

```
    <dependencies>
        <dependency>
            <groupId>com.XXX</groupId>
            <artifactId>microservice-api</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-feign</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-ribbon</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>springloaded</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>
```

1. 因为Feign开发其实是面向接口编程，所以Feign接口可以放在api模块中供各模块使用，所以要在api模块中添加Feign依赖
2. 在api中编写接口，接口上添加@FeignClient注解，并通过value指定作用的微服务名

```
@FeignClient(value = "MICROSERVICECLOUD-DEPT")
public interface DeptClientService {

    @PostMapping("/dept")
    public boolean addDept(Dept dept);

    @GetMapping("/dept")
    public List<Dept> findAll();

    @GetMapping("/dept/{id}")
    public Dept findById(@PathVariable("id")Integer id);
}
```

1. 在Feign模块中编写Controller，并注入FeignClient接口，直接调用service接口中的方法即可（因为声明Feign接口时已经指定过微服务，所以访问时会正确地找到微服务）

```
@RestController
@RequestMapping("/consumer")
public class ConsumerDeptController {
    @Autowired
    private DeptClientService service;

    @PostMapping("/dept")
    public boolean addDept(Dept dept){
        return service.addDept(dept);
    }

    @GetMapping("/dept")
    public List<Dept> findAll(){
        return service.findAll();
    }

    @GetMapping("/dept/{id}")
    public Dept findById(@PathVariable("id")Integer id){
        return service.findById(id);
    }
}
```

1. 修改Feign模块的主启动类，加入@EnableFeignClients注解和@ComponentScan注解（主要是扫描api中声明的接口）

```
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.XXX"})
@ComponentScan("com.XXX")
public class Consumer80Feign_APP {
    public static void main(String[] args) {
        SpringApplication.run(Consumer80Feign_APP.class,args);
    }
}
```

1. 启动后访问，即会按照轮询的方式调用provider集群

### 总结

- Feign通过接口方法调用REST服务，在Eureka中查找对应的服务
- Feign集成了Ribbon技术，所以也支持负载均衡（轮询）
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














