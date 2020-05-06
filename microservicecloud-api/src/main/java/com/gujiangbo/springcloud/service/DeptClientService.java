package com.gujiangbo.springcloud.service;

import com.gujiangbo.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author gujiangbo
 */
@FeignClient(value = "MICROSERVICECLOUD-DEPT")//定义feign 负载均衡
public interface DeptClientService {

    @RequestMapping(value = "/insertDept", method = RequestMethod.POST)
    public String insertDept(Dept dept);

    @RequestMapping(value = "/findDeptById", method = RequestMethod.POST)
    public String findDeptById(String id);

    @RequestMapping(value = "/findDeptAll", method = RequestMethod.POST)
    public String findDeptAll();
}
