package com.gujiangbo.springcloud.application.configbean;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
     *
     * LoadBalanced 是SpringCloud ribbon实现客户端负载均衡工具
     *  默认采用的算法是轮询算法，每个微服务轮询访问
     *  常用算法如下：
     *  RoundRobinRule: 轮询
     *  RandomRule：随机
     *  AvailabilityFilteringRule: 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，还有并发
     *  的连接数量超过阈值的服务，然后对剩余的列表按照轮询策略进行访问
     *
     *  WeightedResponseTimeRule: 根据平均的响应时间计算所有服务的权重，响应时间越快服务权重越大，被选中的概率越高
     *  刚启动时如果是统计时间不足，则采用RoundRobinRule策略，等统计时间足够会切回WeightedResponseTimeRule
     *
     *  RetryRule 先按照RoundRobinRule的策略获取服务，如果获取服务失败则在指定时间内重试，获取可用的服务
     *
     *  BestAvailableRule 会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务。然后选择一个并发量最小的服务
     *
     *  ZoneAvoidanceRule 默认规则，复合判断server所在区域的性能和server可用性选择服务器
     *
     **/
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * 随机
     * 如果生成了一个IRue对象，则将覆盖默认的轮询方式RoundRobinRule
     * @return
     */
 /*   @Bean
    public IRule rule(){
        return new RandomRule();
    }*/


}