package com.gujiangbo.springcloud.rule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 自定义Ribbon算法
 * 注意：自定义策略规则不能与主启动类在同一个包下，这是因为ribbon文档要求  自定义的规则类不能再@packageScan扫描的包下
 */
@Configuration
public class MyRule {

    /**
     * @return
     */
    @Bean
    public IRule rule() {
        // return new RandomRule();//采用使用随机算法
        return new MyRandomRule();
    }
}
