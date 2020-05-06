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




### 服务降级
### 服务监控hystrixDashboard

## zuul 路由网关

## SpringCloud Config SpringCloud 配置中心 

